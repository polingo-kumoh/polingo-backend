package com.tangtang.polingo.situation.admin.dto;

import lombok.Data;

@Data
public class SentenceUpdateRequest {
    private String originText;
    private String translatedText;
    private String language;
}
