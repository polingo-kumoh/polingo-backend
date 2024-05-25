package com.tangtang.polingo.transtlate;


import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

import com.tangtang.polingo.global.constant.Language;
import com.tangtang.polingo.translate.config.GoogleCloudConfig;
import com.tangtang.polingo.translate.service.stt.GoogleSTTImpl;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@SpringBootTest
@ContextConfiguration(classes = {GoogleCloudConfig.class, GoogleSTTImpl.class})
public class GoogleSTTImplTest {
    @Autowired
    private GoogleSTTImpl googleSTT;

    @Test
    public void testConvert() throws Exception {
        // 테스트용 오디오 파일 로드 (M 파일)
        Path path = Path.of("src/test/resources/test.mp3");
        byte[] content = Files.readAllBytes(path);
        MultipartFile multipartFile = new MockMultipartFile("file", "test.mp3", "audio/mp3", content);

        log.info("file = {}", multipartFile);
        // 서비스 호출
        String result = googleSTT.convert(multipartFile, Language.ENGLISH);

        // 결과 검증
        log.info("결과 = {}", result);
        assertNotNull(result, "stt 결과는 null이 아니어야 합니다.");
    }
}
