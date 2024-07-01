package com.example.pharmaceuticalsales.Controller;

import com.example.pharmaceuticalsales.Model.Drug;
import com.example.pharmaceuticalsales.Service.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/drugs")
public class DrugController {

    private final DrugService drugService;

    @Autowired
    public DrugController(DrugService drugService) {
        this.drugService = drugService;
    }

    @GetMapping
    public ResponseEntity<List<Drug>> getAllDrugs() {
        return ResponseEntity.ok(drugService.getAllDrugs());
    }

    @PostMapping
    public ResponseEntity<Drug> createDrug(@RequestBody Drug drug) {
        return ResponseEntity.ok(drugService.saveDrug(drug));
    }

    @PostMapping("/batch")
    public ResponseEntity<List<Drug>> createDrugs(@RequestBody List<Drug> drugs) {
        return ResponseEntity.ok(drugService.saveDrugs(drugs));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Drug> updateDrug(@PathVariable Long id, @RequestBody Drug drug) {
        return ResponseEntity.ok(drugService.updateDrug(id, drug));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDrug(@PathVariable Long id) {
        drugService.deleteDrug(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Drug>> searchDrugsByName(@RequestParam String name) {
        return ResponseEntity.ok(drugService.searchDrugsByName(name));
    }

    @GetMapping("/expiring-soon")
    public ResponseEntity<List<Drug>> getDrugsExpiringSoon() {
        return ResponseEntity.ok(drugService.getDrugsExpiringSoon());
    }

    @GetMapping("/manufacturer/{manufacturer}")
    public ResponseEntity<List<Drug>> getDrugsByManufacturer(@PathVariable String manufacturer) {
        return ResponseEntity.ok(drugService.getDrugsByManufacturer(manufacturer));
    }
}
