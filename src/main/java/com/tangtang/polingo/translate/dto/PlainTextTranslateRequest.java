package com.tangtang.polingo.translate.dto;


import com.tangtang.polingo.global.constant.Language;

public record PlainTextTranslateRequest(String sourceType, String text){
    public String sourceType() {
        return Language.fromCode(sourceType).getCode();
    }
}
