package com.tangtang.polingo.user.service;

import com.tangtang.polingo.global.constant.LoginType;
import com.tangtang.polingo.user.dto.UserInfo;
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
public class KakaoOAuth2Service implements OAuth2Service {
    private final KakaoProperties kakaoProperties;
    private final KakaoAuthClient kakaoAuthClient;

    @Override
    public ResponseEntity<Void> redirectAuthorizeURI() {
        String redirectUrl = createRedirectUrl();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(redirectUrl));

        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    @Override
    public UserInfo handleCallback(String code) {
        String accessToken = kakaoAuthClient.requestKakaoAccessToken(code);
        return kakaoAuthClient.requestUserInfo(accessToken);
    }

    @Override
    public LoginType getLoginType() {
        return LoginType.KAKAO;
    }

    private String createRedirectUrl() {
        return UriComponentsBuilder.fromUriString(kakaoProperties.getAuthorizationUri())
                .queryParam("client_id", kakaoProperties.getClientId())
                .queryParam("redirect_uri", kakaoProperties.getRedirectUri())
                .queryParam("response_type", "code")
                .build()
                .encode(StandardCharsets.UTF_8)
                .toUriString();
    }
}
