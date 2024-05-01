package com.tangtang.polingo.quiz.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AnswerResponse {
    // 정답 수
    private int correctCount;
    // 맞힌 퀴즈 ID 목록
    private List<Integer> correctQuizIds;
    // 오답 수
    private int incorrectCount;
    // 틀린 퀴즈 ID 목록
    private List<Integer> incorrectQuizIds;
}

