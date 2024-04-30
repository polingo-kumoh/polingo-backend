package com.tangtang.polingo.wordset.dto;

import lombok.Builder;

@Builder
public record WordMeaningResponse(String word, String description) {
}
