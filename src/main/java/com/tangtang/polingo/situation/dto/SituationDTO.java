package com.tangtang.polingo.situation.dto;

import lombok.Builder;

@Builder
public record SituationDTO(String sentence, String translate, String imageUrl) {
}

