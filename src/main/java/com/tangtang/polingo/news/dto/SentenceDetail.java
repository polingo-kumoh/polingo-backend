package com.tangtang.polingo.news.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SentenceDetail {
    private Long sentenceId;
    private String originalText;
    private String grammar;
    private String translatedText;
}
