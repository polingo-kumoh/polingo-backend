package com.tangtang.polingo.wordset.service;

import com.tangtang.polingo.global.constant.Language;
import com.tangtang.polingo.wordset.dto.WordMeaningResponse;
import com.tangtang.polingo.wordset.service.search.WordSearcher;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WordSetService {
    private final WordSearcher wordSearcher;

    public WordMeaningResponse findWordMeaning( String code, String word) {
        Language language = Language.fromCode(code);
        Optional<String> descriptionOpt = wordSearcher.search(language, word);
        String description = descriptionOpt.orElse("단어가 사전에 존재하지 않습니다!");

        return WordMeaningResponse.builder()
                .word(word)
                .description(description)
                .build();
    }
}
