package com.example.keycloakservice.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserLoginDto {
    private String username;
    private String password;
}
