package com.tangtang.polingo.situation.controller;

import com.tangtang.polingo.global.constant.Language;
import com.tangtang.polingo.situation.dto.SituationResponse;
import com.tangtang.polingo.situation.entity.Category;
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

@Tag(name = "상황별 예문 관련 API", description = "예문 API를 제공합니다. ")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/situation")
public class SituationController {
    private final SituationService situationService;

    @Operation(summary = "상황별 예문 API")
    @GetMapping
    public ResponseEntity<SituationResponse> getSituation(
            @RequestParam Category category,
            @RequestParam Language lang,
            @RequestParam LocalDate date) {
        SituationResponse response = situationService.getSituation(category, lang, date);
        return ResponseEntity.ok(response);
    }
}

