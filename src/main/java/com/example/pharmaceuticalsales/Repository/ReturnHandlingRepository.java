package com.example.pharmaceuticalsales.Repository;

import com.example.pharmaceuticalsales.Model.ReturnHandling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReturnHandlingRepository extends JpaRepository<ReturnHandling, Long> {
}
