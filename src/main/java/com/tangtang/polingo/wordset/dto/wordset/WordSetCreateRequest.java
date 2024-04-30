package com.tangtang.polingo.wordset.dto.wordset;

import lombok.Builder;

@Builder
public record WordSetCreateRequest(String name, String languageCode) {
}
