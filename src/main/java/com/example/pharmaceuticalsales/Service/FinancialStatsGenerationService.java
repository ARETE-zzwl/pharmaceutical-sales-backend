package com.example.pharmaceuticalsales.Service;

import com.example.pharmaceuticalsales.Model.FinancialStats;
import com.example.pharmaceuticalsales.Repository.FinancialStatsRepository;
import com.example.pharmaceuticalsales.Repository.SalesRepository;
import com.example.pharmaceuticalsales.Repository.StockInRepository;
import com.example.pharmaceuticalsales.Repository.SalesReturnRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.logging.Logger;

@Service
public class FinancialStatsGenerationService {

    private static final Logger LOGGER = Logger.getLogger(FinancialStatsGenerationService.class.getName());

    @Autowired
    private FinancialStatsRepository financialStatsRepository;

    @Autowired
    private SalesRepository salesRepository;

    @Autowired
    private StockInRepository stockInRepository;

    @Autowired
    private SalesReturnRepository salesReturnRepository;

    @Scheduled(cron = "0 0 0 * * ?") // 每天凌晨执行一次
    public void generateDailyFinancialStats() {
        LOGGER.info("Generating daily financial stats...");

        Date currentDate = new Date(); // 使用当前日期作为统计日期
        double salesAmount = salesRepository.calculateTotalSalesAmount(currentDate);
        double purchaseAmount = stockInRepository.calculateTotalPurchaseAmount(currentDate);
        double returnAmount = salesReturnRepository.calculateTotalReturnAmount(currentDate);

        FinancialStats financialStats = new FinancialStats();
        financialStats.setStatsDate(currentDate);
        financialStats.setSalesAmount(salesAmount);
        financialStats.setPurchaseAmount(purchaseAmount);
        financialStats.setReturnAmount(returnAmount);

        financialStatsRepository.save(financialStats);

        LOGGER.info("Daily financial stats generated successfully.");
    }
}
