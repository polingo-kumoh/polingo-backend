package com.tangtang.polingo.oauth2.service;

import com.tangtang.polingo.global.constant.LoginType;
import com.tangtang.polingo.oauth2.dto.UserInfo;
import org.springframework.http.ResponseEntity;

public interface OAuth2Service {
    ResponseEntity<Void> redirectAuthorizeURI();

    String requestAccessToken(String code);

    UserInfo requestUserInfo(String accessToken);

    LoginType getLoginType();
}
