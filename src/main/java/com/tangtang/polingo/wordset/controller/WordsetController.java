package com.tangtang.polingo.wordset.controller;


import com.tangtang.polingo.wordset.dto.WordMeaningResponse;
import com.tangtang.polingo.wordset.service.WordSetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "단어장 API", description = "단어장 관련 API를 제공합니다. ")
@Slf4j
@RestController
@RequestMapping("/api/wordset")
@RequiredArgsConstructor
public class WordsetController {
    private final WordSetService wordSetService;

    @GetMapping("/{code}")
    @Operation(summary = "단어 조회 API", description = "단어를 조회합니다.")
    public ResponseEntity<WordMeaningResponse> getWordInDictionary(@PathVariable("code") String code,
                                                                   @RequestParam String word) {
        WordMeaningResponse response = wordSetService.findWordMeaning(code, word);
        return ResponseEntity.ok(response);
    }
}
