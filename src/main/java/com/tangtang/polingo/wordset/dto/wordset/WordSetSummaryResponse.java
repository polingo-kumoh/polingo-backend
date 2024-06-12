package com.tangtang.polingo.wordset.dto.wordset;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.tangtang.polingo.global.constant.Language;
import lombok.Builder;

@Builder
public record WordSetSummaryResponse(long id, String name, Language language, boolean isDefault, long count) {
    @JsonGetter("is_default")
    public boolean isDefault() {
        return isDefault;
    }
}
