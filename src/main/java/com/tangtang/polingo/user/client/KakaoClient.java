package com.tangtang.polingo.user.client;

import com.tangtang.polingo.user.property.KakaoProperties;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KakaoClient {
    private final KakaoProperties kakaoProperties;

    public ResponseEntity<Void> redirectToKakaoAuth() {
        String redirectUrl = createRedirectUrl();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(redirectUrl));

        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    private String createRedirectUrl() {
        String authorizationUri = kakaoProperties.getAuthorizationUri();
        String clientId = encodeQueryParam(kakaoProperties.getClientId());
        String redirectUri = encodeQueryParam(kakaoProperties.getRedirectUri());

        return String.format(
                "%s?client_id=%s&redirect_uri=%s&response_type=%s",
                authorizationUri, clientId, redirectUri, "code");
    }

    private String encodeQueryParam(String param) {
        return URLEncoder.encode(param, StandardCharsets.UTF_8);
    }
}
