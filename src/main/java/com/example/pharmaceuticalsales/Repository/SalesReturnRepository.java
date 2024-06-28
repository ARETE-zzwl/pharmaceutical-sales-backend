package com.example.pharmaceuticalsales.Repository;


import com.example.pharmaceuticalsales.Model.SalesReturn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface SalesReturnRepository extends JpaRepository<SalesReturn, Long> {
    @Query("SELECT SUM(sr.quantity * d.unitPrice) FROM SalesReturn sr JOIN sr.drug d WHERE DATE(sr.returnDate) = DATE(:date)")
    double calculateTotalReturnAmount(@Param("date") Date date);
}
