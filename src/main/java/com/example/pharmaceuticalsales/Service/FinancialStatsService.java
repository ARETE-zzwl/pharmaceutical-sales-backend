package com.example.pharmaceuticalsales.Service;

import com.example.pharmaceuticalsales.Exception.ResourceNotFoundException;
import com.example.pharmaceuticalsales.Model.FinancialStats;
import com.example.pharmaceuticalsales.Repository.FinancialStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Service
public class FinancialStatsService {

    private final FinancialStatsRepository financialStatsRepository;
    private static final Logger LOGGER = Logger.getLogger(FinancialStatsService.class.getName());

    @Autowired
    public FinancialStatsService(FinancialStatsRepository financialStatsRepository) {
        this.financialStatsRepository = financialStatsRepository;
    }

    public Page<FinancialStats> getAllFinancialStats(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return financialStatsRepository.findAll(pageable);
    }

    public FinancialStats saveFinancialStats(FinancialStats financialStats) {
        validateFinancialStats(financialStats);
        return financialStatsRepository.save(financialStats);
    }

    public FinancialStats updateFinancialStats(Long id, FinancialStats financialStats) {
        FinancialStats existingFinancialStats = financialStatsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("FinancialStats not found"));
        validateFinancialStats(financialStats);
        existingFinancialStats.setStatsDate(financialStats.getStatsDate());
        existingFinancialStats.setSalesAmount(financialStats.getSalesAmount());
        existingFinancialStats.setPurchaseAmount(financialStats.getPurchaseAmount());
        existingFinancialStats.setReturnAmount(financialStats.getReturnAmount());
        return financialStatsRepository.save(existingFinancialStats);
    }

    public void deleteFinancialStats(Long id) {
        if (!financialStatsRepository.existsById(id)) {
            throw new ResourceNotFoundException("FinancialStats not found");
        }
        financialStatsRepository.deleteById(id);
    }

    public List<FinancialStats> getFinancialStatsByDate(Date statsDate) {
        return financialStatsRepository.findByStatsDate(statsDate);
    }

    private void validateFinancialStats(FinancialStats financialStats) {
        if (financialStats.getSalesAmount() < 0 || financialStats.getPurchaseAmount() < 0 || financialStats.getReturnAmount() < 0) {
            throw new IllegalArgumentException("Amounts must be non-negative");
        }
        if (financialStats.getStatsDate() == null) {
            throw new IllegalArgumentException("Stats date cannot be null");
        }
    }
}
