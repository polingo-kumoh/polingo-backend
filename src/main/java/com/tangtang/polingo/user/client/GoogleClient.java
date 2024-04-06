package com.tangtang.polingo.user.client;

import com.tangtang.polingo.user.property.GoogleProperties;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class GoogleClient {
    private final GoogleProperties googleProperties;

    public ResponseEntity<Void> redirectToGoogleAuth() {
        String redirectUrl = createRedirectUrl();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(redirectUrl));

        return new ResponseEntity<>(headers, HttpStatus.FOUND);
    }

    private String createRedirectUrl() {
        return UriComponentsBuilder.fromUriString(googleProperties.getAuthorizationUri())
                .queryParam("client_id", googleProperties.getClientId())
                .queryParam("redirect_uri", googleProperties.getRedirectUri())
                .queryParam("response_type", googleProperties.getResponseType())
                .queryParam("scope", googleProperties.getScope())
                .build().toUriString();
    }
}

