package com.tangtang.polingo.user.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.tangtang.polingo.user.dto.KakaoResponse;
import com.tangtang.polingo.user.dto.UserInfo;
import com.tangtang.polingo.user.property.KakaoProperties;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
@RequiredArgsConstructor
public class KakaoAuthClient {
    private final KakaoProperties kakaoProperties;
    private final RestTemplate restTemplate;

    public String requestKakaoAccessToken(String code) {
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
}
