package com.tangtang.polingo.wordset.controller;

import com.tangtang.polingo.global.dto.CommonResponse;
import com.tangtang.polingo.security.security.annotation.CurrentUser;
import com.tangtang.polingo.user.entity.User;
import com.tangtang.polingo.wordset.dto.WordSetCreateRequest;
import com.tangtang.polingo.wordset.dto.WordSetSummaryResponse;
import com.tangtang.polingo.wordset.dto.dto.InsertWordRequest;
import com.tangtang.polingo.wordset.dto.dto.WordSetDetailsResponse;
import com.tangtang.polingo.wordset.service.WordSetService;
import com.tangtang.polingo.wordset.service.WordSetWordService;
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

@Tag(name = "단어장 API", description = "단어 조회를 통해 얻은 데이터를 단어장에 저장하고 관리할 수 있습니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/wordset")
public class WordSetController {
    private final WordSetService wordSetService;
    private final WordSetWordService wordSetWordService;

    @PostMapping
    public CommonResponse createWordSet(@RequestBody WordSetCreateRequest wordSetCreateRequest,
                                        @CurrentUser User user) {
        wordSetService.createWordSet(user, wordSetCreateRequest);
        return new CommonResponse(HttpStatus.OK.value(), "단어장을 생성했습니다.");
    }

    @PutMapping("/{wordSetId}")
    @PreAuthorize("hasPermission(#wordSetId, 'WordSet', 'write')")
    public CommonResponse updateWordSetName(@PathVariable Long wordSetId, @RequestParam String newName) {
        wordSetService.updateWordSetName(wordSetId, newName);
        return new CommonResponse(HttpStatus.OK.value(), "단어장의 이름을 변경하였습니다.");
    }

    @GetMapping
    public ResponseEntity<List<WordSetSummaryResponse>> getAllWordSetsByUserId(@CurrentUser User user) {
        List<WordSetSummaryResponse> wordSets = wordSetService.getAllWordSetSummariesByUser(user);
        return ResponseEntity.ok(wordSets);
    }

    @DeleteMapping("/{wordSetId}")
    @PreAuthorize("hasPermission(#wordSetId, 'WordSet', 'delete')")
    public CommonResponse deleteWordSet(@PathVariable Long wordSetId) {
        wordSetService.deleteWordSet(wordSetId);
        return new CommonResponse(HttpStatus.OK.value(), "단어장을 삭제하였습니다.");
    }

    @PutMapping("/{wordSetId}/set-default")
    @PreAuthorize("hasPermission(#wordSetId, 'WordSet', 'write')")
    public CommonResponse setDefaultWordSet(@CurrentUser User user, @PathVariable Long wordSetId) {
        wordSetService.setDefaultWordSet(user, wordSetId);
        return new CommonResponse(HttpStatus.OK.value(), "기본 단어장으로 설정되었습니다.");
    }

    @PostMapping("/{wordSetId}/insert-word")
    @PreAuthorize("hasPermission(#wordSetId, 'WordSet', 'write')")
    public CommonResponse insertWordInWordSet(@PathVariable Long wordSetId, @RequestBody InsertWordRequest req) {
        wordSetWordService.insertWord(wordSetId, req);
        return new CommonResponse(HttpStatus.OK.value(), "단어를 단어장에 삽입하였습니다.");
    }

    @GetMapping("/{wordSetId}/details")
    public ResponseEntity<WordSetDetailsResponse> getWordSetDetails(@PathVariable Long wordSetId) {
        WordSetDetailsResponse response = wordSetWordService.getWordSetDetails(wordSetId);

        return ResponseEntity.ok(response);
    }
}
