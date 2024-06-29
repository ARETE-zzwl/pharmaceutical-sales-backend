package com.example.pharmaceuticalsales.Controller;


import com.example.pharmaceuticalsales.Model.SalesReturn;
import com.example.pharmaceuticalsales.Service.SalesReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/salesreturns")
public class SalesReturnController {

    private final SalesReturnService salesReturnService;

    @Autowired
    public SalesReturnController(SalesReturnService salesReturnService) {
        this.salesReturnService = salesReturnService;
    }

    @GetMapping
    public ResponseEntity<List<SalesReturn>> getAllSalesReturns() {
        return ResponseEntity.ok(salesReturnService.getAllSalesReturns());
    }

    @PostMapping
    public ResponseEntity<SalesReturn> createSalesReturn(@RequestBody SalesReturn salesReturn) {
        return ResponseEntity.ok(salesReturnService.saveSalesReturn(salesReturn));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SalesReturn> updateSalesReturn(@PathVariable Long id, @RequestBody SalesReturn salesReturn) {
        return ResponseEntity.ok(salesReturnService.updateSalesReturn(id, salesReturn));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSalesReturn(@PathVariable Long id) {
        salesReturnService.deleteSalesReturn(id);
        return ResponseEntity.noContent().build();
    }
}
