package com.tangtang.polingo.translate.service;

import com.deepl.api.DeepLException;
import com.deepl.api.TextResult;
import com.deepl.api.Translator;
import com.tangtang.polingo.global.constant.Language;
import com.tangtang.polingo.translate.dto.PlainTextTranslateRequest;
import com.tangtang.polingo.translate.dto.TranslateResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TranslateService {
    private static final String TARGET = Language.KOREA.getCode();
    private final Translator translator;

    public TranslateResponse translatePlainText(PlainTextTranslateRequest request){
        TextResult result;
        try {
            result = translator.translateText(request.text(), request.getSource(), TARGET);
        } catch (DeepLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return TranslateResponse.builder()
                .originalText(request.text())
                .translatedText(result.getText())
                .build();
    }
}
