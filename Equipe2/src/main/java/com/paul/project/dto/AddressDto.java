package com.paul.project.dto;

import com.paul.project.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
public class AddressDto {

    private  Integer id;
    private String street;
    private Integer houseNumber;
    private Integer zipCode;
    private String city;
    private String country;
    private Integer userId;

    public static AddressDto fromEntity(Address address){
        return AddressDto.builder()
                .id(address.getId())
                .street(address.getStreet())
                .zipCode(address.getZipCode())
                .city(address.getCity())
                .country(address.getCountry())
                .build();
    }

    public static Address toEntity(AddressDto addressDto){
        return Address.builder()
                .id(addressDto.getId())
                .street(addressDto.getStreet())
                .zipCode(addressDto.getZipCode())
                .city(addressDto.getCity())
                .country(addressDto.getCountry())
                .build();
    }
}
