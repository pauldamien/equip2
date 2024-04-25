package com.paul.project.repository;


import com.paul.project.entity.Transaction;
import com.paul.project.entity.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/*
@auteur paul Damien
 */
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

    List<Transaction> findAllByUserId(Integer userId);

    @Query(" select sum(t.amount) from  Transaction t where t.user.id = :userId")
    BigDecimal findAccountBalance(Integer userId);

    @Query("select max(abs(t.amount)) as amount from Transaction t where t.user.id =:user.id and t.transactiontype = :transactiontype")
    BigDecimal findHighestAmountByTransactionType(Integer userId, TransactionType transactionType);

    @Query("select  t.createDate , sum(t.amount) from Transaction t where t.user.id = :userId and t.createDate" + "between:start and :end" +"group by t.transactionDate")
  Map<LocalDate, BigDecimal> findSumTransactionByDate(LocalDateTime start, LocalDateTime end,Integer userId);
}
