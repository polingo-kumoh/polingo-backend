package com.tangtang.polingo.oauth2.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.security.oauth2.google")
@Getter
@Setter
public class GoogleProperties {
    private String clientId;
    private String grantType;
    private String redirectUri;
    private String clientSecret;
    private String tokenUri;
    private String userInfoUri;
    private String scope;
    private String responseType;
    private String authorizationUri;
}
