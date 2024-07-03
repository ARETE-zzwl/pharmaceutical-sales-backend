package com.example.pharmaceuticalsales.Service;

import com.example.pharmaceuticalsales.Exception.ResourceNotFoundException;
import com.example.pharmaceuticalsales.Model.Drug;
import com.example.pharmaceuticalsales.Model.Inventory;
import com.example.pharmaceuticalsales.Model.Sales;
import com.example.pharmaceuticalsales.Repository.InventoryRepository;
import com.example.pharmaceuticalsales.Repository.SalesRepository;
import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.LSTM;
import org.deeplearning4j.nn.conf.layers.RnnOutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.dataset.api.preprocessor.NormalizerStandardize;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.learning.config.Adam;
import org.nd4j.linalg.lossfunctions.LossFunctions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class InventoryService {

    private static final Logger log = LoggerFactory.getLogger(InventoryService.class);

    private final InventoryRepository inventoryRepository;
    private final SalesRepository salesRepository;

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository, SalesRepository salesRepository) {
        this.inventoryRepository = inventoryRepository;
        this.salesRepository = salesRepository;
    }

    public Page<Inventory> getAllInventories(Pageable pageable) {
        return inventoryRepository.findAll(pageable);
    }

    public List<Inventory> getAllInventories() {
        return inventoryRepository.findAll();
    }

    public Inventory getInventoryById(Long id) {
        return inventoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Inventory not found"));
    }

    public Inventory saveInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    public Inventory updateInventory(Long id, Inventory inventory) {
        Inventory existingInventory = inventoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Inventory not found"));
        existingInventory.setDrug(inventory.getDrug());
        existingInventory.setQuantity(inventory.getQuantity());
        existingInventory.setBatchNumber(inventory.getBatchNumber());
        existingInventory.setExpirationDate(inventory.getExpirationDate());
        return inventoryRepository.save(existingInventory);
    }

    public void deleteInventory(Long id) {
        inventoryRepository.deleteById(id);
    }

    public Inventory addStock(Long id, int quantity) {
        Inventory inventory = getInventoryById(id);
        inventory.setQuantity(inventory.getQuantity() + quantity);
        return inventoryRepository.save(inventory);
    }

    public Inventory reduceStock(Long id, int quantity) {
        Inventory inventory = getInventoryById(id);
        if (inventory.getQuantity() < quantity) {
            throw new IllegalArgumentException("Insufficient stock");
        }
        inventory.setQuantity(inventory.getQuantity() - quantity);
        return inventoryRepository.save(inventory);
    }

    public List<Sales> getSalesData(Long drugId) {
        return salesRepository.findByDrugDrugId(drugId);
    }

    public double predictStock(Long drugId, int days) {
        List<Sales> sales = getSalesData(drugId);

        if (sales.isEmpty()) {
            throw new IllegalArgumentException("No sales records found for drug ID " + drugId);
        }

        double[] quantities = sales.stream().mapToDouble(Sales::getQuantity).toArray();
        int trainSize = quantities.length - days;

        if (trainSize <= 0) {
            throw new IllegalArgumentException("Not enough data to train the model. Ensure that the number of days to predict is less than the total number of data points.");
        }

        log.debug("Training size: {}", trainSize);


        INDArray input = Nd4j.create(new int[]{1, 1, trainSize});//表示输入的样本数目
        INDArray output = Nd4j.create(new int[]{1, 1, trainSize});//表示输出的样本数目

        for (int i = 0; i < trainSize; i++) {
            input.putScalar(new int[]{0, 0, i}, quantities[i]);//输入
            output.putScalar(new int[]{0, 0, i}, quantities[i + 1]);//输出
        }

        DataSet trainData = new DataSet(input, output);//训练数据集
        DataSetIterator trainDataIterator = new org.deeplearning4j.datasets.iterator.impl.SingletonDataSetIterator(trainData);//训练数据集迭代器
        NormalizerStandardize normalizer = new NormalizerStandardize();//数据标准化
        normalizer.fit(trainDataIterator);//标准化
        trainDataIterator.setPreProcessor(normalizer);//数据标准化

        int lstmLayerSize = 50;//LSTM层的神经元个数

        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()//神经网络配置
                .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT)//优化算法
                .updater(new Adam(0.001))//学习率
                .list()//神经网络结构
                .layer(0, new LSTM.Builder()//LSTM层
                        .nIn(1)//输入神经元个数
                        .nOut(lstmLayerSize)//输出神经元个数
                        .activation(Activation.TANH)//激活函数
                        .build())//LSTM层
                .layer(1, new RnnOutputLayer.Builder(LossFunctions.LossFunction.MSE)//输出层
                        .activation(Activation.IDENTITY)//激活函数
                        .nIn(lstmLayerSize)//输入神经元个数
                        .nOut(1)//输出神经元个数
                        .build())//输出层
                .build();//神经网络配置

        MultiLayerNetwork net = new MultiLayerNetwork(conf);//神经网络
        net.init();//初始化神经网络
        net.setListeners(new ScoreIterationListener(20));//训练过程监听器

        for (int i = 0; i < 100; i++) {//训练100次
            trainDataIterator.reset();//数据集迭代器重置
            net.fit(trainDataIterator);//训练模型
        }

        // 预测
        INDArray forecastInput = Nd4j.create(new int[]{1, 1, days});//表示输入的样本数目
        for (int i = 0; i < days; i++) {//输入预测数据
            forecastInput.putScalar(new int[]{0, 0, i}, quantities[quantities.length - days + i]);//输入
        }

        INDArray forecastOutput = net.rnnTimeStep(forecastInput);//预测输出
        normalizer.revertLabels(forecastOutput);//数据反标准化

        double forecast = forecastOutput.getDouble(forecastOutput.length() - 1);//预测结果

        List<Inventory> inventories = inventoryRepository.findAllByDrugDrugId(drugId);//库存数据
        int totalQuantity = inventories.stream().mapToInt(Inventory::getQuantity).sum();//总库存量

        return totalQuantity - forecast;//库存量预测值
    }

    public List<Drug> getDrugsinventoryExpiringSoon() {

        Date currentDate = new Date();//表示当前时间
        Calendar cal = Calendar.getInstance();//得到日历实例
        cal.setTime(currentDate);//设置当前时间
        cal.add(Calendar.MONTH, 1); // 得到下个月
        Date nextMonth = cal.getTime();//得到下个月时间
        return inventoryRepository.findByExpirationDateBetween(currentDate, nextMonth);//得到即将过期的药品
        }

    public Page<Drug> getExpiredDrugs(Pageable pageable) {
        Date currentDate = new Date();
        return inventoryRepository.findByExpirationDateBefore(currentDate, pageable);
    }
}


