package com.tangtang.polingo.security.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "spring.jwt")
public class JwtProperties {
    private String secretKey;
    private long expirationHours;
    private String issuer;
}
