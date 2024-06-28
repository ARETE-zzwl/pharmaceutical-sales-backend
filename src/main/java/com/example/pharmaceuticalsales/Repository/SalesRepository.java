package com.example.pharmaceuticalsales.Repository;

import com.example.pharmaceuticalsales.Model.Sales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Long> {
}
