package com.tangtang.polingo.situation.admin.dto.detail;

import lombok.Data;

@Data
public class SentenceUpdateRequest {
    private String originText;
    private String translatedText;
    private String language;
}
