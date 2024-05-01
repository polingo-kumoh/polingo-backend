package com.tangtang.polingo.quiz.service;

import com.tangtang.polingo.quiz.dto.AnswerRequest;
import com.tangtang.polingo.quiz.dto.AnswerResponse;
import com.tangtang.polingo.quiz.dto.QuizResponseList;
import com.tangtang.polingo.quiz.entity.Quiz;
import com.tangtang.polingo.quiz.repository.QuizRepository;
import com.tangtang.polingo.user.entity.User;
import com.tangtang.polingo.wordset.entity.WordSet;
import com.tangtang.polingo.wordset.entity.WordSetWord;
import com.tangtang.polingo.wordset.repository.WordSetRepository;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuizService {
    private final WordSetRepository wordSetRepository;
    private final QuizGenerator quizGenerator;
    private final QuizRepository quizRepository;

    public QuizResponseList createQuizzes(Long wordSetId, User user) {
        WordSet wordSet = wordSetRepository.findById(wordSetId)
                .orElseThrow(() -> new IllegalArgumentException("단어장을 찾을 수 없습니다."));

        Set<WordSetWord> wordInWordSet = wordSet.getWordSetWords();

        List<Quiz> quizzes = quizGenerator.generateQuizzes(wordInWordSet);
        quizRepository.saveQuizAnswers(user.getId(), quizzes);

        return QuizResponseList.builder()
                .count(quizzes.size())
                .quizes(quizzes)
                .build();
    }

    public AnswerResponse checkAnswer(AnswerRequest req, User user) {
        Map<Integer, Integer> correctAnswers = quizRepository.getQuizAnswers(user.getId())
                .orElseThrow(() -> new IllegalStateException("해당 사용자의 퀴즈 정보를 찾을 수 없습니다."));

        Map<Boolean, List<Integer>> partitionedAnswers = req.getAnswers().stream()
                .collect(Collectors.partitioningBy(
                        answer -> isCorrect(answer, correctAnswers),
                        Collectors.mapping(AnswerRequest.QuizAnswer::getQuizId, Collectors.toList())
                ));

        List<Integer> correctQuizIds = partitionedAnswers.get(true);
        List<Integer> incorrectQuizIds = partitionedAnswers.get(false);

        quizRepository.deleteQuizAnswers(user.getId());

        return AnswerResponse.builder()
                .correctCount(correctQuizIds.size())
                .correctQuizIds(correctQuizIds)
                .incorrectQuizIds(incorrectQuizIds)
                .incorrectCount(incorrectQuizIds.size())
                .build();
    }

    private boolean isCorrect(AnswerRequest.QuizAnswer answer, Map<Integer, Integer> correctAnswers) {
        return correctAnswers.get(answer.getQuizId()).equals(answer.getSelectedOptionId());
    }
}
