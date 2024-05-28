package com.tangtang.polingo.situation.controller;

import com.tangtang.polingo.situation.constants.SituationCategory;
import com.tangtang.polingo.situation.dto.AdminSituationDetailResponse;
import com.tangtang.polingo.situation.dto.AdminSituationListResponse;
import com.tangtang.polingo.situation.dto.AdminStiuationPostRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "어드민 상황별 예문 API", description = "어드민용 예문 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/situation")
public class AdminSituationController {


    @PostMapping("")
    @Operation(summary = "상황별 예문 추가 API")
    public void add(@RequestBody AdminStiuationPostRequest reqBody){

    }


    @GetMapping("")
    @Operation(summary = "상황별 예문 목록 조회 API", description = "상황별 예문 목록 조회 API, 카테고리로 필터링이 가능하다.")
    public Page<AdminSituationListResponse> getList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam List<SituationCategory> categories
    ){
        return null;
    }


    @GetMapping("/{situationId}")
    @Operation(summary = "상황별 예문 상세 조회 API", description = "상황별 예문 상세 조회 API")
    public AdminSituationDetailResponse getDetail(
            @PathVariable String situationId
    ){
        return null;
    }



}
