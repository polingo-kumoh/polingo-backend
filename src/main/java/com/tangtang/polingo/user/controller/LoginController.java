package com.tangtang.polingo.user.controller;

import com.tangtang.polingo.global.constant.LoginType;
import com.tangtang.polingo.oauth2.dto.UserInfo;
import com.tangtang.polingo.oauth2.service.OAuth2Service;
import com.tangtang.polingo.oauth2.service.OAuth2Services;
import com.tangtang.polingo.user.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;
    private final OAuth2Services oAuth2Services;
    @Value("${frontend-url}")
    private String frontendUrl;

    @GetMapping("/{provider}")
    public ResponseEntity<Void> redirect(@PathVariable String provider) {
        LoginType loginType = LoginType.fromProvider(provider);
        OAuth2Service authService = oAuth2Services.getAuthService(loginType);

        return authService.redirectAuthorizeURI();
    }

    @GetMapping("/{provider}/callback")
    public void callback(@PathVariable String provider, @RequestParam("code") String code, HttpServletResponse response)
            throws IOException {
        LoginType loginType = LoginType.fromProvider(provider);
        OAuth2Service authService = oAuth2Services.getAuthService(loginType);

        String accessToken = authService.requestAccessToken(code);
        UserInfo userInfo = authService.requestUserInfo(accessToken);
        log.info("[{}] {} 로그인 성공", provider, userInfo.getName());

        String token = userService.login(loginType, userInfo);

        response.sendRedirect(frontendUrl + "?token=" + token);
    }
}
