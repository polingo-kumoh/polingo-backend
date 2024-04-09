package com.tangtang.polingo.user.dto;

import com.tangtang.polingo.global.constant.Language;
import lombok.Builder;

@Builder
public record UserResponse(long id, String nickname, Language defaultLanguage) {
}
