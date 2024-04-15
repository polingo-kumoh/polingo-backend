package com.tangtang.polingo.global.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Language {
    ENGLISH("en"), JAPAN("ja"), KOREA("ko");

    private final String code;

    public static Language fromCode(String code) {
        for (Language language : Language.values()) {
            if (language.getCode().equalsIgnoreCase(code)) {
                return language;
            }
        }
        throw new IllegalArgumentException("유효하지 않은 언어 코드입니다. 올바른 코드를 사용해주세요.");
    }
}
