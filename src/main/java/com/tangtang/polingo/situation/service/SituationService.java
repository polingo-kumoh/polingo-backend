package com.tangtang.polingo.situation.service;

import com.tangtang.polingo.global.constant.Language;
import com.tangtang.polingo.situation.dto.DateResponse;
import com.tangtang.polingo.situation.dto.SituationDTO;
import com.tangtang.polingo.situation.dto.WeatherResponse;
import com.tangtang.polingo.situation.dto.WeekResponse;
import com.tangtang.polingo.situation.service.holiday.HolidayHandler;
import com.tangtang.polingo.situation.service.weather.WeatherHandler;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SituationService {
    private final HolidayHandler holidayHandler;
    private final WeatherHandler weatherHandler;
    private final WeekHandler weekHandler;

    public DateResponse getDateSituation(Language language, LocalDate date) {
        return holidayHandler.getSituation(language, date);
    }

    public WeatherResponse getWeatherSituation(Language language, String lon, String lat) {
        return weatherHandler.getSituation(language, lon, lat);
    }

    public WeekResponse getWeekSituation(Language language) {
        List<SituationDTO> situations = weekHandler.getSituation(language);
        return new WeekResponse(situations);
    }
}
