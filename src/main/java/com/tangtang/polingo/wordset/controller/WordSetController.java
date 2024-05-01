package com.tangtang.polingo.wordset.controller;

import com.tangtang.polingo.global.dto.CommonResponse;
import com.tangtang.polingo.security.security.annotation.CurrentUser;
import com.tangtang.polingo.user.entity.User;
import com.tangtang.polingo.wordset.dto.wordset.WordSetCreateRequest;
import com.tangtang.polingo.wordset.dto.wordset.WordSetSummaryResponse;
import com.tangtang.polingo.wordset.service.WordSetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "단어장 API", description = "단어장의 생성 및 조작을 수행합니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/wordset")
public class WordSetController {
    private final WordSetService wordSetService;

    @Operation(summary = "단어장 생성 API", description = "새로운 단어장을 생성합니다.")
    @PostMapping
    public CommonResponse createWordSet(@RequestBody WordSetCreateRequest wordSetCreateRequest,
                                        @CurrentUser User user) {
        wordSetService.createWordSet(user, wordSetCreateRequest);
        return new CommonResponse(HttpStatus.OK.value(), "단어장을 생성했습니다.");
    }

    @Operation(summary = "단어장 이름 변경 API", description = "지정된 단어장의 이름을 변경합니다.")
    @PutMapping("/{wordSetId}")
    @PreAuthorize("hasPermission(#wordSetId, 'WordSet', 'write')")
    public CommonResponse updateWordSetName(@PathVariable Long wordSetId, @RequestParam String newName) {
        wordSetService.updateWordSetName(wordSetId, newName);
        return new CommonResponse(HttpStatus.OK.value(), "단어장의 이름을 변경하였습니다.");
    }

    @Operation(summary = "단어장 목록 조회 API", description = "사용자의 모든 단어장을 조회합니다.")
    @GetMapping
    public ResponseEntity<List<WordSetSummaryResponse>> getAllWordSetsByUserId(@CurrentUser User user) {
        List<WordSetSummaryResponse> wordSets = wordSetService.getAllWordSetSummariesByUser(user);
        return ResponseEntity.ok(wordSets);
    }

    @Operation(summary = "단어장 삭제 API", description = "지정된 단어장을 삭제합니다.")
    @DeleteMapping("/{wordSetId}")
    @PreAuthorize("hasPermission(#wordSetId, 'WordSet', 'delete')")
    public CommonResponse deleteWordSet(@PathVariable Long wordSetId) {
        wordSetService.deleteWordSet(wordSetId);
        return new CommonResponse(HttpStatus.OK.value(), "단어장을 삭제하였습니다.");
    }

    @Operation(summary = "기본 단어장 설정 API", description = "지정된 단어장을 기본 단어장으로 설정합니다.")
    @PutMapping("/{wordSetId}/set-default")
    @PreAuthorize("hasPermission(#wordSetId, 'WordSet', 'write')")
    public CommonResponse setDefaultWordSet(@CurrentUser User user, @PathVariable Long wordSetId) {
        wordSetService.setDefaultWordSet(user, wordSetId);
        return new CommonResponse(HttpStatus.OK.value(), "기본 단어장으로 설정되었습니다.");
    }
}
