package com.tangtang.polingo.translate.service.ocr;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Component
@RequiredArgsConstructor
public class OCRConverterImpl implements ImageToText {
    private final RestTemplate restTemplate;

    @Value("${translator.ocr-uri}")
    private String ocrApiUri;

    @Override
    public String convert(MultipartFile image) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            Resource resource = new ByteArrayResource(image.getBytes()) {
                @Override
                public String getFilename() {
                    return image.getOriginalFilename();
                }
            };

            body.add("file", resource);

            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

            ResponseEntity<String> response = restTemplate.postForEntity(ocrApiUri, requestEntity, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody(); // Or parse JSON to extract "data" field
            } else {
                log.error("Failed to get OCR results: {}", response.getStatusCode());
                return null;
            }
        } catch (Exception e) {
            log.error("Exception during OCR text conversion", e);
            throw new RuntimeException("Error processing image file", e);
        }
    }
}
