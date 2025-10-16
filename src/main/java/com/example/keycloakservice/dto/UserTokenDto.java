package com.example.keycloakservice.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserTokenDto {
    private String accessToken;
    private String refreshToken;

}
