package com.tangtang.polingo.wordset.service;

import com.tangtang.polingo.global.constant.Language;
import com.tangtang.polingo.user.entity.User;
import com.tangtang.polingo.wordset.dto.WordSetCreateRequest;
import com.tangtang.polingo.wordset.dto.WordSetSummaryResponse;
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
                .orElseThrow(() -> new IllegalArgumentException("WordSet not found"));
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
                        .isDefault(wordSet.isDefault())
                        .build())
                .collect(Collectors.toList());
    }

    // 단어장 삭제
    public void deleteWordSet(Long wordSetId) {
        wordSetRepository.deleteById(wordSetId);
    }
}
