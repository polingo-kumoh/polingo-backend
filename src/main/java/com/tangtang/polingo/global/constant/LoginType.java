package com.tangtang.polingo.global.constant;

import lombok.Getter;

@Getter
public enum LoginType {
    KAKAO("kakao"),
    GOOGLE("google");

    private final String provider;

    LoginType(String provider) {
        this.provider = provider;
    }

    public static LoginType fromProvider(String provider) {
        for (LoginType type : values()) {
            if (type.getProvider().equalsIgnoreCase(provider)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown provider: " + provider);
    }

}
