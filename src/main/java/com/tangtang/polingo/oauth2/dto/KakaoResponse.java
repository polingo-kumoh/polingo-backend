package com.tangtang.polingo.oauth2.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.tangtang.polingo.oauth2.util.KakaoResponseDeserializer;
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
