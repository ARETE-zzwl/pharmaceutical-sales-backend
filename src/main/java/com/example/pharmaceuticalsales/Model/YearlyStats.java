package com.example.pharmaceuticalsales.Model;

import lombok.Data;

@Data
public class YearlyStats {
    private int year;
    private double totalSales;
    private double totalPurchases;
    private double totalReturns;

    public YearlyStats(int year, double totalSales, double totalPurchases, double totalReturns) {
        this.year = year;
        this.totalSales = totalSales;
        this.totalPurchases = totalPurchases;
        this.totalReturns = totalReturns;
    }

    // Getters and Setters
}
