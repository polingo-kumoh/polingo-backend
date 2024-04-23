package com.tangtang.polingo.translate.service.stt;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Component
public class FileConverter {

    public File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
        // 임시 디렉토리에 파일을 생성합니다. 시스템의 기본 임시 파일 저장소를 사용
        Path tempDir = Files.createTempDirectory("audio_files");
        // 임시 디렉토리에 파일의 원본 이름으로 파일을 생성
        File tempFile = new File(tempDir.toFile(), Objects.requireNonNull(multipartFile.getOriginalFilename()));

        // MultipartFile의 내용을 임시 파일로 복사.
        multipartFile.transferTo(tempFile);
        log.info("temp = {}", tempFile);
        return tempFile;
    }
}
