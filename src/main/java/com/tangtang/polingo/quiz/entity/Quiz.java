package com.tangtang.polingo.quiz.entity;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Quiz {
    private int id;
    private String question;
    private List<Option> options;
    private Option correctAnswer;

    public int getCorrectId() {
        return correctAnswer.getOptionId();
    }

    @Getter
    @Builder
    public static class Option implements Comparable<Option> {
        private int optionId;
        private String text;

        @Override
        public int compareTo(Option o) {
            return Integer.compare(optionId, o.optionId);
        }
    }
}
