package com.tangtang.polingo.situation.service.weather;

import com.tangtang.polingo.situation.config.OpenWeatherMapConfig;
import com.tangtang.polingo.situation.dto.externel.OpenWeatherMapResponse;
import com.tangtang.polingo.situation.vo.Weather;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class WeatherClient {

    private final RestTemplate restTemplate;
    private final OpenWeatherMapConfig config;

    @Cacheable(value = "weatherCache", key = "#lon + #lat", unless = "#result == null")
    public Weather getWeatherInfo(String lon, String lat) {
        String url = String.format("%s?lat=%s&lon=%s&appid=%s&units=metric", config.getApi(), lat, lon,
                config.getKey());
        OpenWeatherMapResponse response = restTemplate.getForObject(url, OpenWeatherMapResponse.class);

        if (response != null) {
            return new Weather(
                    response.getName(),
                    response.getWeather().get(0).getDescription(),
                    response.getMain().getTemp()
            );
        } else {
            throw new IllegalArgumentException("Invalid response from OpenWeatherMap API");
        }
    }
}
