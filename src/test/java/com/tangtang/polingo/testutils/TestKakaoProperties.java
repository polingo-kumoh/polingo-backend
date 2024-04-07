package com.tangtang.polingo.testutils;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.security.oauth2.kakao")
@Getter
@Setter
public class TestKakaoProperties {


    public static final String KAKAO_AUTH_CODE = "kakao_auth_code";
    public static final String KAKAO_ACCESS_TOKEN = "kakao_access_token";
    public static final String KAKAO_REFRESH_TOKEN = "kakao_refresh_token";
    private final String responseType = "code";
    private String authorizationUri;
    private String clientId;
    private String redirectUri;
    private String tokenUri;
    private String userInfoUri;
    private String grantType;
    private String authServerHost;
    private String authorizationEndpoint;
    private String tokenEndpoint;
    private String userInfoEndpoint;

}
