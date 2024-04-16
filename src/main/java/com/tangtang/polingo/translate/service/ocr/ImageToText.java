package com.tangtang.polingo.translate.service.ocr;

import org.springframework.web.multipart.MultipartFile;

@FunctionalInterface
public interface ImageToText {
    String convert(MultipartFile image);
}
