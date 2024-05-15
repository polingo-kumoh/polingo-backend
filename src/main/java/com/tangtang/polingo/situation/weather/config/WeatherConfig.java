package com.tangtang.polingo.situation.weather.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "open-api.weather")
public class WeatherConfig {
    private String key;
    private String uri;
}
