package com.tangtang.polingo.oauth2.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.tangtang.polingo.global.constant.LoginType;
import com.tangtang.polingo.oauth2.dto.KakaoResponse;
import com.tangtang.polingo.oauth2.dto.UserInfo;
import com.tangtang.polingo.oauth2.property.KakaoProperties;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
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
public class KakaoOAuth2ServiceImpl implements OAuth2Service {
    private final KakaoProperties kakaoProperties;
    private final RestTemplate restTemplate;

    @Override
    public ResponseEntity<Void> redirectAuthorizeURI() {
        String redirectUrl = UriComponentsBuilder.fromUriString(kakaoProperties.getAuthorizationUri())
                .queryParam("client_id", kakaoProperties.getClientId())
                .queryParam("redirect_uri", kakaoProperties.getRedirectUri())
                .queryParam("response_type", "code")
                .build()
                .encode(StandardCharsets.UTF_8)
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
        body.add("grant_type", kakaoProperties.getGrantType());
        body.add("client_id", kakaoProperties.getClientId());

        HttpEntity<?> httpEntity = new HttpEntity<>(body, httpHeaders);

        JsonNode response = restTemplate.postForObject(kakaoProperties.getTokenUri(), httpEntity, JsonNode.class);
        return response != null ? response.path("access_token").asText() : null;
    }

    @Override
    public UserInfo requestUserInfo(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        MultiValueMap<String, List<String>> body = new LinkedMultiValueMap<>();
        body.add("property_keys", kakaoProperties.getPropertyKeys());

        HttpEntity<?> httpEntity = new HttpEntity<>(body, headers);

        KakaoResponse response = restTemplate.postForObject(kakaoProperties.getUserInfoUri(), httpEntity,
                KakaoResponse.class);

        return Objects.requireNonNull(response).toUserInfo();
    }

    @Override
    public LoginType getLoginType() {
        return LoginType.KAKAO;
    }
}
