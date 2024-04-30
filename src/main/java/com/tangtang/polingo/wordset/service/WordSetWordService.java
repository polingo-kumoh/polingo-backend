package com.tangtang.polingo.wordset.service;

import com.tangtang.polingo.word.entity.Word;
import com.tangtang.polingo.word.repository.WordRepository;
import com.tangtang.polingo.wordset.dto.dto.InsertWordRequest;
import com.tangtang.polingo.wordset.dto.dto.WordSetDetailsResponse;
import com.tangtang.polingo.wordset.entity.WordSet;
import com.tangtang.polingo.wordset.entity.WordSetWord;
import com.tangtang.polingo.wordset.repository.WordSetRepository;
import com.tangtang.polingo.wordset.repository.repository.WordSetWordRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WordSetWordService {
    private final WordRepository wordRepository;
    private final WordSetRepository wordSetRepository;
    private final WordSetWordRepository wordSetWordRepository;

    public void insertWord(Long wordSetId, InsertWordRequest req) {
        WordSet wordSet = wordSetRepository.findById(wordSetId)
                .orElseThrow(() -> new IllegalArgumentException("단어장을 찾을 수 없습니다."));

        // 단어를 찾거나 새로 생성
        Word word = wordRepository.findByText(req.word())
                .orElseGet(() -> createAndSaveWord(req.word(), req.description()));

        // 단어장과 단어를 연결
        linkWordToWordSet(wordSet, word);
    }

    private Word createAndSaveWord(String text, String description) {
        Word newWord = Word.builder()
                .text(text)
                .description(description)
                .build();
        return wordRepository.save(newWord);
    }

    private void linkWordToWordSet(WordSet wordSet, Word word) {
        // 단어장과 단어가 이미 연결되어 있는지 확인
        if (!wordSetWordRepository.existsByWordSetAndWord(wordSet, word)) {
            WordSetWord wordSetWord = WordSetWord.builder()
                    .wordSet(wordSet)
                    .word(word)
                    .build();
            wordSetWordRepository.save(wordSetWord);
        }
    }

    public WordSetDetailsResponse getWordSetDetails(Long wordSetId) {
        WordSet wordSet = wordSetRepository.findById(wordSetId)
                .orElseThrow(() -> new IllegalArgumentException("단어장을 찾을 수 없습니다."));

        List<WordSetDetailsResponse.WordDetail> wordDetails = wordSetWordRepository.findAllByWordSet(wordSet).stream()
                .map(wsw -> WordSetDetailsResponse.WordDetail.builder()
                        .id(wsw.getId())
                        .word(wsw.getWord().getText())
                        .description(wsw.getWord().getDescription())
                        .build())
                .toList();

        return WordSetDetailsResponse.builder()
                .id(wordSet.getId())
                .language(wordSet.getLanguage().toString())
                .name(wordSet.getName())
                .words(wordDetails)
                .build();
    }
}

