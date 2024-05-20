package com.tangtang.polingo.situation.service;

import com.tangtang.polingo.global.constant.Language;
import com.tangtang.polingo.situation.dto.SituationResponse;
import com.tangtang.polingo.situation.entity.Category;
import java.time.LocalDate;

public interface SituationHandler {
    boolean supports(Category category);
    SituationResponse getSituation(Language language, LocalDate date);
}
