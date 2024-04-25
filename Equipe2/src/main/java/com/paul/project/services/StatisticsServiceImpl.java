package com.paul.project.services;

import com.paul.project.entity.TransactionType;
import com.paul.project.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;

/*
@auteur paul Damien
 */
@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements  StatisticsService{
   private  final TransactionRepository transactionRepository;

    @Override
    public Map<LocalDate, BigDecimal> findSumTransactionByDate(LocalDate startDate, LocalDate endDate, Integer userId) {
       //to transform in local date
        LocalDateTime start = LocalDateTime.of(startDate, LocalTime.of(0,0,0));
        LocalDateTime end = LocalDateTime.of(startDate, LocalTime.of(23,59,59));
        return transactionRepository.findSumTransactionByDate(start,end,userId);
    }

    @Override
    public BigDecimal getAccountBalance(Integer userId) {
        return transactionRepository.findAccountBalance(userId);
    }

    @Override
    public BigDecimal highestTransfer(Integer userId) {
        return transactionRepository.findHighestAmountByTransactionType(userId, TransactionType.TRANSFERT);
    }

    @Override
    public BigDecimal highestDeposit(Integer userId) {
        return transactionRepository.findHighestAmountByTransactionType(userId, TransactionType.DEPOSIT);
    }
}
