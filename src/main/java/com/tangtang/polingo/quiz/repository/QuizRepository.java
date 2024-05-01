package com.tangtang.polingo.quiz.repository;

import com.tangtang.polingo.quiz.entity.Quiz;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public class QuizRepository {
    private final ConcurrentHashMap<Long, Map<Integer, Integer>> quizAnswers = new ConcurrentHashMap<>();

    public void saveQuizAnswers(Long userId, List<Quiz> quizes) {
        Map<Integer, Integer> answerMap = quizes.stream()
                .collect(Collectors.toMap(Quiz::getId, Quiz::getCorrectId));

        quizAnswers.put(userId, answerMap);
    }

    public Optional<Map<Integer, Integer>> getQuizAnswers(Long userId) {
        return Optional.ofNullable(quizAnswers.get(userId));
    }

    public void deleteQuizAnswers(Long userId) {
        quizAnswers.remove(userId);
    }
}
