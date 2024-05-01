package com.tangtang.polingo.quiz.controller;

import com.tangtang.polingo.quiz.dto.AnswerRequest;
import com.tangtang.polingo.quiz.dto.AnswerResponse;
import com.tangtang.polingo.quiz.dto.QuizResponseList;
import com.tangtang.polingo.quiz.service.QuizService;
import com.tangtang.polingo.security.security.annotation.CurrentUser;
import com.tangtang.polingo.user.entity.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "퀴즈 관련 API", description = "퀴즈 생성 및 제출 후 채점을 수행합니다.")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/quiz")
public class QuizController {
    private final QuizService quizService;

    @GetMapping("/{wordSetId}/create")
    public ResponseEntity<QuizResponseList> createQuizzes(@PathVariable Long wordSetId, @CurrentUser User user) {
        QuizResponseList quizzes = quizService.createQuizzes(wordSetId, user);
        return ResponseEntity.ok(quizzes);
    }

    @PostMapping("/submit")
    public ResponseEntity<AnswerResponse> submitAnswer(@RequestBody AnswerRequest req, @CurrentUser User user) {
        AnswerResponse response = quizService.checkAnswer(req, user);
        return ResponseEntity.ok(response);
    }
}
