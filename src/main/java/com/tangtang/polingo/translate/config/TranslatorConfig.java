package com.tangtang.polingo.translate.config;

import com.deepl.api.Translator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class TranslatorConfig {

    @Value("${translator.deepL-key}")
    private String authKey;

    @Bean
    public Translator translator() {
        log.info("key = {}", authKey);
        return new Translator(authKey);
    }
}
