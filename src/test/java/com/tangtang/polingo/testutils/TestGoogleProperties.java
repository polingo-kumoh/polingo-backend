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

    public static final String GOOGLE_ACCESS_TOKEN = "google_access_token";

    public static final String GOOGLE_REFRESH_TOKEN = "google_refresh_token";

    private String authorizationEndpoint;

    private String tokenEndpoint;

    private String userInfoEndpoint;

    private String clientId;

    private String clientSecret;

    private String redirectUri;

    private String responseType;

    private String scope;

    private String grantType;

}
