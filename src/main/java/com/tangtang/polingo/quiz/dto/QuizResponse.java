package com.tangtang.polingo.quiz.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QuizResponse {
    private String question;
    private List<String> options;
    private String correctAnswer;
}
