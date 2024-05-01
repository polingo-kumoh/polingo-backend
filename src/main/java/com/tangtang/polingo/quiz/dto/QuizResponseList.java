package com.tangtang.polingo.quiz.dto;

import com.tangtang.polingo.quiz.entity.Quiz;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QuizResponseList {
    int count;
    List<Quiz> quizes;
}
