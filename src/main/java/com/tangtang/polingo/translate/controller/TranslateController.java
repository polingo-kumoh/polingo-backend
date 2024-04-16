package com.tangtang.polingo.translate.controller;


import com.tangtang.polingo.global.constant.Language;
import com.tangtang.polingo.translate.dto.PlainTextTranslateRequest;
import com.tangtang.polingo.translate.dto.TranslateResponse;
import com.tangtang.polingo.translate.service.TranslateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Tag(name = "번역 관련 API", description = "번역 관련 API를 제공합니다. "
        + "요청에서 sorce_type은 원문의 국가가 됩니다. (ex. 번역하려는 text가 apple is red 면 sourcetype은 English)"
        + "</br>요청의 sourcetype은 JAPAN과 ENGLISH를 지원하며, 공통적으로 한국어를 반환합니다.")
@Slf4j
@RestController
@RequestMapping("/api/translate")
@RequiredArgsConstructor
public class TranslateController {
    private final TranslateService translateService;

    @PostMapping("/plain")
    @Operation(summary = "텍스트 번역 API", description = "텍스트를 번역합니다.")
    public ResponseEntity<TranslateResponse> translateText(@RequestBody PlainTextTranslateRequest request) {
        TranslateResponse result = translateService.translatePlainText(request);
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/voice", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "음성 번역 API", description = "음성을 번역합니다.")
    public ResponseEntity<TranslateResponse> translateVoice(@RequestParam("voice") MultipartFile voice,
                                                            @RequestParam("source-type") Language language) {
        TranslateResponse result = translateService.translateAudio(voice, language);
        return ResponseEntity.ok(result);
    }

    @PostMapping(value = "/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "이미지 번역 API", description = "이미지를 번역합니다.")
    public ResponseEntity<TranslateResponse> translateImage(@RequestParam("image") MultipartFile image,
                                                            @RequestParam("source-type") Language language) {
        TranslateResponse result = translateService.translateImage(image, language);
        return ResponseEntity.ok(result);
    }


    /**
     * @param file 파일
     * @return 이미지 파일 여부
     * @author minseok kim
     * @description 이미지 파일인지 확인하는 메서드
     */
    private boolean isImageFile(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null && contentType.startsWith("image/");
    }

    /**
     * @param file 파일
     * @return 음성 파일 여부
     * @author minseok kim
     * @description 음성 파일인지 확인하는 메서드
     */
    private boolean isVoiceFile(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null && contentType.startsWith("audio/");
    }
}
