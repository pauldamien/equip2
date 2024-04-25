package com.paul.project.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;


/*
@auteur paul Damien
 */
public interface StatisticsService {
    Map<LocalDate, BigDecimal> findSumTransactionByDate(LocalDate startDate, LocalDate endDate, Integer userId);

    BigDecimal getAccountBalance(Integer userId);

    BigDecimal highestTransfer(Integer userId);

    BigDecimal highestDeposit(Integer userId);


}
