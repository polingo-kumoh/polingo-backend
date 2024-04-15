package com.tangtang.polingo.translate.service.stt;

import com.tangtang.polingo.global.constant.Language;
import org.springframework.web.multipart.MultipartFile;

public interface SpeachToText {
    String convert(MultipartFile file, Language source);
}
