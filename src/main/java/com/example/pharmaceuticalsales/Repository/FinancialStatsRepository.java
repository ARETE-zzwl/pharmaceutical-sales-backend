package com.example.pharmaceuticalsales.Repository;

import com.example.pharmaceuticalsales.Model.FinancialStats;
import com.example.pharmaceuticalsales.Model.MonthlyStats;
import com.example.pharmaceuticalsales.Model.YearlyStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface FinancialStatsRepository extends JpaRepository<FinancialStats, Long> {
    List<FinancialStats> findByStatsDate(Date statsDate);
    List<FinancialStats> findByStatsDateBetween(Date startDate, Date endDate);
    @Query("SELECT new com.example.pharmaceuticalsales.Model.MonthlyStats(" +
            "YEAR(f.statsDate), MONTH(f.statsDate), SUM(f.salesAmount), SUM(f.purchaseAmount), SUM(f.returnAmount)) " +
            "FROM FinancialStats f " +
            "WHERE YEAR(f.statsDate) = :year " +
            "GROUP BY YEAR(f.statsDate), MONTH(f.statsDate)")
    List<MonthlyStats> findMonthlyStatsByYear(int year);

    @Query("SELECT new com.example.pharmaceuticalsales.Model.YearlyStats(" +
            "YEAR(f.statsDate), SUM(f.salesAmount), SUM(f.purchaseAmount), SUM(f.returnAmount)) " +
            "FROM FinancialStats f " +
            "WHERE YEAR(f.statsDate) = :year " +
            "GROUP BY YEAR(f.statsDate)")
    List<YearlyStats> findYearlyStatsByYear(int year);
}
