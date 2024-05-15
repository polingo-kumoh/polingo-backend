package com.tangtang.polingo.situation.weather.service;

import com.tangtang.polingo.situation.weather.config.WeatherConfig;
import com.tangtang.polingo.situation.weather.util.BaseTimeCalculator;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Component
@RequiredArgsConstructor
public class WeatherClient {
    private final RestTemplate restTemplate;
    private final WeatherConfig weatherConfig;

    public String getWeather(int nx, int ny) {
        LocalDate currentDate = LocalDate.now();
        LocalDateTime currentDateTime = LocalDateTime.now();

        String baseDate = currentDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String baseTime = BaseTimeCalculator.execute(currentDateTime);

        String uri = UriComponentsBuilder.fromHttpUrl(weatherConfig.getUri())
                .queryParam("serviceKey", weatherConfig.getKey())
                .queryParam("pageNo", 1)
                .queryParam("numOfRows", 100)
                .queryParam("dataType", "json")
                .queryParam("base_date", baseDate)
                .queryParam("base_time", baseTime)
                .queryParam("nx", nx)
                .queryParam("ny", ny)
                .toUriString();

        return restTemplate.getForObject(uri, String.class);
    }
}
