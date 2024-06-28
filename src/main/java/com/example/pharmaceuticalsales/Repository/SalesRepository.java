package com.example.pharmaceuticalsales.Repository;

import com.example.pharmaceuticalsales.Model.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Long> {
    @Query("SELECT SUM(s.quantity * s.unitPrice) FROM Sales s WHERE DATE(s.salesDate) = DATE(:date)")
    double calculateTotalSalesAmount(@Param("date") Date date);
}
