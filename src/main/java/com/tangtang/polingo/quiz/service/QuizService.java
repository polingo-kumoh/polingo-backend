package com.tangtang.polingo.quiz.service;

import com.tangtang.polingo.global.constant.Language;
import com.tangtang.polingo.quiz.dto.QuizResponse;
import com.tangtang.polingo.quiz.entity.UserWord;
import com.tangtang.polingo.quiz.repository.UserWordRepository;
import com.tangtang.polingo.user.entity.User;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final UserWordRepository userWordRepository;
    private final Map<Long, List<QuizResponse>> quizzes = new ConcurrentHashMap<>();

    public List<QuizResponse> createQuizzes(User user, String code) {
        Language language = Language.fromCode(code);

        List<UserWord> userWords = userWordRepository.findByUserIdAndLanguage(user.getId(), language);
        Collections.shuffle(userWords);  // 단어 목록을 랜덤하게 섞습니다.
        List<QuizResponse> generatedQuizzes = new ArrayList<>();

        int quizCount = Math.min(userWords.size(), 10); // 최대 10개의 퀴즈를 생성
        for (int i = 0; i < quizCount; i++) {
            UserWord userWord = userWords.get(i);
            List<String> options = generateOptions(userWord.getWord().getDescription(), userWords);
            options.add(userWord.getWord().getDescription());
            Collections.shuffle(options);

            QuizResponse quiz = QuizResponse.builder()
                    .question(userWord.getWord().getText())
                    .options(options)
                    .correctAnswer(userWord.getWord().getDescription())
                    .build();

            generatedQuizzes.add(quiz);
        }

        quizzes.put(user.getId(), generatedQuizzes);
        return generatedQuizzes;
    }

    private List<String> generateOptions(String correctWord, List<UserWord> userWords) {
        return userWords.stream()
                .filter(userWord -> !userWord.getWord().getDescription().equals(correctWord))
                .map(userWord -> userWord.getWord().getDescription())
                .distinct()
                .limit(3)
                .collect(Collectors.toList());
    }
}
