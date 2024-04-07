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

    private final String responseType = "code";

}
