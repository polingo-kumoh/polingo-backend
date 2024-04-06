package com.tangtang.polingo.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KakaoAuthResponse {
    @JsonProperty("access_token")
    private String accessToken;
}
