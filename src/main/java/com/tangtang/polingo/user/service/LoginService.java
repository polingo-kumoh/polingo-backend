package com.tangtang.polingo.user.service;

import com.tangtang.polingo.global.constant.Language;
import com.tangtang.polingo.global.constant.LoginType;
import com.tangtang.polingo.global.constant.UserRole;
import com.tangtang.polingo.oauth2.dto.UserInfo;
import com.tangtang.polingo.oauth2.service.OAuth2Service;
import com.tangtang.polingo.oauth2.service.OAuth2Services;
import com.tangtang.polingo.security.service.JwtService;
import com.tangtang.polingo.user.entity.User;
import com.tangtang.polingo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final JwtService jwtService;
    private final OAuth2Services oAuth2Services;

    private final UserRepository userRepository;

    public ResponseEntity<Void> redirectLogin(String provider) {
        LoginType loginType = LoginType.fromProvider(provider);
        OAuth2Service oauth2Service = oAuth2Services.getAuthService(loginType);

        return oauth2Service.redirectAuthorizeURI();
    }

    public String loginAfterCallback(String provider, String code) {
        LoginType loginType = LoginType.fromProvider(provider);
        OAuth2Service oauth2Service = oAuth2Services.getAuthService(loginType);

        String accessToken = oauth2Service.requestAccessToken(code);
        UserInfo userInfo = oauth2Service.requestUserInfo(accessToken);

        User user = userRepository.findByLoginTypeAndProviderId(loginType, userInfo.getId())
                .orElseGet(() -> createUser(loginType, userInfo));

        return jwtService.createToken(user);
    }

    private User createUser(LoginType loginType, UserInfo userInfo) {
        User newUser = User.builder()
                .nickname(userInfo.getName())
                .language(Language.ENGLISH)
                .role(UserRole.COMMON)
                .loginType(loginType)
                .providerId(userInfo.getId())
                .build();
        return userRepository.save(newUser);
    }
}