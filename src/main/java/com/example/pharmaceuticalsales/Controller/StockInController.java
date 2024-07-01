package com.example.pharmaceuticalsales.Controller;


import com.example.pharmaceuticalsales.Model.StockIn;
import com.example.pharmaceuticalsales.Service.StockInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/stockins")
public class StockInController {

    private final StockInService stockInService;

    @Autowired
    public StockInController(StockInService stockInService) {
        this.stockInService = stockInService;
    }

    @GetMapping
    public ResponseEntity<List<StockIn>> getAllStockIns() {
        return ResponseEntity.ok(stockInService.getAllStockIns());
    }

    @PostMapping
    public ResponseEntity<StockIn> createStockIn(@RequestBody StockIn stockIn) {
        return ResponseEntity.ok(stockInService.saveStockIn(stockIn));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StockIn> updateStockIn(@PathVariable Long id, @RequestBody StockIn stockIn) {
        return ResponseEntity.ok(stockInService.updateStockIn(id, stockIn));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStockIn(@PathVariable Long id) {
        stockInService.deleteStockIn(id);
        return ResponseEntity.noContent().build();
    }
}
