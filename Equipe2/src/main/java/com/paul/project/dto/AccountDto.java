package com.paul.project.dto;

import com.paul.project.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
public class AccountDto {

    private Integer id;
    private String iban;
    private  UserDto user;

    public static   AccountDto fromEntity(Account account){
        return AccountDto.builder()
                .id(account.getId())
                .iban(account.getIban())
                .build();
    }

    public static Account toEntity(AccountDto accountDto){
        return Account.builder()
                .id(accountDto.getId())
                .iban(accountDto.getIban())
                .build();
    }

}
