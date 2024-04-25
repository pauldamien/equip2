package com.paul.project.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Role {

    @Id
    @GeneratedValue
    private Integer id;


    private String name;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
