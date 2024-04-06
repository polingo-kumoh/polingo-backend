package com.tangtang.polingo.user.client;

import com.tangtang.polingo.user.dto.KakaoRequest;
import com.tangtang.polingo.user.dto.KakaoResponse;
import com.tangtang.polingo.user.property.KakaoProperties;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class KakaoClient {
    private final KakaoProperties kakaoProperties;
    private final KakaoAuthClient kakaoAuthClient;

    public ResponseEntity<Void> redirectToKakaoAuth() {
        String redirectUrl = createRedirectUrl();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(redirectUrl));

        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    public KakaoResponse handleCallback(KakaoRequest req) {
        String accessToken = kakaoAuthClient.requestKakaoAccessToken(req);
        return kakaoAuthClient.requestKakaoUserInfo(accessToken);
    }

    private String createRedirectUrl() {
        return UriComponentsBuilder.fromUriString(kakaoProperties.getAuthorizationUri())
                .queryParam("client_id", kakaoProperties.getClientId())
                .queryParam("redirect_uri", kakaoProperties.getRedirectUri())
                .queryParam("response_type", "code")
                .build().encode(StandardCharsets.UTF_8).toUriString();
    }
}
