package com.tangtang.polingo.situation.controller;

import com.tangtang.polingo.global.constant.Language;
import com.tangtang.polingo.situation.dto.DateResponse;
import com.tangtang.polingo.situation.dto.SituationResponse;
import com.tangtang.polingo.situation.dto.WeatherResponse;
import com.tangtang.polingo.situation.service.SituationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "상황별 예문 관련 API", description = "예문 API를 제공합니다.")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/situation")
public class SituationController {
    private final SituationService situationService;

    @Operation(summary = "날짜별 상황별 예문 API")
    @GetMapping("/date")
    public ResponseEntity<DateResponse> getDateSituation(
            @RequestParam Language lang,
            @RequestParam LocalDate date) {
        DateResponse response = situationService.getDateSituation(lang, date);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "날씨별 상황별 예문 API")
    @GetMapping("/weather")
    public ResponseEntity<WeatherResponse> getWeatherSituation(
            @RequestParam Language lang,
            @RequestParam String lon,
            @RequestParam String lat) {
        WeatherResponse response = situationService.getWeatherSituation(lang, lon, lat);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "요일 상황별 예문 API")
    @GetMapping("/week")
    public ResponseEntity<SituationResponse> getWeekSituation(
            @RequestParam Language lang) {
        SituationResponse response = situationService.getWeekSituation(lang);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "시간 상황별 예문 API")
    @GetMapping("/time")
    public ResponseEntity<SituationResponse> getTimeSituation(
            @RequestParam Language lang) {
        SituationResponse response = situationService.getTimeSituation(lang);
        return ResponseEntity.ok(response);
    }
}
