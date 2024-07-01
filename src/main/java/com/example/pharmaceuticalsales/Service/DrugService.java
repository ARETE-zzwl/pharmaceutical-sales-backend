package com.example.pharmaceuticalsales.Service;


import com.example.pharmaceuticalsales.Model.Drug;
import com.example.pharmaceuticalsales.Repository.DrugRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.pharmaceuticalsales.Exception.ResourceNotFoundException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class DrugService {

    private final DrugRepository drugRepository;

    @Autowired
    public DrugService(DrugRepository drugRepository) {
        this.drugRepository = drugRepository;
    }
    public Drug getDrugById(Long id) {
        return drugRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Drug not found"));
    }
    public Page<Drug> getAllDrugs(Pageable pageable) {
        return drugRepository.findAll(pageable);
    }

    public Drug saveDrug(Drug drug) {
        return drugRepository.save(drug);
    }

    public List<Drug> saveDrugs(List<Drug> drugs) {
        return drugRepository.saveAll(drugs);
    }

    public Drug updateDrug(Long id, Drug drug) {
        Drug existingDrug = drugRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Drug not found"));
        existingDrug.setName(drug.getName());
        existingDrug.setSpecification(drug.getSpecification());
        existingDrug.setManufacturer(drug.getManufacturer());
        existingDrug.setBatchNumber(drug.getBatchNumber());
        existingDrug.setExpirationDate(drug.getExpirationDate());
        existingDrug.setUnitPrice(drug.getUnitPrice());
        return drugRepository.save(existingDrug);
    }

    public void deleteDrug(Long id) {
        drugRepository.deleteById(id);
    }

    public List<Drug> searchDrugsByName(String name) {
        return drugRepository.findByNameContaining(name);
    }

    public List<Drug> getDrugsExpiringSoon() {
        Date currentDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.add(Calendar.MONTH, 1); // 假设即将过期的定义是一个月内
        Date nextMonth = cal.getTime();
        return drugRepository.findByExpirationDateBetween(currentDate, nextMonth);
    }

    public List<Drug> getDrugsByManufacturer(String manufacturer) {
        return drugRepository.findByManufacturer(manufacturer);
    }

    public Page<Drug> getExpiredDrugs(Pageable pageable) {
        Date currentDate = new Date();
        return drugRepository.findByExpirationDateBefore(currentDate, pageable);
    }
}
