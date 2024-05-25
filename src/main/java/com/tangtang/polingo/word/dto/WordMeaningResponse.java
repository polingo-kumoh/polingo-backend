package com.tangtang.polingo.word.dto;

import lombok.Builder;

@Builder
public record WordMeaningResponse(String word, String translate, String description) {
}
