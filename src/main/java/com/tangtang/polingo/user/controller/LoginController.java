package com.tangtang.polingo.user.controller;

import com.tangtang.polingo.user.service.LoginService;
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
    @Value("${frontend-url}")
    private String frontendUrl;
    private final LoginService loginService;

    @GetMapping("/{provider}")
    public ResponseEntity<Void> redirect(@PathVariable String provider) {
        return loginService.redirectLogin(provider);
    }

    @GetMapping("/{provider}/callback")
    public void callback(@PathVariable String provider, @RequestParam("code") String code, HttpServletResponse response)
            throws IOException {
        String token = loginService.loginAfterCallback(provider, code);

        log.info("[{}] 로그인 성공, Token: {}", provider, token);
        response.sendRedirect(frontendUrl + "?token=" + token);
    }
}
