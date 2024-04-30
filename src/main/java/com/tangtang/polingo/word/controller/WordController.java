package com.tangtang.polingo.word.controller;


import com.tangtang.polingo.word.dto.WordMeaningResponse;
import com.tangtang.polingo.word.service.wordService;
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

@Tag(name = "단어조회 API", description = "번역 기능, 뉴스 보기 등의 문장에서 단어에 대한 뜻을 제공하는 api입니다. code : [en/ja] ")
@Slf4j
@RestController
@RequestMapping("/api/word")
@RequiredArgsConstructor
public class WordController {
    private final wordService wordService;

    @GetMapping("/{code}")
    @Operation(summary = "단어 조회 API", description = "단어를 조회합니다.")
    public ResponseEntity<WordMeaningResponse> getWordInDictionary(@PathVariable("code") String code,
                                                                   @RequestParam String word) {
        WordMeaningResponse response = wordService.findWordMeaning(code, word);
        return ResponseEntity.ok(response);
    }
}
