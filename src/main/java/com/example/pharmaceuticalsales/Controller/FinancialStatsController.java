package com.example.pharmaceuticalsales.Controller;

import com.example.pharmaceuticalsales.Model.FinancialStats;
import com.example.pharmaceuticalsales.Service.FinancialStatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<Page<FinancialStats>> getAllFinancialStats(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy) {
        return ResponseEntity.ok(financialStatsService.getAllFinancialStats(page, size, sortBy));
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
