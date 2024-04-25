package com.paul.project.dto;

import com.paul.project.entity.Transaction;
import com.paul.project.entity.TransactionType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
public class TransactionDto {


    private  Integer id;
    @Positive
    @Max(value = 1000000000)
    @Min(value = 10)
    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    private String destinationIban;
    private LocalDate transactionDate;

    private Integer UserId;


    public static TransactionDto fromEntity(Transaction transaction){
        return TransactionDto.builder()
                .id(transaction.getId())
                .amount(transaction.getAmount())
                .type(transaction.getType())
                .destinationIban(transaction.getDestinationIban())
                .transactionDate(transaction.getTransactionDate())
                .build();
    }

    public static Transaction toEntity(TransactionDto transactionDto){
        return Transaction.builder()
                .id(transactionDto.getId())
                .amount(transactionDto.getAmount())
                .type(transactionDto.getType())
                .destinationIban(transactionDto.getDestinationIban())
                .transactionDate(transactionDto.getTransactionDate())
                .build();
    }
}
