package com.tangtang.polingo.word.service;

import com.tangtang.polingo.global.constant.Language;
import com.tangtang.polingo.word.dto.WordMeaningResponse;
import java.util.Optional;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WordService {
    private static final String WORD_NOT_FOUND_MESSAGE = "단어를 찾을 수 없습니다.";
    private static final Pattern KOREAN_CHARACTERS_PATTERN = Pattern.compile("[^가-힣 ]");

    private final AsyncFindService asyncFindService;

    public WordMeaningResponse findWordMeaning(String code, String word) {
        Language language = Language.fromCode(code);
        CompletableFuture<Optional<String>> esFuture = asyncFindService.searchInElasticsearch(language, word);
        CompletableFuture<Optional<String>> deepLFuture = asyncFindService.translateWithDeepL(word, language);

        CompletableFuture<WordMeaningResponse> responseFuture = esFuture.thenCompose(
                esResultOpt -> esResultOpt
                        .map(esResult -> completeWithResponse(word, esResult))
                        .orElseGet(() -> deepLFuture.thenCompose(
                                deepLResultOpt -> deepLResultOpt
                                        .map(deepLResult -> completeWithResponse(word, deepLResult))
                                        .orElseThrow(() -> new RuntimeException(WORD_NOT_FOUND_MESSAGE))
                        ))
        );

        return handleCompletion(responseFuture);
    }

    private CompletableFuture<WordMeaningResponse> completeWithResponse(String word, String result) {
        return CompletableFuture.completedFuture(buildResponse(word, result));
    }

    private WordMeaningResponse handleCompletion(CompletableFuture<WordMeaningResponse> future) {
        try {
            return future.join();
        } catch (CancellationException ce) {
            log.error("작업이 취소되었습니다.", ce);
            throw new IllegalStateException("작업이 취소되었습니다.", ce);
        } catch (CompletionException ce) {
            log.error("비동기 처리 중 오류가 발생했습니다.", ce);
            throw new IllegalStateException("처리 중 오류가 발생했습니다.", ce);
        }
    }

    private WordMeaningResponse buildResponse(String word, String description) {
        String translate = extractFirstWord(description);
        return WordMeaningResponse.builder()
                .word(word)
                .translate(translate)
                .description(description)
                .build();
    }

    private String extractFirstWord(String description) {
        String cleanedDescription = KOREAN_CHARACTERS_PATTERN.matcher(description).replaceAll("").strip();
        String[] tokens = cleanedDescription.split(" ");
        return tokens[0];
    }
}
