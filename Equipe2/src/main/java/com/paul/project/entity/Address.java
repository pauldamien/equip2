package com.paul.project.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Address {

    @Id
    @GeneratedValue
    private  Integer id;
    private String street;
    private Integer houseNumber;
    private Integer zipCode;
    private String city;
    private String country;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
