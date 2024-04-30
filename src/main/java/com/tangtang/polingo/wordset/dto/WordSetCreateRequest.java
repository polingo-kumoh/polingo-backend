package com.tangtang.polingo.wordset.dto;

import lombok.Builder;

@Builder
public record WordSetCreateRequest(String name, String languageCode) {
}
