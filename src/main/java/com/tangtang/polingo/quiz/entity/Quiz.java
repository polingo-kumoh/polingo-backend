package com.tangtang.polingo.quiz.entity;

import com.tangtang.polingo.word.entity.Word;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Quiz {
    private Word question;
    private List<String> options;
    private String correctAnswer;
}
