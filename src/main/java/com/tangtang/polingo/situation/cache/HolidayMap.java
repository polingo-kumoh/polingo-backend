package com.tangtang.polingo.situation.cache;

import com.tangtang.polingo.situation.vo.Holiday;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class HolidayMap {

    private final RestTemplate restTemplate;
    private final Map<String, Map<LocalDate, List<Holiday>>> holidaysByCountry = new HashMap<>();

    @Autowired
    public HolidayMap(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @PostConstruct
    public void init() {
        loadHolidaysForCountry("US");
        loadHolidaysForCountry("KR");
        loadHolidaysForCountry("JP");
        log.info("Holidays = {}", holidaysByCountry);
    }

    public List<Holiday> getHolidays(String countryCode, LocalDate date) {
        return holidaysByCountry.getOrDefault(countryCode, Collections.emptyMap())
                .getOrDefault(date, Collections.emptyList());
    }

    private void loadHolidaysForCountry(String countryCode) {
        int currentYear = LocalDate.now().getYear();
        List<Holiday> holidays = getPublicHolidays(currentYear, countryCode);
        holidays.addAll(getPublicHolidays(currentYear , countryCode));
        addHolidays(countryCode, holidays);
    }

    private List<Holiday> getPublicHolidays(int year, String countryCode) {
        String url = String.format("https://date.nager.at/api/v3/PublicHolidays/%d/%s", year, countryCode);
        ResponseEntity<List<Holiday>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );
        return response.getBody();
    }

    private void addHolidays(String countryCode, List<Holiday> holidays) {
        Map<LocalDate, List<Holiday>> holidayMap = holidaysByCountry.computeIfAbsent(countryCode, k -> new HashMap<>());

        for (Holiday holiday : holidays) {
            LocalDate date = LocalDate.parse(holiday.getDate());
            holidayMap.computeIfAbsent(date, k -> new ArrayList<>()).add(holiday);
        }
    }
}
