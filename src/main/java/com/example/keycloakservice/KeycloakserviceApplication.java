package com.example.keycloakservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@SpringBootApplication
@EnableMethodSecurity
public class KeycloakserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(KeycloakserviceApplication.class, args);
	}

}
