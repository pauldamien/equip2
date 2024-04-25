package com.paul.project.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Account {
    @Id
    @GeneratedValue
    private Integer id;
    private String iban;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
