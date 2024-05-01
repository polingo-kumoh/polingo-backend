package com.tangtang.polingo.quiz.service;

import com.tangtang.polingo.quiz.cache.OptionCache;
import com.tangtang.polingo.quiz.cache.OptionPool;
import com.tangtang.polingo.quiz.entity.Quiz;
import com.tangtang.polingo.quiz.entity.Quiz.Option;
import com.tangtang.polingo.quiz.util.IdGenerator;
import com.tangtang.polingo.word.entity.Word;
import com.tangtang.polingo.wordset.entity.WordSetWord;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QuizGenerator {
    private static final int MAX_QUIZ_SIZE = 10; // 최대 문제 개수
    private static final int OPTION_SIZE_PER_QUESTION = 3; // 문제 당 선택지 수

    private final OptionCache optionCache;
    private IdGenerator idGenerator;

    public List<Quiz> generateQuizzes(Set<WordSetWord> wordSetWords) {
        List<String> descriptions = optionCache.getShuffledOptions(MAX_QUIZ_SIZE * OPTION_SIZE_PER_QUESTION);
        idGenerator = new IdGenerator();
        OptionPool optionPool = new OptionPool(descriptions);

        List<Word> questionWords = wordSetWords.stream()
                .map(WordSetWord::getWord)
                .collect(Collectors.toList());

        int quizSize = Math.min(questionWords.size(), MAX_QUIZ_SIZE);
        questionWords = questionWords.subList(0, quizSize);

        return questionWords.stream()
                .map(word -> createQuiz(word, optionPool))
                .collect(Collectors.toList());
    }

    private Quiz createQuiz(Word word, OptionPool optionPool) {
        List<String> optionDescriptions = optionPool.getNextOptions(OPTION_SIZE_PER_QUESTION);

        idGenerator.reset();

        List<Option> options = optionDescriptions.stream()
                .map(desc -> Option.builder()
                        .optionId(idGenerator.generateOptionId())
                        .text(desc)
                        .build())
                .collect(Collectors.toList());

        Option correctOption = Option.builder()
                .optionId(idGenerator.generateOptionId())
                .text(word.getDescription())
                .build();

        options.add(correctOption);

        Collections.sort(options);

        return Quiz.builder()
                .id(idGenerator.generateQuizId())
                .question(word.getText())
                .options(options)
                .correctAnswer(correctOption)
                .build();
    }
}
