package com.example.pharmaceuticalsales.Repository;

import com.example.pharmaceuticalsales.Model.StockIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockInRepository extends JpaRepository<StockIn, Long> {
}
