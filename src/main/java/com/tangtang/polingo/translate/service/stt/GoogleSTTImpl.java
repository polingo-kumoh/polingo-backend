package com.tangtang.polingo.translate.service.stt;

import com.google.cloud.speech.v1.RecognitionAudio;
import com.google.cloud.speech.v1.RecognitionConfig;
import com.google.cloud.speech.v1.RecognitionConfig.AudioEncoding;
import com.google.cloud.speech.v1.SpeechClient;
import com.google.cloud.speech.v1.SpeechRecognitionResult;
import com.google.protobuf.ByteString;
import com.tangtang.polingo.global.constant.Language;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Component
@RequiredArgsConstructor
public class GoogleSTTImpl implements SpeachToText {
    private final SpeechClient speechClient;

    @Override
    public String convert(MultipartFile file, Language language) {
        ByteString audioBytes;
        try {
            audioBytes = ByteString.copyFrom(file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 파일 MIME 타입에 따라 인코딩과 샘플 레이트 결정
        String contentType = file.getContentType();
        EncodingSampleRate encodingSampleRate = getEncodingAndSampleRateFromMimeType(contentType);

        RecognitionConfig config = RecognitionConfig.newBuilder()
                .setEncoding(encodingSampleRate.encoding)
                .setSampleRateHertz(encodingSampleRate.sampleRate)
                .setLanguageCode(language.getSttCode())
                .build();

        RecognitionAudio audio = RecognitionAudio.newBuilder()
                .setContent(audioBytes)
                .build();

        List<SpeechRecognitionResult> results = speechClient.recognize(config, audio).getResultsList();
        if (results.isEmpty()) {
            return "No transcription results found.";
        }

        return results.stream()
                .filter(result -> !result.getAlternativesList().isEmpty())
                .map(result -> result.getAlternativesList().get(0).getTranscript())
                .collect(Collectors.joining(". "));
    }

    private EncodingSampleRate getEncodingAndSampleRateFromMimeType(String mimeType) {
        return switch (mimeType) {
            case "audio/mp3" -> new EncodingSampleRate(AudioEncoding.MP3, 44100);
            case "audio/flac" -> new EncodingSampleRate(AudioEncoding.FLAC, 44100);
            case "audio/wav" -> new EncodingSampleRate(AudioEncoding.LINEAR16, 44100);
            case "audio/webm" -> new EncodingSampleRate(AudioEncoding.WEBM_OPUS, 48000);
            default -> new EncodingSampleRate(AudioEncoding.ENCODING_UNSPECIFIED, 16000);
        };
    }

    record EncodingSampleRate(AudioEncoding encoding, int sampleRate) {
    }
}
