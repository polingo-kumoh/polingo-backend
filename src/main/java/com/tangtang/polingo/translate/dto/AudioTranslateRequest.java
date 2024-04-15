package com.tangtang.polingo.translate.dto;

import com.tangtang.polingo.global.constant.Language;
import org.springframework.web.multipart.MultipartFile;

public record AudioTranslateRequest(Language sourceType, MultipartFile voice) {
}
