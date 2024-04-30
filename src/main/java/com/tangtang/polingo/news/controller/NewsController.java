package com.tangtang.polingo.news.controller;


import com.tangtang.polingo.global.dto.CommonResponse;
import com.tangtang.polingo.news.dto.NewsDetailResponse;
import com.tangtang.polingo.news.dto.NewsSummaryResponse;
import com.tangtang.polingo.news.service.NewsService;
import com.tangtang.polingo.security.security.annotation.CurrentUser;
import com.tangtang.polingo.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "뉴스 관련 API", description = "사용자와 뉴스의 상호작용에 대해서 다룹니다. languageCode는 [en/ja]  둘줄 하나로 선택하시면 됩니다.")
@Slf4j
@RestController
@RequestMapping("/api/news")
@RequiredArgsConstructor
public class NewsController {
    private final NewsService newsService;

    @GetMapping("")
    @Operation(summary = "뉴스 목록 조회 API", description = "페이지당 뉴스 목록을 최신순으로 조회합니다. 언어로 필터링 가능합니다.")
    public ResponseEntity<Page<NewsSummaryResponse>> getNewses(
            @RequestParam(required = false) String languageCode,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @CurrentUser User user
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<NewsSummaryResponse> newsPage = newsService.getNewsSummaries(languageCode, pageable, user);
        return ResponseEntity.ok(newsPage);
    }


    @GetMapping("/{newsId}")
    @Operation(summary = "뉴스 단건 조회 API", description = "뉴스 ID로 뉴스의 상세 정보와 관련 문장들을 조회합니다.")
    public ResponseEntity<NewsDetailResponse> getNews(@PathVariable Long newsId, @CurrentUser User user) {
        NewsDetailResponse newsDetail = newsService.getNewsDetail(newsId, user);
        return ResponseEntity.ok(newsDetail);
    }

    @PostMapping("/scrap/{newsId}")
    @Operation(summary = "뉴스 스크랩 API", description = "뉴스 ID로 스크랩을 합니다.")
    public ResponseEntity<CommonResponse> scrapNews(@CurrentUser User user, @PathVariable Long newsId) {
        newsService.scrapNews(user, newsId);
        return ResponseEntity.ok(new CommonResponse(HttpStatus.OK.value(), "뉴스를 스크랩하였습니다."));
    }

    @GetMapping("/scrap")
    @Operation(summary = "스크랩한 뉴스 목록 조회 API", description = "스크랩한 뉴스를 조회합니다. 언어로 필터링 가능합니다.")
    public ResponseEntity<Page<NewsSummaryResponse>> getScrapedNewses(
            @CurrentUser User user,
            @RequestParam(required = false) String languageCode,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<NewsSummaryResponse> newsPage = newsService.getScrapedNews(user, languageCode, pageable);
        return ResponseEntity.ok(newsPage);
    }

    @DeleteMapping("/scrap/{newsId}")
    @Operation(summary = "뉴스 스크랩 취소 API", description = "뉴스 ID로 스크랩 취소를 합니다.")
    public ResponseEntity<CommonResponse> deleteScrap(@CurrentUser User user, @PathVariable Long newsId) {
        newsService.unscrapNews(user, newsId);
        return ResponseEntity.ok(new CommonResponse(HttpStatus.OK.value(), "뉴스 스크랩을 취소하였습니다."));
    }
}
