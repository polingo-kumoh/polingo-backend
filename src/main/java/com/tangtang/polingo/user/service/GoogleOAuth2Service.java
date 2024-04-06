package com.tangtang.polingo.user.service;

import com.tangtang.polingo.global.constant.LoginType;
import com.tangtang.polingo.user.dto.UserInfo;
import com.tangtang.polingo.user.property.GoogleProperties;
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
public class GoogleOAuth2Service implements OAuth2Service {
    private final GoogleProperties googleProperties;
    private final GoogleAuthClient googleAuthClient;

    @Override
    public ResponseEntity<Void> redirectAuthorizeURI() {
        String redirectUrl = createRedirectUrl();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(redirectUrl));

        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    @Override
    public UserInfo handleCallback(String code) {
        String accessToken = googleAuthClient.requestKakaoAccessToken(code);
        return googleAuthClient.requestUserInfo(accessToken);
    }

    @Override
    public LoginType getLoginType() {
        return LoginType.GOOGLE;
    }

    private String createRedirectUrl() {
        return UriComponentsBuilder.fromUriString(googleProperties.getAuthorizationUri())
                .queryParam("client_id", googleProperties.getClientId())
                .queryParam("redirect_uri", googleProperties.getRedirectUri())
                .queryParam("response_type", googleProperties.getResponseType())
                .queryParam("scope", googleProperties.getScope())
                .encode(StandardCharsets.UTF_8)
                .build()
                .toUriString();
    }
}

