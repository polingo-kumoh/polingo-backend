package com.tangtang.polingo.global.dto;

public record CommonResponse(org.springframework.http.HttpStatus status, String message) {
}
