package com.example.pharmaceuticalsales.Repository;

import com.example.pharmaceuticalsales.Model.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
@Repository
public interface DrugRepository extends JpaRepository<Drug, Long> {
    List<Drug> findByNameContaining(String name);
    List<Drug> findByExpirationDateBetween(Date startDate, Date endDate);
    List<Drug> findByManufacturer(String manufacturer);
    Page<Drug> findByExpirationDateBefore(Date currentDate, Pageable pageable);
}
