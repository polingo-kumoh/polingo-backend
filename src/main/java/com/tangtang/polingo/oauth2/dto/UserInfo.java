package com.tangtang.polingo.oauth2.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserInfo {
    private String id;
    private String name;
}
