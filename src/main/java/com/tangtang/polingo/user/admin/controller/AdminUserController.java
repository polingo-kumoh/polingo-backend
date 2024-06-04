package com.tangtang.polingo.user.admin.controller;

import com.tangtang.polingo.user.admin.dto.AdminLoginResponse;
import com.tangtang.polingo.user.admin.dto.AdminUserLoginRequest;
import com.tangtang.polingo.user.admin.dto.AdminUserSignUpRequest;
import com.tangtang.polingo.user.admin.service.AdminUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "어드민 로그인 관련 API", description = "어드민 로그인을 지원합니다.")
@Slf4j
@RestController
@RequestMapping("/api/admin/user")
@RequiredArgsConstructor
public class AdminUserController {
    private final AdminUserService adminUserService;
    @Value("${system.password}")
    private String systemPassword;

    @PostMapping("/signup")
    public ResponseEntity<Void> addUser(@RequestBody AdminUserSignUpRequest request) {
        log.info("syspassword = {}", this.systemPassword);
        if (!request.systemPassword().equals(this.systemPassword)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        adminUserService.createAdmin(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<AdminLoginResponse> loginAdmin(@RequestBody AdminUserLoginRequest request) {
        try {
            String token = adminUserService.loginAdmin(request);
            return ResponseEntity.ok(new AdminLoginResponse(token));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
