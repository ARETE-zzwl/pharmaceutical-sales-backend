package com.example.pharmaceuticalsales.Service;


import com.example.pharmaceuticalsales.Exception.ResourceNotFoundException;
import com.example.pharmaceuticalsales.Model.StockIn;
import com.example.pharmaceuticalsales.Repository.StockInRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockInService {

    private final StockInRepository stockInRepository;

    @Autowired
    public StockInService(StockInRepository stockInRepository) {
        this.stockInRepository = stockInRepository;
    }

    public List<StockIn> getAllStockIns() {
        return stockInRepository.findAll();
    }

    public StockIn saveStockIn(StockIn stockIn) {
        return stockInRepository.save(stockIn);
    }

    public StockIn updateStockIn(Long id, StockIn stockIn) {
        StockIn existingStockIn = stockInRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("StockIn not found"));
        existingStockIn.setDrug(stockIn.getDrug());
        existingStockIn.setQuantity(stockIn.getQuantity());
        existingStockIn.setUnitPrice(stockIn.getUnitPrice());
        existingStockIn.setSupplier(stockIn.getSupplier());
        existingStockIn.setStockInDate(stockIn.getStockInDate());
        return stockInRepository.save(existingStockIn);
    }

    public void deleteStockIn(Long id) {
        stockInRepository.deleteById(id);
    }
}
