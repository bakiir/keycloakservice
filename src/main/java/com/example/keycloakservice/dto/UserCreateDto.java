package com.example.keycloakservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDto {

    private String email;
    private String firstName;
    private String lastname;
    private String username;
    private String password;


}
