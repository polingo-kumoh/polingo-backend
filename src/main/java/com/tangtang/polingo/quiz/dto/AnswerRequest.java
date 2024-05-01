package com.tangtang.polingo.quiz.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerRequest {
    private List<QuizAnswer> answers;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class QuizAnswer {
        private int quizId;
        private int selectedOptionId;
    }
}

