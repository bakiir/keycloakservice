package com.example.keycloakservice.client;

import com.example.keycloakservice.dto.UserCreateDto;
import com.example.keycloakservice.dto.UserLoginDto;
import com.example.keycloakservice.dto.UserTokenDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CertificateRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.core.Response;
import java.sql.Struct;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class KeyCloakClient {

    private final Keycloak keyCloak;
    private final RestTemplate restTemplate;

    @Value("${keycloak.url}")
    private String keyCloakUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.grant_type}")
    private String grantType;

    @Value("${keycloak.client_id}")
    private String clientId;

    @Value("${keycloak.client_secret}")
    private String clientSecret;

    public UserTokenDto signIn(UserLoginDto loginDto) {
        String tokenEndPoint = keyCloakUrl + "/realms/" + realm + "/protocol/openid-connect/token";

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", grantType);
        formData.add("client_secret", clientSecret);
        formData.add("client_id", clientId);
        formData.add("username", loginDto.getUsername());
        formData.add("password", loginDto.getPassword());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");

        ResponseEntity<Map> response = restTemplate.postForEntity(
                tokenEndPoint, new HttpEntity<>(formData, headers), Map.class);

        Map<String, Object> responceBody = response.getBody();
        if (!response.getStatusCode().is2xxSuccessful() || responceBody == null) {
            log.error("Error in signing in with username: {}",
                    loginDto.getUsername());

            throw new RuntimeException("Error in signing in: " +
                    loginDto.getUsername());

        }

        UserTokenDto tokenDto = new UserTokenDto();
        tokenDto.setAccessToken(responceBody.get("access_token").toString());
        tokenDto.setRefreshToken(responceBody.get("refresh_token").toString());
        return tokenDto;
    }

    public UserRepresentation createUser(UserCreateDto dto) {
        UserRepresentation user = new UserRepresentation();
        user.setEmail(dto.getEmail());
        user.setEmailVerified(true);
        user.setUsername(dto.getUsername());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastname());
        user.setEnabled(true);

        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(dto.getPassword());
        credential.setTemporary(false);

        user.setCredentials(List.of(credential));

        Response response = keyCloak.realm(realm)
                .users().create(user);

        if (response.getStatus() != HttpStatus.CREATED.value()) {
            log.error("Error on creating new user: {}", response.getStatus());
            throw new RuntimeException("Failed to create user in KeyCloak:" + response.getStatusInfo());
        }

        List<UserRepresentation> userList = keyCloak.realm(realm).users().search(dto.getUsername());

        return userList.get(0);
    }
}
