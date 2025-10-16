package com.example.keycloakservice.service;

import com.example.keycloakservice.client.KeyCloakClient;
import com.example.keycloakservice.dto.UserCreateDto;
import com.example.keycloakservice.dto.UserDto;
import com.example.keycloakservice.dto.UserLoginDto;
import com.example.keycloakservice.dto.UserTokenDto;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final KeyCloakClient keyCloakClient;

    public UserDto createUser(UserCreateDto userCreateDto) {
        UserRepresentation userRepresentation = keyCloakClient.createUser(userCreateDto);
        UserDto userDto = new UserDto();
        userDto.setEmail(userRepresentation.getEmail());
        userDto.setUsername(userRepresentation.getUsername());
        userDto.setFirstName(userRepresentation.getFirstName());
        userDto.setLastname(userRepresentation.getFirstName());

        return userDto;
    }

    public UserTokenDto authenticate(UserLoginDto loginDto) {
        return keyCloakClient.signIn(loginDto);
    }
}
