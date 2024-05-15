package com.tangtang.polingo.situation.weather.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.tangtang.polingo.situation.weather.config.WeatherConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;


@ActiveProfiles("dev")
@Slf4j
@SpringBootTest
@ContextConfiguration(classes = {WeatherClient.class, WeatherConfig.class, RestTemplate.class})
public class WeatherClientTest {

    @Autowired
    private WeatherClient weatherClient;
    @Autowired
    private WeatherConfig weatherConfig;

    @Test
    public void testGetWeather() {
        log.info("expr = {}", weatherConfig.getKey());
        // When
        String response = weatherClient.getWeather(86, 95);

        // Then
        assertNotNull(response);
    }
}
