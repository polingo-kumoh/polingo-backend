package com.tangtang.polingo.situation.service;

import com.tangtang.polingo.global.constant.Language;
import com.tangtang.polingo.situation.dto.DateResponse;
import com.tangtang.polingo.situation.dto.PlaceSituationResponse;
import com.tangtang.polingo.situation.dto.SituationDTO;
import com.tangtang.polingo.situation.dto.SituationResponse;
import com.tangtang.polingo.situation.dto.WeatherResponse;
import com.tangtang.polingo.situation.service.holiday.HolidayHandler;
import com.tangtang.polingo.situation.service.weather.WeatherHandler;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SituationService {
    private final HolidayHandler holidayHandler;
    private final WeatherHandler weatherHandler;
    private final WeekHandler weekHandler;
    private final TimeHandler timeHandler;
    private final PlaceHandler placeHandler;

    public DateResponse getDateSituation(Language language, LocalDate date) {
        return holidayHandler.getSituation(language, date);
    }

    public WeatherResponse getWeatherSituation(Language language, String lon, String lat) {
        return weatherHandler.getSituation(language, lon, lat);
    }

    public SituationResponse getWeekSituation(Language language) {
        List<SituationDTO> situations = weekHandler.getSituation(language);
        return new SituationResponse(situations);
    }

    public SituationResponse getTimeSituation(Language language) {
        List<SituationDTO> situations = timeHandler.getSituation(language);
        return new SituationResponse(situations);
    }

    public List<PlaceSituationResponse> getPlaceSituations(Language language) {
        return placeHandler.getSituation(language);
    }
}
