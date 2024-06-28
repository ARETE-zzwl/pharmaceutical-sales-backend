package com.example.pharmaceuticalsales.Service;


import com.example.pharmaceuticalsales.Exception.ResourceNotFoundException;
import com.example.pharmaceuticalsales.Model.ReturnHandling;
import com.example.pharmaceuticalsales.Repository.ReturnHandlingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReturnHandlingService {

    private final ReturnHandlingRepository returnHandlingRepository;

    @Autowired
    public ReturnHandlingService(ReturnHandlingRepository returnHandlingRepository) {
        this.returnHandlingRepository = returnHandlingRepository;
    }

    public List<ReturnHandling> getAllReturnHandlings() {
        return returnHandlingRepository.findAll();
    }

    public ReturnHandling saveReturnHandling(ReturnHandling returnHandling) {
        return returnHandlingRepository.save(returnHandling);
    }

    public ReturnHandling updateReturnHandling(Long id, ReturnHandling returnHandling) {
        ReturnHandling existingReturnHandling = returnHandlingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ReturnHandling not found"));
        existingReturnHandling.setDrug(returnHandling.getDrug());
        existingReturnHandling.setQuantity(returnHandling.getQuantity());
        existingReturnHandling.setReason(returnHandling.getReason());
        existingReturnHandling.setReturnDate(returnHandling.getReturnDate());
        return returnHandlingRepository.save(existingReturnHandling);
    }

    public void deleteReturnHandling(Long id) {
        returnHandlingRepository.deleteById(id);
    }
}
