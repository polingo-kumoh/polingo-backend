package com.tangtang.polingo.oauth2.service;

import com.tangtang.polingo.global.constant.LoginType;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OAuth2Services {
    private final Map<LoginType, OAuth2Service> authServiceMap;

    public OAuth2Services(List<OAuth2Service> oAuth2Services) {
        this.authServiceMap = oAuth2Services.stream()
                .collect(Collectors.toMap(OAuth2Service::getLoginType, Function.identity()));
    }

    public OAuth2Service getAuthService(LoginType loginType) {
        return this.authServiceMap.get(loginType);
    }
}
