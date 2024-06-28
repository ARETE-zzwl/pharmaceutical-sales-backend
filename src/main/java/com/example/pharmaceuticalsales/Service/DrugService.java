package com.example.pharmaceuticalsales.Service;


import com.example.pharmaceuticalsales.Exception.ResourceNotFoundException;
import com.example.pharmaceuticalsales.Model.Drug;
import com.example.pharmaceuticalsales.Repository.DrugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrugService {

    private final DrugRepository drugRepository;

    @Autowired
    public DrugService(DrugRepository drugRepository) {
        this.drugRepository = drugRepository;
    }

    public List<Drug> getAllDrugs() {
        return drugRepository.findAll();
    }

    public Drug saveDrug(Drug drug) {
        return drugRepository.save(drug);
    }

    public Drug updateDrug(Long id, Drug drug) {
        Drug existingDrug = drugRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Drug not found"));
        existingDrug.setName(drug.getName());
        existingDrug.setSpecification(drug.getSpecification());
        existingDrug.setManufacturer(drug.getManufacturer());
        existingDrug.setBatchNumber(drug.getBatchNumber());
        existingDrug.setExpirationDate(drug.getExpirationDate());
        return drugRepository.save(existingDrug);
    }

    public void deleteDrug(Long id) {
        drugRepository.deleteById(id);
    }
}
