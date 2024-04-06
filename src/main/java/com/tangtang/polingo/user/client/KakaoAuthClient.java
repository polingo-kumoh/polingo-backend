package com.tangtang.polingo.user.client;

import com.tangtang.polingo.user.dto.KakaoAuthResponse;
import com.tangtang.polingo.user.dto.KakaoRequest;
import com.tangtang.polingo.user.dto.KakaoResponse;
import com.tangtang.polingo.user.property.KakaoProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
@Slf4j
public class KakaoAuthClient {

    private final KakaoProperties kakaoProperties;
    private final RestTemplate restTemplate;

    public String requestKakaoAccessToken(KakaoRequest request) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = request.makeBody();
        body.add("grant_type", kakaoProperties.getGrantType());
        body.add("client_id", kakaoProperties.getClientId());

        HttpEntity<?> httpEntity = new HttpEntity<>(body, httpHeaders);

        KakaoAuthResponse response = restTemplate.postForObject(kakaoProperties.getTokenUri(), httpEntity, KakaoAuthResponse.class);

        return response.getAccessToken();
    }

    public KakaoResponse requestKakaoUserInfo(String accessToken) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.set("Authorization", "Bearer " + accessToken);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("property_keys", "[\"kakao_account.profile\"]");

        HttpEntity<?> httpEntity = new HttpEntity<>(body, httpHeaders);

        return restTemplate.postForObject(kakaoProperties.getUserInfoUri(), httpEntity, KakaoResponse.class);
    }
}
