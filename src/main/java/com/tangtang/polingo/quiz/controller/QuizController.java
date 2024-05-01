package com.tangtang.polingo.quiz.controller;

import com.tangtang.polingo.quiz.dto.QuizResponse;
import com.tangtang.polingo.quiz.service.QuizService;
import com.tangtang.polingo.security.security.annotation.CurrentUser;
import com.tangtang.polingo.user.entity.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "퀴즈 관련 API", description = "퀴즈 생성 및 제출 후 채점을 수행합니다.")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/quiz")
public class QuizController {
    private final QuizService quizService;

    @GetMapping("/{code}/create")
    public ResponseEntity<List<QuizResponse>> createQuizzes(@PathVariable String code, @CurrentUser User user) {
        List<QuizResponse> quizzes = quizService.createQuizzes(user, code);
        return ResponseEntity.ok(quizzes);
    }
}
