package com.example.pharmaceuticalsales.Repository;

import com.example.pharmaceuticalsales.Model.FinancialStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancialStatsRepository extends JpaRepository<FinancialStats, Long> {
}
