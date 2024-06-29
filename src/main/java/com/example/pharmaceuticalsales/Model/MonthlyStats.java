package com.example.pharmaceuticalsales.Model;

import lombok.Data;

@Data
public class MonthlyStats {
    private int year;
    private int month;
    private double totalSales;
    private double totalPurchases;
    private double totalReturns;

    public MonthlyStats(int year, int month, double totalSales, double totalPurchases, double totalReturns) {
        this.year = year;
        this.month = month;
        this.totalSales = totalSales;
        this.totalPurchases = totalPurchases;
        this.totalReturns = totalReturns;
    }

    // Getters and Setters
}
