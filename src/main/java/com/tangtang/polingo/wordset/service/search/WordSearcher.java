package com.tangtang.polingo.wordset.service.search;

import com.tangtang.polingo.global.constant.Language;
import java.util.Optional;

@FunctionalInterface
public interface WordSearcher {
    Optional<String> search(Language code, String word);
}
