package com.tangtang.polingo.testutils;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.security.oauth2.google")
@Getter
@Setter
public class TestGoogleProperties{

    public static final String GOOGLE_AUTH_CODE = "google_auth_code";

    private String authServerHost;

    private String authorizationEndpoint;

    private String tokenEndpoint;

    private String userInfoEndpoint;

    private String clientId;

    private String redirectUri;

    private String responseType;

    private String scope;


}
