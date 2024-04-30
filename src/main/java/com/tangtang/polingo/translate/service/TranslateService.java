package com.tangtang.polingo.translate.service;

import com.tangtang.polingo.global.constant.Language;
import com.tangtang.polingo.translate.dto.PlainTextTranslateRequest;
import com.tangtang.polingo.translate.dto.TranslateResponse;
import com.tangtang.polingo.translate.service.ocr.ImageToText;
import com.tangtang.polingo.translate.service.stt.SpeachToText;
import com.tangtang.polingo.translate.service.translate.TextTranslator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TranslateService {
    private final TextTranslator textTranslator;
    private final SpeachToText speachToText;
    private final ImageToText imageToText;

    public TranslateResponse translatePlainText(PlainTextTranslateRequest request) {
        String original = request.text();
        Language source = request.sourceType();

        String result = textTranslator.translate(original, source);

        return TranslateResponse.builder()
                .originalText(original)
                .translatedText(result)
                .build();
    }

    public TranslateResponse translateAudio(MultipartFile voice, Language language) {
        String originalText = speachToText.convert(voice, language);
        String translatedText = textTranslator.translate(originalText, language);

        return TranslateResponse.builder()
                .originalText(originalText)
                .translatedText(translatedText)
                .build();
    }

    public TranslateResponse translateImage(MultipartFile image, Language language) {
        String originalText = imageToText.convert(image);
        String translatedText = textTranslator.translate(originalText, language);

        return TranslateResponse.builder()
                .originalText(originalText)
                .translatedText(translatedText)
                .build();
    }
}
