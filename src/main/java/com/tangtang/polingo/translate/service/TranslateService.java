package com.tangtang.polingo.translate.service;

import com.deepl.api.DeepLException;
import com.deepl.api.TextResult;
import com.deepl.api.Translator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TranslateService {
    private final Translator translator;

    public String translatePlainText(String text){
        TextResult result = null;
        try {
            result = translator.translateText(text, null, "ko");
        } catch (DeepLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return result.getText();
    }
}
