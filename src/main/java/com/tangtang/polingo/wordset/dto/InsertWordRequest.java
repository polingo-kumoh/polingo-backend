package com.tangtang.polingo.wordset.dto.dto;

import lombok.Builder;

@Builder
public record InsertWordRequest(String word, String description) {
}
