package com.tangtang.polingo.translate.service;

import com.deepl.api.DeepLException;
import com.deepl.api.TextResult;
import com.deepl.api.Translator;
import com.tangtang.polingo.global.constant.Language;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeepLTranslationImpl implements Translation {
    private static final String TARGET = Language.KOREA.getCode();
    private final Translator translator;

    @Override
    public String translate(String text, String type) {
        TextResult result;
        try {
            result = translator.translateText(text, type, TARGET);
        } catch (DeepLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return result.getText();
    }
}
