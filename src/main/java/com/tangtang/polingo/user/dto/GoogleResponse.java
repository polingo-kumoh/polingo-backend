package com.tangtang.polingo.user.dto;

public record GoogleResponse(String id, String name, String email) {

    public UserInfo toUserInfo() {
        return UserInfo.builder()
                .id(this.id)
                .name(this.name)
                .build();
    }
}
