package com.example.pharmaceuticalsales.Repository;

import com.example.pharmaceuticalsales.Model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Inventory findByDrugDrugId(Long drugId);
}
