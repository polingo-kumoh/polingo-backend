package com.tangtang.polingo.wordset.controller;

import com.tangtang.polingo.global.dto.CommonResponse;
import com.tangtang.polingo.wordset.dto.wordsetword.InsertWordRequest;
import com.tangtang.polingo.wordset.dto.wordsetword.WordSetDetailsResponse;
import com.tangtang.polingo.wordset.service.WordSetWordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "단어장 내부 단어 API", description = "단어 조회를 통해 얻은 데이터를 단어장에 저장하고 관리할 수 있습니다. wordSetId는 단어장의 id, wordId는 단어의 id")
@RestController
@RequestMapping("/api/wordset/{wordSetId}/words")
@RequiredArgsConstructor
public class WordSetWordController {
    private final WordSetWordService wordSetWordService;

    @Operation(summary = "단어 삽입 API", description = "지정된 단어장에 새로운 단어를 삽입합니다.")
    @PostMapping
    @PreAuthorize("hasPermission(#wordSetId, 'WordSet', 'write')")
    public CommonResponse insertWordInWordSet(@PathVariable Long wordSetId, @RequestBody InsertWordRequest req) {
        wordSetWordService.insertWord(wordSetId, req);
        return new CommonResponse(HttpStatus.OK.value(), "단어를 단어장에 삽입하였습니다.");
    }

    @Operation(summary = "단어장 상세 조회 API", description = "지정된 단어장의 상세 내용을 조회합니다, 포함된 단어의 목록을 포함합니다.")
    @GetMapping("/details")
    public ResponseEntity<WordSetDetailsResponse> getWordSetDetails(@PathVariable Long wordSetId) {
        WordSetDetailsResponse response = wordSetWordService.getWordSetDetails(wordSetId);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "단어 삭제 API", description = "지정된 단어장에서 특정 단어를 삭제합니다.")
    @DeleteMapping("/{wordId}")
    @PreAuthorize("hasPermission(#wordSetId, 'WordSet', 'write')")
    public CommonResponse deleteWordFromWordSet(@PathVariable Long wordSetId, @PathVariable Long wordId) {
        wordSetWordService.deleteWordFromWordSet(wordSetId, wordId);
        return new CommonResponse(HttpStatus.OK.value(), "단어장에서 해당 단어를 삭제했습니다.");
    }

    @Operation(summary = "단어 이동 API", description = "하나의 단어장에서 다른 단어장으로 특정 단어를 이동시킵니다. targetWordSetId는 이동시킬 단어장의 id를 의미합니다.")
    @PostMapping("/{wordId}/move-to/{targetWordSetId}")
    @PreAuthorize("hasPermission(#wordSetId, 'WordSet', 'write') and hasPermission(#targetWordSetId, 'WordSet', 'write')")
    public CommonResponse moveWordToAnotherWordSet(@PathVariable Long wordSetId, @PathVariable Long wordId, @PathVariable Long targetWordSetId) {
        wordSetWordService.moveWordToAnotherWordSet(wordSetId, wordId, targetWordSetId);
        return new CommonResponse(HttpStatus.OK.value(), "단어장의 단어를 이동시켰습니다.");
    }
}
