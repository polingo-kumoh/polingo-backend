package com.tangtang.polingo.wordset.dto.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WordSetDetailsResponse {
    private final Long id;
    private final String language;
    private final String name;
    private final List<WordDetail> words;

    @Getter
    @Builder
    public static class WordDetail {
        private final Long id;
        private final String word;
        private final String description;
    }
}
