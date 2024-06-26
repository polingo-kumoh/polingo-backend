package com.tangtang.polingo.word.admin.controller;


import com.tangtang.polingo.word.admin.service.AdminWordService;
import com.tangtang.polingo.word.dto.AdminWordPostRequest;
import com.tangtang.polingo.word.dto.AdminWordResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "어드민용 단어 API")
@Slf4j
@RestController
@RequestMapping("/api/admin/word")
@RequiredArgsConstructor
public class AdminWordController {
    private final AdminWordService adminWordService;

    @GetMapping("")
    @Operation(summary = "단어 리스트 조회 API", description = "단어 리스트 조회 API로, searchQuery가 있는 경우 해당 검색어를 englishWord, japaneseWord에서 검색해서 결과를 반환한다.")
    public Page<AdminWordResponse> getList(
            @RequestParam(required = false) String searchQuery,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) throws IOException {
        Pageable pageable = PageRequest.of(page, size);
        return adminWordService.getList(searchQuery, pageable);
    }

    @PostMapping("")
    @Operation(summary = "단어 추가 API")
    public void add(@RequestBody AdminWordPostRequest reqBody) throws IOException {
        adminWordService.add(reqBody);
    }

    @PutMapping("/{wordId}")
    @Operation(summary = "단어 수정 API")
    public void update(
            @PathVariable String wordId,
            @RequestBody AdminWordPostRequest reqBody
    ) throws IOException {
        adminWordService.update(wordId, reqBody);
    }

    @DeleteMapping("/{wordId}")
    @Operation(summary = "단어 제거 API")
    public void delete(@PathVariable String wordId) throws IOException {
        adminWordService.delete(wordId);
    }
}
