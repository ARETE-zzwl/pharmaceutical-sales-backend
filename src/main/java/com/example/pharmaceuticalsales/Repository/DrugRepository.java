package com.example.pharmaceuticalsales.Repository;

import com.example.pharmaceuticalsales.Model.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DrugRepository extends JpaRepository<Drug, Long> {
    List<Drug> findByNameContaining(String name);
    List<Drug> findByExpirationDateBetween(Date startDate, Date endDate);
    List<Drug> findByManufacturer(String manufacturer);
}
