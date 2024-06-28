package com.example.pharmaceuticalsales.Controller;


import com.example.pharmaceuticalsales.Model.FinancialStats;
import com.example.pharmaceuticalsales.Service.FinancialStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/financialstats")
public class FinancialStatsController {

    private final FinancialStatsService financialStatsService;

    @Autowired
    public FinancialStatsController(FinancialStatsService financialStatsService) {
        this.financialStatsService = financialStatsService;
    }

    @GetMapping
    public ResponseEntity<List<FinancialStats>> getAllFinancialStats() {
        return ResponseEntity.ok(financialStatsService.getAllFinancialStats());
    }

    @PostMapping
    public ResponseEntity<FinancialStats> createFinancialStats(@RequestBody FinancialStats financialStats) {
        return ResponseEntity.ok(financialStatsService.saveFinancialStats(financialStats));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FinancialStats> updateFinancialStats(@PathVariable Long id, @RequestBody FinancialStats financialStats) {
        return ResponseEntity.ok(financialStatsService.updateFinancialStats(id, financialStats));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFinancialStats(@PathVariable Long id) {
        financialStatsService.deleteFinancialStats(id);
        return ResponseEntity.noContent().build();
    }
}
