package com.tangtang.polingo.news.controller;


import com.tangtang.polingo.news.dto.NewsPostRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Tag(name = "어드민 뉴스 제어 API", description = "어드민용 뉴스 제어 API 입니다. languageCode는 [en/ja]  둘중 하나로 선택하시면 됩니다.")
@Slf4j
@RestController
@RequestMapping("/api/admin/news")
@RequiredArgsConstructor
public class AdminNewsController {


    @PostMapping("")
    @Operation(summary = "뉴스 추가 API", description = "뉴스 정보를 추가하는 API입니다.")
    public void saveNews(@RequestBody NewsPostRequest reqBody){

    }



    @PutMapping("/{newsId}")
    @Operation(summary = "뉴스 수정 API", description = "뉴스 정보를 수정하는 API입니다.")
    public void updateNews(@PathVariable String newsId, @RequestBody NewsPostRequest reqBody){

    }




    @DeleteMapping("/{newsId}")
    @Operation(summary = "뉴스 제거 API", description = "뉴스 ID로 조회된 뉴스 정보를 제거합니다.")
    public void deleteNews(@PathVariable String newsId){

    }


}
