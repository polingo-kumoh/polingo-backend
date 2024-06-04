package com.tangtang.polingo.user.admin.dto;

public record AdminUserSignUpRequest(
        String username,
        String password,
        String nickname,
        String systemPassword
) {
}
