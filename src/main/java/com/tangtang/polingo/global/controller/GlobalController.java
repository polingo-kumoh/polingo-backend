package com.tangtang.polingo.global.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GlobalController {
    @GetMapping("/health-check")
    public HttpStatus healthCheck() {
        return HttpStatus.OK;
    }
}
