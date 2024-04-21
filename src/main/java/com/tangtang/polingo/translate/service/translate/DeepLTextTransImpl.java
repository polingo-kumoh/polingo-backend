package com.tangtang.polingo.translate.service.translate;

import com.deepl.api.DeepLException;
import com.deepl.api.TextResult;
import com.deepl.api.Translator;
import com.tangtang.polingo.global.constant.Language;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeepLTextTransImpl implements TextTranslator {
    private static final String TARGET = Language.KOREAN.getTextCode();
    private final Translator translator;

    @Override
    public String translate(String text, Language type) {
        TextResult result;
        try {
            result = translator.translateText(text, type.getTextCode(), TARGET);
        } catch (DeepLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return result.getText();
    }
}
