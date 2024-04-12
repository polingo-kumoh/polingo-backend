package com.tangtang.polingo.translate.dto;


import lombok.Builder;

@Builder
public record TranslateResponse(String originalText, String translatedText){
}
