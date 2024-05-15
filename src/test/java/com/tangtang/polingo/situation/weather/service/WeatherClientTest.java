package com.tangtang.polingo.situation.weather.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.tangtang.polingo.situation.config.WeatherConfig;
import com.tangtang.polingo.situation.service.weather.WeatherClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

    @Test
    public void testGetWeather() {
        // When
        String response = weatherClient.getWeather(86, 95);

        log.info("response = {}", response);
        // Then
        assertNotNull(response);
    }
}
