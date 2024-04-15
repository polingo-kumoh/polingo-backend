package com.tangtang.polingo.translate.config;

import com.deepl.api.Translator;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.speech.v1.SpeechClient;
import com.google.cloud.speech.v1.SpeechSettings;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Slf4j
@Configuration
public class TranslatorConfig {

    @Value("${translator.deepL-key}")
    private String authKey;

    @Value("${spring.cloud.gcp.credentials.location}")
    private Resource gcpCredentials;

    @Bean
    public Translator translator() {
        return new Translator(authKey);
    }

    @Bean
    public SpeechClient speechClient() throws IOException {
        GoogleCredentials credentials = GoogleCredentials.fromStream(gcpCredentials.getInputStream());
        SpeechSettings speechSettings = SpeechSettings.newBuilder()
                .setCredentialsProvider(() -> credentials)
                .build();

        return SpeechClient.create(speechSettings);
    }
}
