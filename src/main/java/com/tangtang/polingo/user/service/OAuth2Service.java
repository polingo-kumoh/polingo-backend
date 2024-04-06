package com.tangtang.polingo.user.service;

import com.tangtang.polingo.global.constant.LoginType;
import com.tangtang.polingo.user.dto.UserInfo;
import org.springframework.http.ResponseEntity;

public interface OAuth2Service {
    ResponseEntity<Void> redirectAuthorizeURI();

    UserInfo handleCallback(String code);

    LoginType getLoginType();
}
