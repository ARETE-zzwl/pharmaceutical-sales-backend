package com.example.pharmaceuticalsales.Service;


import com.example.pharmaceuticalsales.Exception.ResourceNotFoundException;
import com.example.pharmaceuticalsales.Model.Sales;
import com.example.pharmaceuticalsales.Repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesService {

    private final SalesRepository salesRepository;

    @Autowired
    public SalesService(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    public List<Sales> getAllSales() {
        return salesRepository.findAll();
    }

    public Sales saveSales(Sales sales) {
        return salesRepository.save(sales);
    }

    public Sales updateSales(Long id, Sales sales) {
        Sales existingSales = salesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sales not found"));
        existingSales.setDrug(sales.getDrug());
        existingSales.setQuantity(sales.getQuantity());
        existingSales.setUnitPrice(sales.getUnitPrice());
        existingSales.setCustomer(sales.getCustomer());
        existingSales.setSalesDate(sales.getSalesDate());
        return salesRepository.save(existingSales);
    }
    public Sales getSaleById(Long id) {
        return salesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Sale not found"));
    }

    public void deleteSales(Long id) {
        salesRepository.deleteById(id);
    }
    public Page<Sales> getAllSales(Pageable pageable) {
        return salesRepository.findAll(pageable);
    }
}
