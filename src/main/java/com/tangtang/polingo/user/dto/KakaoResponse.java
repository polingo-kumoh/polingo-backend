package com.tangtang.polingo.user.dto;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Slf4j
@JsonIgnoreProperties(ignoreUnknown = true)
public class KakaoResponse {
    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;

    @Getter
    @JsonProperty("id")
    private Long uid;

    public String getName() {
        return kakaoAccount.getProfile().getName();
    }

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class KakaoAccount {
        private KakaoProfile profile;
    }

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class KakaoProfile {
        @JsonProperty("nickname")
        private String name;
    }
}
