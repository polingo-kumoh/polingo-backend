package com.tangtang.polingo.user.controller;

import com.tangtang.polingo.global.constant.LoginType;
import com.tangtang.polingo.user.dto.UserInfo;
import com.tangtang.polingo.user.service.OAuth2Service;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
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
    private final List<OAuth2Service> OAuth2Services;
    @Value("${frontend-url}")
    private String frontendUrl;
    private Map<LoginType, OAuth2Service> authServiceMap;

    @PostConstruct
    public void init() {
        authServiceMap = OAuth2Services.stream()
                .collect(Collectors.toMap(OAuth2Service::getLoginType, Function.identity()));
    }

    @GetMapping("/{provider}")
    public ResponseEntity<Void> redirect(@PathVariable String provider) {
        LoginType loginType = LoginType.fromProvider(provider);
        OAuth2Service authService = authServiceMap.get(loginType);
        return authService.redirectAuthorizeURI();
    }

    @GetMapping("/{provider}/callback")
    public void callback(@PathVariable String provider, @RequestParam("code") String code, HttpServletResponse response)
            throws IOException {
        LoginType loginType = LoginType.fromProvider(provider);
        OAuth2Service authService = authServiceMap.get(loginType);
        UserInfo userInfo = authService.handleCallback(code);

        log.info("{} 로그인 성공 : {}", provider, userInfo.getName());
        response.sendRedirect(frontendUrl);
    }
}
