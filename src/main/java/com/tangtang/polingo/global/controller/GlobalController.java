package com.tangtang.polingo.global.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "전역 API", description = "특정 도메인에 종속되지 않는 API들입니다.")
@RestController
public class GlobalController {

    @Operation(summary = "Health-Check API", description = "서버가 정상적으로 구동되는지 확인, 성공시 200 반환")
    @GetMapping("/api/health-check")
    public HttpStatus healthCheck() {
        return HttpStatus.OK;
    }
}
