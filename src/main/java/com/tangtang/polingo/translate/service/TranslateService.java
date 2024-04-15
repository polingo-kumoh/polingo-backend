package com.tangtang.polingo.translate.service;

import com.tangtang.polingo.translate.dto.PlainTextTranslateRequest;
import com.tangtang.polingo.translate.dto.TranslateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TranslateService {
    private final Translation translation;

    public TranslateResponse translatePlainText(PlainTextTranslateRequest request) {
        String original = request.text();
        String result = translation.translate(original, request.getSource());

        return TranslateResponse.builder()
                .originalText(original).
                translatedText(result).
                build();
    }
}
