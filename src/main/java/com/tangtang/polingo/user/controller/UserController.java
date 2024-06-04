package com.tangtang.polingo.user.controller;

import com.tangtang.polingo.global.dto.CommonResponse;
import com.tangtang.polingo.security.security.annotation.CurrentUser;
import com.tangtang.polingo.user.dto.UserResponse;
import com.tangtang.polingo.user.entity.User;
import com.tangtang.polingo.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "사용자 관련 API", description = "사용자 정보를 다루는 API를 제공합니다.")
@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "사용자 조회 API", description = "사용자 정보를 조회할 수 있습니다.")
    @GetMapping()
    public ResponseEntity<UserResponse> getUser(@CurrentUser User user) {
        return userService.getUser(user);
    }

    @Operation(summary = "사용자 기본 언어 설정 변경 API", description = "기본 언어 설정을 변경하는 API입니다. [en/jp] 둘 중 하나로 선택할 수 있습니다.")
    @PutMapping("/language")
    public CommonResponse updateLanguage(@CurrentUser User user, @RequestParam String languageCode) {
        userService.updateLanguage(user, languageCode);
        return new CommonResponse(HttpStatus.OK.value(), "언어를 변경하였습니다.");
    }

    @Operation(summary = "사용자 닉네임 변경 API", description = "닉네임을 변경하는 API입니다.")
    @PutMapping("/nickname")
    public CommonResponse updateNickname(@CurrentUser User user, @RequestParam String name) {
        userService.updateNickname(user, name);
        return new CommonResponse(HttpStatus.OK.value(), "닉네임을 변경하였습니다.");
    }
}
