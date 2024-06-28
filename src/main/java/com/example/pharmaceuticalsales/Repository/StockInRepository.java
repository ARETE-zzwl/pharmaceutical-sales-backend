package com.example.pharmaceuticalsales.Repository;

import com.example.pharmaceuticalsales.Model.StockIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface StockInRepository extends JpaRepository<StockIn, Long> {
    @Query("SELECT SUM(si.quantity * si.unitPrice) FROM StockIn si WHERE DATE(si.stockInDate) = DATE(:date)")
    double calculateTotalPurchaseAmount(@Param("date") Date date);
}
