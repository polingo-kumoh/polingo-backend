package com.tangtang.polingo.situation.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "open-api")
public class OpenConfig {
    private String key;
    private String weatherUri;
}
