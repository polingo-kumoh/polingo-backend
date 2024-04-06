package com.tangtang.polingo.user.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.tangtang.polingo.user.dto.GoogleResponse;
import com.tangtang.polingo.user.property.GoogleProperties;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Component
@RequiredArgsConstructor
public class GoogleAuthClient {
    private final GoogleProperties googleProperties;
    private final RestTemplate restTemplate;

    public String requestKakaoAccessToken(String code) {
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

    public GoogleResponse requestUserInfo(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<?> httpEntity = new HttpEntity<>(headers);

        URI uri = UriComponentsBuilder.fromUriString(googleProperties.getUserInfoUri())
                .build()
                .toUri();

        return restTemplate.exchange(uri, HttpMethod.GET, httpEntity, GoogleResponse.class).getBody();
    }
}
