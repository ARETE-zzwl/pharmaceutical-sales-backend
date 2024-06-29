package com.example.pharmaceuticalsales.Repository;

import com.example.pharmaceuticalsales.Model.FinancialStats;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface FinancialStatsRepository extends JpaRepository<FinancialStats, Long> {
    List<FinancialStats> findByStatsDate(Date statsDate);
    List<FinancialStats> findByStatsDateBetween(Date startDate, Date endDate);
}
