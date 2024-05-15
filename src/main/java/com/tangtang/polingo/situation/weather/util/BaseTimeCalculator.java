package com.tangtang.polingo.situation.weather.util;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class BaseTimeCalculator {

    private static final List<String> BASE_TIMES = Arrays.asList(
            "0030", "0130", "0230", "0330", "0430", "0530",
            "0630", "0730", "0830", "0930", "1030", "1130",
            "1230", "1330", "1430", "1530", "1630", "1730",
            "1830", "1930", "2030", "2130", "2230", "2330"
    );

    public static String execute(LocalDateTime currentTime) {
        int currentHour = currentTime.getHour();
        int currentMinute = currentTime.getMinute();

        for (String baseTime : BASE_TIMES) {
            int baseHour = Integer.parseInt(baseTime.substring(0, 2));
            int baseMinute = Integer.parseInt(baseTime.substring(2, 4));

            if (currentHour < baseHour || (currentHour == baseHour && currentMinute <= baseMinute)) {
                return baseTime;
            }
        }

        return BASE_TIMES.get(BASE_TIMES.size() - 1);
    }
}

