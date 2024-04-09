package com.tangtang.polingo.user.controller;

import com.tangtang.polingo.user.service.LoginService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "로그인 관련 API", description = "구글 로그인, 카카오 로그인을 지원합니다.")
@Slf4j
@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;
    @Value("${frontend-url}")
    private String frontendUrl;

    @Operation(summary = "OAuth2 로그인 API", description =
            "{server-address}/api/login/kakao 혹은 {server-address}/api/login/google의 형태로 각 provider 별 OAuth2 인증 화면에 접근할 수 있습니다. </br> +"
                    + "직접 api호출을 통해 접근할 수 있으며, 로그인이 완료되면 {server-address}?token={JWT 토큰}의 형태로 반환됩니다.")
    @GetMapping("/{provider}")
    public ResponseEntity<Void> redirect(@PathVariable String provider) {
        return loginService.redirectLogin(provider);
    }

    @Hidden
    @GetMapping("/{provider}/callback")
    public void callback(@PathVariable String provider, @RequestParam("code") String code, HttpServletResponse response)
            throws IOException {
        String token = loginService.loginAfterCallback(provider, code);

        log.info("[{}] 로그인 성공, Token: {}", provider, token);
        response.sendRedirect(frontendUrl + "?token=" + token);
    }
}
