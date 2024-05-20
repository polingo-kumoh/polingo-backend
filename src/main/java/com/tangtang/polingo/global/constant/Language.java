package com.tangtang.polingo.global.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Language {
    ENGLISH("en", "en-US", "US"),
    JAPANESE("ja", "ja-JP", "JP"),
    KOREAN("ko", "ko-KR", "KR");

    private final String textCode;
    private final String sttCode;
    private final String countryCode;

    public static Language fromCode(String code) {
        for (Language language : values()) {
            if (language.getTextCode().equalsIgnoreCase(code)) {
                return language;
            }
        }
        throw new IllegalArgumentException("유효하지 않은 언어 코드입니다. 올바른 코드를 사용해주세요.");
    }
}

