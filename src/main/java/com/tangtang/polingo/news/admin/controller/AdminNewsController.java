package com.tangtang.polingo.news.admin.controller;


import com.tangtang.polingo.news.admin.dto.NewsPostRequest;
import com.tangtang.polingo.news.admin.service.AdminNewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "어드민 뉴스 제어 API", description = "어드민용 뉴스 제어 API 입니다. languageCode는 [en/ja]  둘중 하나로 선택하시면 됩니다.")
@Slf4j
@RestController
@RequestMapping("/api/admin/news")
@RequiredArgsConstructor
public class AdminNewsController {
    private final AdminNewsService adminNewsService;

    @PostMapping("")
    @Operation(summary = "뉴스 추가 API", description = "뉴스 정보를 추가하는 API입니다.")
    public ResponseEntity<Void> saveNews(@RequestBody NewsPostRequest reqBody) {
        adminNewsService.saveNews(reqBody);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/{newsId}")
    @Operation(summary = "뉴스 수정 API", description = "뉴스 정보를 수정하는 API입니다.")
    public ResponseEntity<Void> updateNews(@PathVariable Long newsId, @RequestBody NewsPostRequest reqBody) {
        adminNewsService.updateNews(newsId, reqBody);
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{newsId}")
    @Operation(summary = "뉴스 제거 API", description = "뉴스 ID로 조회된 뉴스 정보를 제거합니다.")
    public ResponseEntity<Void> deleteNews(@PathVariable Long newsId) {
        adminNewsService.deleteNews(newsId);
        return ResponseEntity.ok().build();
    }
}
