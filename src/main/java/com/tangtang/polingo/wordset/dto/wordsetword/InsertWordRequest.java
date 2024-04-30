package com.tangtang.polingo.wordset.dto.wordsetword;

import lombok.Builder;

@Builder
public record InsertWordRequest(String word, String description) {
}
