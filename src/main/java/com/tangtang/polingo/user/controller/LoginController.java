package com.tangtang.polingo.user.controller;


import com.tangtang.polingo.user.client.KakaoClient;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "로그인 관련 API", description = "로그인  관련 API입니다. 구글 로그인과 카카오 로그인을 지원합니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/login")
public class LoginController {
    @Value("${frontend-url}")
    private String frontendUrl;
    private final KakaoClient kakaoClient;

    @Operation(summary = "카카오 로그인 API", description = "카카오 로그인 API입니다. 스웨거나 포스트맨으로는 동작하지 않고 브라우저창을 통해 직접 주소에 접근하셔야 됩니다. ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인 성공!, localhost:3000?token=?을 통해서 토큰을 전달합니다.", content = @Content(schema = @Schema(hidden = true))),
            @ApiResponse(responseCode = "500", description= " 다뤄지지 않은 Server 오류, 백엔드 담당자에게 문의!", content = @Content(schema = @Schema(hidden = true)))
    })
    @GetMapping("/kakao")
    public ResponseEntity<Void> redirectToKakaoLogin() {
        return kakaoClient.redirectToKakaoAuth();
    }
}
