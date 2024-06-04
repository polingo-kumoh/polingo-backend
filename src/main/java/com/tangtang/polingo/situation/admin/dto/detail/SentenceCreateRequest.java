package com.tangtang.polingo.situation.admin.dto.detail;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SentenceCreateRequest {
    private String originText;
    private String translatedText;
    private String language;
}
