package com.paul.project.dto;

import com.paul.project.entity.Contact;
import com.paul.project.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
public class ContactDto {

    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String iban;
    private Integer userId;

    public static ContactDto fromEntity(Contact contact){
        return ContactDto.builder()
                .id(contact.getId())
                .firstname(contact.getFirstname())
                .lastname(contact.getLastname())
                .email(contact.getEmail())
                .iban(contact.getIban())
                .userId(contact.getUser().getId())
                .build();
    }

    public static Contact fromEntity(ContactDto contactDto){
        return Contact.builder()
                .id(contactDto.getId())
                .firstname(contactDto.getFirstname())
                .lastname(contactDto.getLastname())
                .email(contactDto.getEmail())
                .iban(contactDto.getIban())
                .user(User.builder()
                        .id(contactDto.getId())
                        .build())
                .build();
    }
}
