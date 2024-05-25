package com.tangtang.polingo.translate.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.speech.v1.SpeechClient;
import com.google.cloud.speech.v1.SpeechSettings;
import com.google.cloud.texttospeech.v1.TextToSpeechClient;
import com.google.cloud.texttospeech.v1.TextToSpeechSettings;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class GoogleCloudConfig {
    @Value("${spring.cloud.gcp.credentials.location}")
    private Resource gcpCredentials;

    @Bean
    public SpeechClient speechClient() throws IOException {
        GoogleCredentials credentials = GoogleCredentials.fromStream(gcpCredentials.getInputStream());
        SpeechSettings speechSettings = SpeechSettings.newBuilder()
                .setCredentialsProvider(() -> credentials)
                .build();

        return SpeechClient.create(speechSettings);
    }

    @Bean
    public TextToSpeechClient textToSpeechClient() throws IOException {
        GoogleCredentials credentials = GoogleCredentials.fromStream(gcpCredentials.getInputStream());
        TextToSpeechSettings textToSpeechSettings = TextToSpeechSettings.newBuilder()
                .setCredentialsProvider(() -> credentials)
                .build();

        return TextToSpeechClient.create(textToSpeechSettings);
    }
}
