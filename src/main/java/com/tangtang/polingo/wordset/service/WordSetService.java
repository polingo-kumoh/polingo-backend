package com.tangtang.polingo.wordset.service;

import com.tangtang.polingo.global.constant.Language;
import com.tangtang.polingo.user.entity.User;
import com.tangtang.polingo.wordset.dto.wordset.WordSetCreateRequest;
import com.tangtang.polingo.wordset.dto.wordset.WordSetSummaryResponse;
import com.tangtang.polingo.wordset.entity.WordSet;
import com.tangtang.polingo.wordset.repository.WordSetRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WordSetService {
    private final WordSetRepository wordSetRepository;

    //단어장 생성
    public void createWordSet(User user, WordSetCreateRequest req) {
        WordSet wordSet = WordSet.builder()
                .name(req.name())
                .language(Language.fromCode(req.languageCode()))
                .isDefault(false)
                .user(user)
                .build();
        wordSetRepository.save(wordSet);
    }

    // 단어장 이름 변경
    public void updateWordSetName(Long wordSetId, String newName) {
        WordSet wordSet = wordSetRepository.findById(wordSetId)
                .orElseThrow(() -> new IllegalArgumentException("단어장을 찾을 수 없습니다."));
        wordSet.updateName(newName);

        wordSetRepository.save(wordSet);
    }

    // 단어장 목록 조회
    public List<WordSetSummaryResponse> getAllWordSetSummariesByUser(User user) {
        List<WordSet> wordSets = wordSetRepository.findAllByUser(user);
        return wordSets.stream()
                .map(wordSet -> WordSetSummaryResponse.builder()
                        .id(wordSet.getId())
                        .name(wordSet.getName())
                        .isDefault(wordSet.getIsDefault())
                        .build())
                .collect(Collectors.toList());
    }

    // 단어장 삭제
    public void deleteWordSet(Long wordSetId) {
        wordSetRepository.deleteById(wordSetId);
    }

    public void setDefaultWordSet(User user, Long wordSetId) {
        WordSet wordSetToSetDefault = wordSetRepository.findById(wordSetId)
                .orElseThrow(() -> new IllegalArgumentException("WordSet not found or does not belong to the user"));

        // 기존의 기본 단어장을 찾아서 기본 설정을 해제
        wordSetRepository.findByUserAndLanguageAndIsDefaultTrue(user, wordSetToSetDefault.getLanguage())
                .ifPresent(currentDefault -> {
                    currentDefault.updateDefault(false);
                    wordSetRepository.save(currentDefault);
                });

        // 새 기본 단어장 설정
        wordSetToSetDefault.updateDefault(true);
        wordSetRepository.save(wordSetToSetDefault);
    }
}
