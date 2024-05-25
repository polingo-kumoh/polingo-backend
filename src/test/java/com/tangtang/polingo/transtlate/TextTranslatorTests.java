package com.tangtang.polingo.transtlate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.deepl.api.Translator;
import com.tangtang.polingo.translate.config.GoogleCloudConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = GoogleCloudConfig.class)
public class TextTranslatorTests {
    @Autowired
    private GoogleCloudConfig googleCloudConfig;
    private Translator translator;

    @BeforeEach
    public void setUp() {
        translator = googleCloudConfig.translator();
    }

    @Test
    @DisplayName("빈 정상 주입 확인")
    public void testTranslatorNotNull() {
        assertNotNull(googleCloudConfig.translator(), "Translator 객체가 초기화되어야 합니다.");
    }

    @Test
    @DisplayName("en -> ko 정상 동작 확인")
    public void testTranslationEnglish() throws Exception {
        //given
        String sourceText = "Hello";
        String sourceLanguage = "en";
        String targetLanguage = "ko";
        String expect = "안녕하세요";

        //when
        String actual = translator.translateText(sourceText, sourceLanguage, targetLanguage).getText();

        // then
        assertNotNull(actual);
        assertEquals(expect, actual, "Should translate 'Hello' to '안녕하세요'");
    }

    @Test
    @DisplayName("jp -> ko 정상 동작 확인")
    public void testTranslationJapan() throws Exception {
        String sourceText = "こんにちは";
        String sourceLanguage = "ja";
        String targetLanguage = "ko";
        String expect = "안녕하세요";

        //when
        String actual = translator.translateText(sourceText, sourceLanguage, targetLanguage).getText();

        // then
        assertNotNull(actual);
        assertEquals(expect, actual, "Should translate 'こんにちは' to '안녕하세요'");
    }
}
