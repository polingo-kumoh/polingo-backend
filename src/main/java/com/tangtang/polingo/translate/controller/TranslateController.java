package com.tangtang.polingo.translate.controller;


import com.tangtang.polingo.global.constant.Language;
import com.tangtang.polingo.translate.dto.PlainTextTranslateRequest;
import com.tangtang.polingo.translate.dto.TranslateResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@Tag(name = "번역 관련 API", description = "번역 관련 API를 제공합니다.")
@Slf4j
@RestController
@RequestMapping("/api/translate")
@RequiredArgsConstructor
public class TranslateController {


    @PostMapping("/text")
    @Operation(summary = "텍스트 번역 API", description = "텍스트를 번역합니다.")
    public TranslateResponse translateText(
            @RequestParam Language sourceLanguage,
            @RequestBody PlainTextTranslateRequest text
    ) {
        return TranslateResponse.builder().originalText("Hello").translatedText("안녕하세요.").build();
    }

    @PostMapping("/image")
    @Operation(summary = "이미지 번역 API", description = "이미지를 번역합니다.")
    public TranslateResponse translateImage(
            @RequestParam Language sourceLanguage,
            @RequestParam("file") MultipartFile image
    ) {
        if (!isImageFile(image)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이미지 파일만 업로드 가능합니다.");
        }

        return TranslateResponse.builder().originalText("Hello").translatedText("안녕하세요.").build();
    }


    // 이미지 파일인지 확인하는 메서드
    private boolean isImageFile(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null && contentType.startsWith("image/");
    }
}
