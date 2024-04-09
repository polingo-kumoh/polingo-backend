package com.tangtang.polingo.global.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public record CommonResponse(int status, String message,
                             @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime timestamp) {
    public CommonResponse(int status, String message) {
        this(status, message, LocalDateTime.now());
    }
}
