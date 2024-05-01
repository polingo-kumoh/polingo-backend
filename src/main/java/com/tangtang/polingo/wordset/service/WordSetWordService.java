package com.tangtang.polingo.wordset.service;

import com.tangtang.polingo.word.entity.Word;
import com.tangtang.polingo.word.repository.WordRepository;
import com.tangtang.polingo.wordset.dto.wordsetword.InsertWordRequest;
import com.tangtang.polingo.wordset.dto.wordsetword.WordSetDetailsResponse;
import com.tangtang.polingo.wordset.entity.WordSet;
import com.tangtang.polingo.wordset.entity.WordSetWord;
import com.tangtang.polingo.wordset.repository.WordSetRepository;
import com.tangtang.polingo.wordset.repository.WordSetWordRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class WordSetWordService {
    private final WordRepository wordRepository;
    private final WordSetRepository wordSetRepository;
    private final WordSetWordRepository wordSetWordRepository;

    public void insertWord(Long wordSetId, InsertWordRequest req) {
        WordSet wordSet = wordSetRepository.findById(wordSetId)
                .orElseThrow(() -> new IllegalArgumentException("단어장을 찾을 수 없습니다."));

        Word word = wordRepository.findByText(req.word())
                .orElseGet(() -> createAndSaveWord(req.word(), req.description()));

        if (wordSetWordRepository.existsByWordSetAndWord(wordSet, word)) {
            throw new IllegalStateException("단어가 이미 이 단어장에 존재합니다.");
        }

        linkWordToWordSet(wordSet, word);
    }

    public void deleteWordsFromWordSet(Long wordSetId, List<Long> wordIds) {
        List<WordSetWord> wordsToDelete = wordSetWordRepository.findByWordSetIdAndWordIds(wordSetId, wordIds);
        wordSetWordRepository.deleteAll(wordsToDelete);
    }

    public void moveWordsToAnotherWordSet(Long wordSetId, List<Long> wordIds, Long targetWordSetId) {
        List<WordSetWord> wordsToMove = wordSetWordRepository.findByWordSetIdAndWordIds(wordSetId, wordIds);
        WordSet targetWordSet = wordSetRepository.findById(targetWordSetId)
                .orElseThrow(() -> new IllegalArgumentException("타겟 단어장을 찾을 수 없습니다."));

        for (WordSetWord wordSetWord : wordsToMove) {
            wordSetWordRepository.delete(wordSetWord);
            linkWordToWordSet(targetWordSet, wordSetWord.getWord());
        }
    }


    @Transactional(readOnly = true)
    public WordSetDetailsResponse getWordSetDetails(Long wordSetId) {
        WordSet wordSet = wordSetRepository.findById(wordSetId)
                .orElseThrow(() -> new IllegalArgumentException("단어장을 찾을 수 없습니다."));

        List<WordSetDetailsResponse.WordDetail> wordDetails = wordSetWordRepository.findAllByWordSet(wordSet).stream()
                .map(wsw -> WordSetDetailsResponse.WordDetail.builder()
                        .id(wsw.getWord().getId())
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

    private Word createAndSaveWord(String text, String description) {
        Word newWord = Word.builder()
                .text(text)
                .description(description)
                .build();
        return wordRepository.save(newWord);
    }

    private void linkWordToWordSet(WordSet wordSet, Word word) {
        if (!wordSetWordRepository.existsByWordSetAndWord(wordSet, word)) {
            WordSetWord newWordSetWord = WordSetWord.builder()
                    .wordSet(wordSet)
                    .word(word)
                    .build();
            wordSetWordRepository.save(newWordSetWord);
        }
    }
}

