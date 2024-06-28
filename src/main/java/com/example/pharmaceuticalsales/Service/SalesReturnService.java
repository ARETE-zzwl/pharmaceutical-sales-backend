package com.example.pharmaceuticalsales.Service;


import com.example.pharmaceuticalsales.Exception.ResourceNotFoundException;
import com.example.pharmaceuticalsales.Model.SalesReturn;
import com.example.pharmaceuticalsales.Repository.SalesReturnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesReturnService {

    private final SalesReturnRepository salesReturnRepository;

    @Autowired
    public SalesReturnService(SalesReturnRepository salesReturnRepository) {
        this.salesReturnRepository = salesReturnRepository;
    }

    public List<SalesReturn> getAllSalesReturns() {
        return salesReturnRepository.findAll();
    }

    public SalesReturn saveSalesReturn(SalesReturn salesReturn) {
        return salesReturnRepository.save(salesReturn);
    }

    public SalesReturn updateSalesReturn(Long id, SalesReturn salesReturn) {
        SalesReturn existingSalesReturn = salesReturnRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("SalesReturn not found"));
        existingSalesReturn.setDrug(salesReturn.getDrug());
        existingSalesReturn.setQuantity(salesReturn.getQuantity());
        existingSalesReturn.setReason(salesReturn.getReason());
        existingSalesReturn.setReturnDate(salesReturn.getReturnDate());
        return salesReturnRepository.save(existingSalesReturn);
    }

    public void deleteSalesReturn(Long id) {
        salesReturnRepository.deleteById(id);
    }
}
