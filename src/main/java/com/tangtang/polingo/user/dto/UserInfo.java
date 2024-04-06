package com.tangtang.polingo.user.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserInfo {
    private String id;
    private String name;
    private String email;
}
