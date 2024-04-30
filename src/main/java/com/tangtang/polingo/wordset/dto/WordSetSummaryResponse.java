package com.tangtang.polingo.wordset.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.Builder;

@Builder
public record WordSetSummaryResponse(long id, String name, boolean isDefault) {
    @JsonGetter("is_default")
    public boolean isScraped() {
        return isDefault;
    }
}
