package com.tangtang.polingo.global.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public record ErrorResponse(String error, String message,
                            @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime timestamp) {
    public ErrorResponse(String error, String message) {
        this(error, message, LocalDateTime.now());
    }
}
