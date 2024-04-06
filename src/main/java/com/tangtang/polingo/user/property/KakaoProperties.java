package com.tangtang.polingo.user.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "spring.security.oauth2.kakao")
public class KakaoProperties {
    private String redirectUri;
    private String tokenUri;
    private String userInfoUri;
    private String clientId;
    private String authorizationUri;
    private String grantType;
}
