package com.paul.project.dto;


import com.paul.project.entity.User;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
public class UserDto {

    private Integer id;
    @NotNull
    @NotEmpty
    @NotBlank
    private String firstname;

    @NotNull
    @NotEmpty
    @NotBlank
    private String lastname;

    @NotNull
    @NotEmpty
    @NotBlank
    @Email
    private String email;
    private String  iban;

    @NotNull
    @NotEmpty
    @NotBlank
    @Size(min = 8 , max = 16)
    private String password;
    private String active;


    public static UserDto fromEntity(User user){
        return UserDto.builder()
                .id(user.getId())
                .firstname(user.getFirstname())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .password(user.getPassword())
                .active(user.getActive())
                .build();
    }

    public static User toEntity(UserDto userDto){
        return User.builder()
                .id(userDto.getId())
                .firstname(userDto.getFirstname())
                .lastname(userDto.getLastname())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .active(userDto.getActive())
                .build();
    }
}
