package com.tangtang.polingo.situation.service.holiday;

import com.tangtang.polingo.situation.vo.Holiday;
import jakarta.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
    private final Map<String, Holiday> holidaysByCountryAndDate = new HashMap<>();

    @Autowired
    public HolidayMap(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @PostConstruct
    public void init() {
        loadHolidaysForCountry("US");
        loadHolidaysForCountry("KR");
        loadHolidaysForCountry("JP");
        log.info("Holidays = {}", holidaysByCountryAndDate);
    }

    public Optional<Holiday> getHoliday(String countryCode, LocalDate date) {
        String key = countryCode + date.toString();
        return Optional.ofNullable(holidaysByCountryAndDate.get(key));
    }

    private void loadHolidaysForCountry(String countryCode) {
        int currentYear = LocalDate.now().getYear();
        List<Holiday> holidays = getPublicHolidays(currentYear, countryCode);
        holidays.addAll(getPublicHolidays(currentYear + 1, countryCode));
        addHolidays(countryCode, holidays);
    }

    private List<Holiday> getPublicHolidays(int year, String countryCode) {
        String url = String.format("https://date.nager.at/api/v3/PublicHolidays/%d/%s", year, countryCode);
        ResponseEntity<List<Holiday>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );
        return response.getBody();
    }

    private void addHolidays(String countryCode, List<Holiday> holidays) {
        for (Holiday holiday : holidays) {
            String key = countryCode + holiday.getDate();
            holidaysByCountryAndDate.put(key, holiday);
        }
    }
}
