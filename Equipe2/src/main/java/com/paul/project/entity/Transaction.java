package com.paul.project.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;


/*
@auteur paul Damien
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Transaction {

    @Id
    @GeneratedValue
    private  Integer id;

    private BigDecimal amount;
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    private String destinationIban;

    private LocalDate transactionDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
