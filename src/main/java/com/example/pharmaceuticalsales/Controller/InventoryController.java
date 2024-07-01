package com.example.pharmaceuticalsales.Controller;


import com.example.pharmaceuticalsales.Model.Inventory;
import com.example.pharmaceuticalsales.Service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventories")
public class InventoryController {

    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public ResponseEntity<List<Inventory>> getAllInventories() {
        return ResponseEntity.ok(inventoryService.getAllInventories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inventory> getInventoryById(@PathVariable Long id) {
        return ResponseEntity.ok(inventoryService.getInventoryById(id));
    }

    @PostMapping
    public ResponseEntity<Inventory> createInventory(@RequestBody Inventory inventory) {
        return ResponseEntity.ok(inventoryService.saveInventory(inventory));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inventory> updateInventory(@PathVariable Long id, @RequestBody Inventory inventory) {
        return ResponseEntity.ok(inventoryService.updateInventory(id, inventory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventory(@PathVariable Long id) {
        inventoryService.deleteInventory(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/add")
    public ResponseEntity<Inventory> addStock(@PathVariable Long id, @RequestParam int quantity) {
        return ResponseEntity.ok(inventoryService.addStock(id, quantity));
    }

    @PutMapping("/{id}/reduce")
    public ResponseEntity<Inventory> reduceStock(@PathVariable Long id, @RequestParam int quantity) {
        return ResponseEntity.ok(inventoryService.reduceStock(id, quantity));
    }

    @GetMapping("/{id}/predict")
    public ResponseEntity<Double> predictStock(@PathVariable Long id, @RequestParam int days) {
        return ResponseEntity.ok(inventoryService.predictStock(id, days));
    }
}
