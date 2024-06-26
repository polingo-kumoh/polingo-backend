package com.tangtang.polingo.oauth2.dto;

public record GoogleResponse(String id, String name) {

    public UserInfo toUserInfo() {
        return UserInfo.builder()
                .id(this.id)
                .name(this.name)
                .build();
    }
}
