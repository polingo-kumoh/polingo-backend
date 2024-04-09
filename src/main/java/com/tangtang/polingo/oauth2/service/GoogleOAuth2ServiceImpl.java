package com.tangtang.polingo.oauth2.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.tangtang.polingo.oauth2.dto.GoogleResponse;
import com.tangtang.polingo.oauth2.dto.UserInfo;
import com.tangtang.polingo.oauth2.property.GoogleProperties;
import com.tangtang.polingo.user.constant.LoginType;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Component
@RequiredArgsConstructor
public class GoogleOAuth2ServiceImpl implements OAuth2Service {
    private final GoogleProperties googleProperties;
    private final RestTemplate restTemplate;

    @Override
    public ResponseEntity<Void> redirectAuthorizeURI() {
        String redirectUrl = UriComponentsBuilder.fromUriString(googleProperties.getAuthorizationUri())
                .queryParam("client_id", googleProperties.getClientId())
                .queryParam("redirect_uri", googleProperties.getRedirectUri())
                .queryParam("response_type", googleProperties.getResponseType())
                .queryParam("scope", googleProperties.getScope())
                .encode(StandardCharsets.UTF_8)
                .build()
                .toUriString();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(redirectUrl));

        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    @Override
    public String requestAccessToken(String code) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("code", code);
        body.add("client_id", googleProperties.getClientId());
        body.add("client_secret", googleProperties.getClientSecret());
        body.add("redirect_uri", googleProperties.getRedirectUri());
        body.add("grant_type", googleProperties.getGrantType());

        HttpEntity<?> httpEntity = new HttpEntity<>(body, httpHeaders);

        JsonNode response = restTemplate.postForObject(googleProperties.getTokenUri(), httpEntity, JsonNode.class);

        return response != null ? response.path("access_token").asText() : null;
    }

    @Override
    public UserInfo requestUserInfo(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<?> httpEntity = new HttpEntity<>(headers);

        URI uri = UriComponentsBuilder.fromUriString(googleProperties.getUserInfoUri())
                .build()
                .toUri();

        GoogleResponse response = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, GoogleResponse.class)
                .getBody();
        return Objects.requireNonNull(response).toUserInfo();
    }

    @Override
    public LoginType getLoginType() {
        return LoginType.GOOGLE;
    }
}
