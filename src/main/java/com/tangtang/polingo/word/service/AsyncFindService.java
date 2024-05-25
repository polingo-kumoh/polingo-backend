package com.tangtang.polingo.word.service;

import com.tangtang.polingo.global.constant.Language;
import com.tangtang.polingo.translate.service.translate.TextTranslator;
import com.tangtang.polingo.word.service.search.WordSearcher;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AsyncFindService {
    private final WordSearcher wordSearcher;
    private final TextTranslator textTranslator;

    @Async
    public CompletableFuture<Optional<String>> searchInElasticsearch(Language language, String word) {
        return CompletableFuture.supplyAsync(() -> wordSearcher.search(language, word));
    }

    @Async
    public CompletableFuture<String> translateWithDeepL(String word, Language language) {
        return CompletableFuture.supplyAsync(() -> textTranslator.translate(word, language));
    }
}
