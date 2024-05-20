package com.tangtang.polingo.situation.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Weather {
    private String cityName;
    private String description;
    private double temperature;
}
