package com.tangtang.polingo.user.controller;

import com.tangtang.polingo.global.dto.CommonResponse;
import com.tangtang.polingo.security.annotation.CurrentUser;
import com.tangtang.polingo.user.dto.UserResponse;
import com.tangtang.polingo.user.entity.User;
import com.tangtang.polingo.user.service.UserService;
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

    @GetMapping()
    public ResponseEntity<UserResponse> getUser(@CurrentUser User user) {
        return userService.getUser(user);
    }

    @PutMapping()
    public CommonResponse updateNickname(@CurrentUser User user, @RequestParam String name) {
        userService.updateNickname(user, name);
        return new CommonResponse(HttpStatus.OK, "닉네임을 변경하였습니다.");
    }
}
