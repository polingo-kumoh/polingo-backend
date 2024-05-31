package com.tangtang.polingo.situation.admin.controller;

import com.tangtang.polingo.situation.admin.dto.AdminSituationDetailResponse;
import com.tangtang.polingo.situation.admin.dto.AdminSituationListResponse;
import com.tangtang.polingo.situation.admin.dto.AdminStiuationPostRequest;
import com.tangtang.polingo.situation.admin.service.AdminSituationService;
import com.tangtang.polingo.situation.constants.SituationCategory;
import com.tangtang.polingo.situation.entity.Category;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "어드민 상황별 예문 API", description = "어드민용 예문 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/situation")
public class AdminSituationController {
    private final AdminSituationService adminSituationService;

    @PostMapping("")
    @Operation(summary = "상황별 예문 추가 API")
    public ResponseEntity<Void> add(@RequestBody AdminStiuationPostRequest reqBody) {
        adminSituationService.addSituation(reqBody);
        return ResponseEntity.ok().build();
    }


    @GetMapping("")
    @Operation(summary = "상황별 예문 목록 조회 API", description = "상황별 예문 목록 조회 API, 카테고리로 필터링이 가능하다.")
    public Page<AdminSituationListResponse> getList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam List<Category> categories
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return adminSituationService.getSituationsByCategories(categories, pageable);
    }


    @GetMapping("/{situationId}")
    @Operation(summary = "상황별 예문 상세 조회 API", description = "상황별 예문 상세 조회 API")
    public AdminSituationDetailResponse getDetail(@PathVariable Long situationId) {
        return adminSituationService.getDetailedSituation(situationId);
    }

    @PutMapping("/{situationId}")
    @Operation(summary = "상황별 예문 수정 API")
    public void update(@PathVariable String situationId, @RequestBody AdminStiuationPostRequest reqBody
    ) {

    }

    @DeleteMapping("/{situationId}")
    @Operation(summary = "상황별 예문 삭제 API")
    public void delete(@PathVariable String situationId) {

    }

}
