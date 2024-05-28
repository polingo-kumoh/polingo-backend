package com.tangtang.polingo.news.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "어드민 뉴스 제어 API", description = "어드민용 뉴스 제어 API 입니다. languageCode는 [en/ja]  둘중 하나로 선택하시면 됩니다.")
@Slf4j
@RestController
@RequestMapping("/api/admin/news")
@RequiredArgsConstructor
public class AdminNewsController {

    @DeleteMapping("/{newsId}")
    @Operation(summary = "뉴스 단건 조회 API", description = "뉴스 ID로 뉴스의 상세 정보와 관련 문장들을 조회합니다.")
    public void deleteNews(@PathVariable String newsId){

    }


}
