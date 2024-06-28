package com.example.pharmaceuticalsales.Controller;


import com.example.pharmaceuticalsales.Model.ReturnHandling;
import com.example.pharmaceuticalsales.Service.ReturnHandlingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/returnhandlings")
public class ReturnHandlingController {

    private final ReturnHandlingService returnHandlingService;

    @Autowired
    public ReturnHandlingController(ReturnHandlingService returnHandlingService) {
        this.returnHandlingService = returnHandlingService;
    }

    @GetMapping
    public ResponseEntity<List<ReturnHandling>> getAllReturnHandlings() {
        return ResponseEntity.ok(returnHandlingService.getAllReturnHandlings());
    }

    @PostMapping
    public ResponseEntity<ReturnHandling> createReturnHandling(@RequestBody ReturnHandling returnHandling) {
        return ResponseEntity.ok(returnHandlingService.saveReturnHandling(returnHandling));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReturnHandling> updateReturnHandling(@PathVariable Long id, @RequestBody ReturnHandling returnHandling) {
        return ResponseEntity.ok(returnHandlingService.updateReturnHandling(id, returnHandling));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReturnHandling(@PathVariable Long id) {
        returnHandlingService.deleteReturnHandling(id);
        return ResponseEntity.noContent().build();
    }
}
