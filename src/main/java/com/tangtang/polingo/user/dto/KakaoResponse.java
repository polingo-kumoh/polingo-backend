package com.tangtang.polingo.user.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.tangtang.polingo.user.util.KakaoResponseDeserializer;
import lombok.Builder;

@Builder
@JsonDeserialize(using = KakaoResponseDeserializer.class)
public record KakaoResponse(String id, String name) {

    public UserInfo toUserInfo() {
        return UserInfo.builder()
                .id(this.id)
                .name(this.name)
                .build();
    }
}
