package com.example.pharmaceuticalsales.Service;

import com.example.pharmaceuticalsales.Exception.ResourceNotFoundException;
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

import java.util.List;
import java.util.Optional;

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

        // Prepare the input data with the correct shape [miniBatchSize, nIn, timeSeriesLength]
        INDArray input = Nd4j.create(new int[]{1, 1, trainSize});
        INDArray output = Nd4j.create(new int[]{1, 1, trainSize});

        for (int i = 0; i < trainSize; i++) {
            input.putScalar(new int[]{0, 0, i}, quantities[i]);
            output.putScalar(new int[]{0, 0, i}, quantities[i + 1]);
        }

        DataSet trainData = new DataSet(input, output);
        DataSetIterator trainDataIterator = new org.deeplearning4j.datasets.iterator.impl.SingletonDataSetIterator(trainData);
        NormalizerStandardize normalizer = new NormalizerStandardize();
        normalizer.fit(trainDataIterator);
        trainDataIterator.setPreProcessor(normalizer);

        int lstmLayerSize = 50;

        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
                .optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT)
                .updater(new Adam(0.001))
                .list()
                .layer(0, new LSTM.Builder()
                        .nIn(1)
                        .nOut(lstmLayerSize)
                        .activation(Activation.TANH)
                        .build())
                .layer(1, new RnnOutputLayer.Builder(LossFunctions.LossFunction.MSE)
                        .activation(Activation.IDENTITY)
                        .nIn(lstmLayerSize)
                        .nOut(1)
                        .build())
                .build();

        MultiLayerNetwork net = new MultiLayerNetwork(conf);
        net.init();
        net.setListeners(new ScoreIterationListener(20));

        for (int i = 0; i < 100; i++) {
            trainDataIterator.reset();
            net.fit(trainDataIterator);
        }

        // Prepare the forecast input data
        INDArray forecastInput = Nd4j.create(new int[]{1, 1, days});
        for (int i = 0; i < days; i++) {
            forecastInput.putScalar(new int[]{0, 0, i}, quantities[quantities.length - days + i]);
        }

        INDArray forecastOutput = net.rnnTimeStep(forecastInput);
        normalizer.revertLabels(forecastOutput);

        double forecast = forecastOutput.getDouble(forecastOutput.length() - 1);

        List<Inventory> inventories = inventoryRepository.findAllByDrugDrugId(drugId);
        if (inventories.size() != 1) {
            throw new IllegalArgumentException("Expected exactly one inventory record for drug ID " + drugId + ", but found " + inventories.size());
        }

        Inventory inventory = inventories.get(0);
        return inventory.getQuantity() - forecast;
    }
}
