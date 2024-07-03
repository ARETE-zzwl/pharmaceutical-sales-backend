package com.example.pharmaceuticalsales.Repository;

import com.example.pharmaceuticalsales.Model.Drug;
import com.example.pharmaceuticalsales.Model.Inventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findByDrugDrugId(Long drugId);
    List<Inventory> findAllByDrugDrugId(Long drugId);

    List<Drug> findByExpirationDateBetween(Date currentDate, Date nextMonth);

    Page<Drug> findByExpirationDateBefore(Date currentDate, Pageable pageable);
}
