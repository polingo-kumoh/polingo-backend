package com.tangtang.polingo.wordset.dto.wordset;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.Builder;

@Builder
public record WordSetSummaryResponse(long id, String name, boolean isDefault, long count) {
    @JsonGetter("is_default")
    public boolean isDefault() {
        return isDefault;
    }
}
