package com.tangtang.polingo.word.controller;


import com.tangtang.polingo.word.dto.AdminWordResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "어드민용 단어 API")
@Slf4j
@RestController
@RequestMapping("/api/admin/word")
@RequiredArgsConstructor
public class AdminWordController {

    @GetMapping("")
    @Operation(summary = "단어 리스트 조회 API", description = "단어 리스트 조회 API로, searchQuery가 있는 경우 해당 검색어를 englishWord, japaneseWord에서 검색해서 결과를 반환한다.")
    public Page<AdminWordResponse> getList(
            @RequestParam String searchQuery,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        return null;
    }
}
