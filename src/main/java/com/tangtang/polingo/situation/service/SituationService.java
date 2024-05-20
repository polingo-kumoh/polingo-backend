package com.tangtang.polingo.situation.service;

import com.tangtang.polingo.global.constant.Language;
import com.tangtang.polingo.situation.dto.SituationResponse;
import com.tangtang.polingo.situation.entity.Category;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SituationService {

    private final List<SituationHandler> handlers;

    public SituationResponse getSituation(Category category, Language language, LocalDate date) {
        return handlers.stream()
                .filter(handler -> handler.supports(category))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 카테고리입니다."))
                .getSituation(language, date);
    }
}
