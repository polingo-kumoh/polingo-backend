package com.tangtang.polingo.translate.service.translate;

import com.tangtang.polingo.global.constant.Language;

public interface TextTranslator {
    String translate(String text, Language type);
}
