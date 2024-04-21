package com.tangtang.polingo.translate.service.ocr;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
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

            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                JSONObject jsonObject = new JSONObject(response.getBody());
                return jsonObject.optString("data", "데이터가 없습니다.");  // "data" 키가 없을 경우 "데이터가 없습니다." 기본값 반환
            } else {
                log.error("OCR 결과를 가져오는데 실패했습니다: {}", response.getStatusCode());
                return null;
            }
        } catch (Exception e) {
            log.error("OCR 텍스트 변환 중 예외 발생", e);
            throw new RuntimeException("이미지 파일 처리 중 오류 발생", e);
        }
    }
}
