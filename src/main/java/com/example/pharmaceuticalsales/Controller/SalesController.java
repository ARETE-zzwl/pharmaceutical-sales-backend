package com.example.pharmaceuticalsales.Controller;


import com.example.pharmaceuticalsales.Model.Sales;
import com.example.pharmaceuticalsales.Service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/sales")
public class SalesController {

    private final SalesService salesService;

    @Autowired
    public SalesController(SalesService salesService) {
        this.salesService = salesService;
    }

    @GetMapping
    public ResponseEntity<Page<Sales>> getAllSales(Pageable pageable) {
        return ResponseEntity.ok(salesService.getAllSales(pageable));
    }

    @PostMapping
    public ResponseEntity<Sales> createSales(@RequestBody Sales sales) {
        return ResponseEntity.ok(salesService.saveSales(sales));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sales> updateSales(@PathVariable Long id, @RequestBody Sales sales) {
        return ResponseEntity.ok(salesService.updateSales(id, sales));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSales(@PathVariable Long id) {
        salesService.deleteSales(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sales> getSaleById(@PathVariable Long id) {
        Sales sales = salesService.getSaleById(id);
        return ResponseEntity.ok(sales);
    }
}

