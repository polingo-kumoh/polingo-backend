package com.tangtang.polingo.transtlate;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

import com.tangtang.polingo.translate.config.ExternalAPIConfig;
import com.tangtang.polingo.translate.service.ocr.OCRConverterImpl;
import com.tangtang.polingo.translate.service.stt.GoogleSTTImpl;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@SpringBootTest
@ContextConfiguration(classes = {RestTemplate.class, OCRConverterImpl.class})
public class OCRConverterImplTest {

    @Autowired
    private OCRConverterImpl ocrConverter;

    @Test
    public void testConvertImageToText() throws Exception {
        // 테스트용 이미지 파일 로드
        Path path = Path.of("src/test/resources/test.png");
        byte[] content = Files.readAllBytes(path);
        MultipartFile multipartFile = new MockMultipartFile("file", "test.png", "image/png", content);

        log.info("file = {}", multipartFile);

        // OCR 변환 서비스 호출
        String result = ocrConverter.convert(multipartFile);

        // 결과 출력 및 검증
        log.info("OCR 결과 = {}", result);
        assertNotNull(result, "OCR 변환 결과는 null이 아니어야 합니다.");
    }
}
