package com.example.pharmaceuticalsales.Service;


import com.example.pharmaceuticalsales.Exception.ResourceNotFoundException;
import com.example.pharmaceuticalsales.Model.FinancialStats;
import com.example.pharmaceuticalsales.Repository.FinancialStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinancialStatsService {

    private final FinancialStatsRepository financialStatsRepository;

    @Autowired
    public FinancialStatsService(FinancialStatsRepository financialStatsRepository) {
        this.financialStatsRepository = financialStatsRepository;
    }

    public List<FinancialStats> getAllFinancialStats() {
        return financialStatsRepository.findAll();
    }

    public FinancialStats saveFinancialStats(FinancialStats financialStats) {
        return financialStatsRepository.save(financialStats);
    }

    public FinancialStats updateFinancialStats(Long id, FinancialStats financialStats) {
        FinancialStats existingFinancialStats = financialStatsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("FinancialStats not found"));
        existingFinancialStats.setStatsDate(financialStats.getStatsDate());
        existingFinancialStats.setSalesAmount(financialStats.getSalesAmount());
        existingFinancialStats.setPurchaseAmount(financialStats.getPurchaseAmount());
        existingFinancialStats.setReturnAmount(financialStats.getReturnAmount());
        return financialStatsRepository.save(existingFinancialStats);
    }

    public void deleteFinancialStats(Long id) {
        financialStatsRepository.deleteById(id);
    }
}
