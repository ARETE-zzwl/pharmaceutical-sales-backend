package com.example.pharmaceuticalsales.Repository;


import com.example.pharmaceuticalsales.Model.SalesReturn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesReturnRepository extends JpaRepository<SalesReturn, Long> {
}
