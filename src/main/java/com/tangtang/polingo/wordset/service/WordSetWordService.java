package com.tangtang.polingo.wordset.service;

import com.tangtang.polingo.quiz.service.UserWordService;
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
    private final UserWordService userWordService;

    public void insertWord(Long wordSetId, InsertWordRequest req) {
        WordSet wordSet = wordSetRepository.findById(wordSetId)
                .orElseThrow(() -> new IllegalArgumentException("단어장을 찾을 수 없습니다."));

        Word word = wordRepository.findByText(req.word())
                .orElseGet(() -> createAndSaveWord(req.word(), req.description()));

        boolean isExist = wordSetWordRepository.existsByWordSetAndWord(wordSet, word);

        if(isExist){
            throw new IllegalStateException("단어가 이미 이 단어장에 존재합니다.");
        }

        userWordService.createUserWord(wordSet.getUser(), word, wordSet.getLanguage());

        linkWordToWordSet(wordSet, word);
    }

    public void deleteWordFromWordSet(Long wordSetId, Long wordId) {
        WordSetWord wordSetWord = wordSetWordRepository.findByWordSetIdAndWordId(wordSetId, wordId)
                .orElseThrow(() -> new IllegalArgumentException("해당 단어 연결 정보를 찾을 수 없습니다."));

        userWordService.deleteUserWordOrDecreaseCount(wordSetWord.getWordSet().getUser().getId(), wordId);

        wordSetWordRepository.delete(wordSetWord);
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

    public void moveWordToAnotherWordSet(Long wordSetId, Long wordId, Long targetWordSetId) {
        WordSetWord wordSetWord = wordSetWordRepository.findByWordSetIdAndWordId(wordSetId, wordId)
                .orElseThrow(() -> new IllegalArgumentException("해당 단어 연결 정보를 찾을 수 없습니다."));

        WordSet targetWordSet = wordSetRepository.findById(targetWordSetId)
                .orElseThrow(() -> new IllegalArgumentException("타겟 단어장을 찾을 수 없습니다."));

        wordSetWordRepository.delete(wordSetWord);

        linkWordToWordSet(targetWordSet, wordSetWord.getWord());
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

