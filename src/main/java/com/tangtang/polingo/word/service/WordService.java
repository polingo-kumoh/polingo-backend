package com.tangtang.polingo.word.service;

import com.tangtang.polingo.global.constant.Language;
import com.tangtang.polingo.word.dto.WordMeaningResponse;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WordService {
    private final AsyncFindService asyncFindService;

    public WordMeaningResponse findWordMeaning(String code, String word) {
        Language language = Language.fromCode(code);

        CompletableFuture<Optional<String>> esFuture = asyncFindService.searchInElasticsearch(language, word);
        CompletableFuture<Optional<String>> deepLFuture = asyncFindService.translateWithDeepL(word, language)
                .thenApply(Optional::of);

        return createWordMeaningResponse(word, esFuture, deepLFuture);
    }

    private WordMeaningResponse createWordMeaningResponse(String word,
                                                          CompletableFuture<Optional<String>> esFuture,
                                                          CompletableFuture<Optional<String>> deepLFuture) {
        try {
            CompletableFuture<Object> future = CompletableFuture.anyOf(esFuture, deepLFuture);
            Optional<String> firstResult = (Optional<String>) future.get(3, TimeUnit.SECONDS);

            // 첫 번째 완료된 작업의 결과가 있는 경우 이를 사용
            return firstResult
                    .filter(result -> {
                        try {
                            return esFuture.isDone() && esFuture.get().isPresent();
                        } catch (InterruptedException | ExecutionException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .map(result -> {
                        deepLFuture.cancel(true); // esResult가 있으면 deepLFuture를 취소
                        return buildResponse(word, result);
                    })
                    .orElseGet(() -> {
                        try {
                            // 첫 번째 완료된 작업의 결과가 없는 경우 deepLFuture의 결과를 사용
                            String description = deepLFuture.get(4, TimeUnit.SECONDS)
                                    .orElseThrow(() -> new RuntimeException("단어를 찾을 수 없습니다."));
                            return buildResponse(word, description);
                        } catch (InterruptedException | ExecutionException | TimeoutException e) {
                            throw new RuntimeException("단어를 찾는 중에 에러가 발생했습니다.", e);
                        }
                    });
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // 인터럽트 상태를 복원
            throw new RuntimeException("단어를 찾는 중에 인터럽트가 발생했습니다.", e);
        } catch (ExecutionException | TimeoutException e) {
            throw new RuntimeException("단어를 찾는 중에 에러가 발생했습니다.", e);
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
        String cleanedDescription = description.replaceAll("[^가-힣 ]", "").strip();
        String[] tokens = cleanedDescription.split(" ");
        return tokens.length > 0 ? tokens[0] : "";
    }
}
