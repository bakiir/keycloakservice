package com.example.keycloakservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String email;
    private String firstName;
    private String lastname;
    private String username;
}
