package com.tangtang.polingo.situation.dto;

import java.util.List;
import lombok.Data;

@Data
public class OpenWeatherMapResponse {
    private String name;
    private List<Weather> weather;
    private Main main;

    @Data
    public static class Weather {
        private String description;
    }

    @Data
    public static class Main {
        private double temp;
    }
}
