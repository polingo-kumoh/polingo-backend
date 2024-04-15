package com.tangtang.polingo.translate.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tangtang.polingo.global.constant.Language;

public record PlainTextTranslateRequest(Language sourceType, String text) {
    @JsonIgnore
    public String getSource() {
        return sourceType.getCode();
    }
}
