package com.example.keycloakservice.controller;

import com.example.keycloakservice.dto.UserCreateDto;
import com.example.keycloakservice.dto.UserDto;
import com.example.keycloakservice.dto.UserLoginDto;
import com.example.keycloakservice.dto.UserTokenDto;
import com.example.keycloakservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public UserDto createUser(@RequestBody UserCreateDto createDto){
        return userService.createUser(createDto);
    }

    @PostMapping("/token")
    public UserTokenDto signIn(@RequestBody UserLoginDto loginDto){
        return userService.authenticate(loginDto);
    }


}
