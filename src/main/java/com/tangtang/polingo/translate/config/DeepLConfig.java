package com.tangtang.polingo.translate.config;

import com.deepl.api.Translator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeepLConfig {
    @Value("${translator.deepL-key}")
    private String authKey;

    @Bean
    public Translator translator() {
        return new Translator(authKey);
    }
}
