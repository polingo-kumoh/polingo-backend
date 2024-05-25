package com.tangtang.polingo.word.service;

import com.tangtang.polingo.global.constant.Language;
import com.tangtang.polingo.word.dto.WordMeaningResponse;
import com.tangtang.polingo.word.service.search.WordSearcher;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WordService {
    private final WordSearcher wordSearcher;

    public WordMeaningResponse findWordMeaning(String code, String word) {
        Language language = Language.fromCode(code);
        Optional<String> descriptionOpt = wordSearcher.search(language, word);

        if (descriptionOpt.isEmpty()) {
            throw new IllegalArgumentException("단어가 사전에 존재하지 않습니다!");
        }

        String description = descriptionOpt.get();
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
        if (tokens.length > 0) {
            return tokens[0];
        }
        return "";
    }
}

