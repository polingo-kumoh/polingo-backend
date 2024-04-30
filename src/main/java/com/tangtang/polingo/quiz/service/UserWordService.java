package com.tangtang.polingo.quiz.service;

import com.tangtang.polingo.global.constant.Language;
import com.tangtang.polingo.quiz.entity.UserWord;
import com.tangtang.polingo.quiz.repository.UserWordRepository;
import com.tangtang.polingo.user.entity.User;
import com.tangtang.polingo.word.entity.Word;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserWordService {
    private final UserWordRepository userWordRepository;
    public void createUserWord(User user, Word word, Language language) {
        UserWord userWord = userWordRepository.findByUserIdAndWordId(user.getId(), word.getId())
                .orElseGet(() -> UserWord.builder()
                        .user(user)
                        .word(word)
                        .language(language)
                        .count(0)
                        .build());

        userWord.increaseCount();
        userWordRepository.save(userWord);
    }

    public void deleteUserWordOrDecreaseCount(Long userId, Long wordId) {
        UserWord userWord = userWordRepository.findByUserIdAndWordId(userId, wordId)
                .orElseThrow(() -> new IllegalArgumentException("사용자 단어 정보를 찾을 수 없습니다."));

        if (userWord.decreaseCount()) {
            userWordRepository.save(userWord);
        } else {
            userWordRepository.delete(userWord);
        }
    }
}
