package com.example.keycloakservice.config;

import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class KeyCloakConfig {

    @Value("${keycloak.url}")
    private String keyCloakUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.client_id}")
    private String clientId;

    @Value("${keycloak.client_secret}")
    private String clientSecret;


    @Value("${keycloak.username}")
    private String userName;

    @Value("${keycloak.password}")
    private String password;

    @Value("${keycloak.grant_type}")
    private String grantType;

    @Bean
    public Keycloak keycloak(){
        log.info("Init keycloak:realm = {}", realm);

        return KeycloakBuilder.builder()
                .serverUrl(keyCloakUrl)
                .realm(realm)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .username(userName)
                .password(password)
                .grantType(grantType).build();
    }



}
