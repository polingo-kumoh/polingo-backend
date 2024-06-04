package com.tangtang.polingo.situation.admin.controller;

import com.tangtang.polingo.situation.admin.dto.AdminSituationDetailResponse;
import com.tangtang.polingo.situation.admin.dto.AdminSituationListResponse;
import com.tangtang.polingo.situation.admin.dto.AdminStiuationPostRequest;
import com.tangtang.polingo.situation.admin.dto.DetailedSituationUpdateRequest;
import com.tangtang.polingo.situation.admin.dto.ImageUpdateRequest;
import com.tangtang.polingo.situation.admin.dto.SentenceUpdateRequest;
import com.tangtang.polingo.situation.admin.dto.SituationUpdateRequest;
import com.tangtang.polingo.situation.admin.service.AdminSituationService;
import com.tangtang.polingo.situation.entity.Category;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
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


@Tag(name = "어드민 상황별 예문 API", description = "어드민용 예문 API를 관리하는 컨트롤러입니다.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/situation")
public class AdminSituationController {
    private final AdminSituationService adminSituationService;

    @PostMapping("")
    @Operation(summary = "상황별 예문 추가", description = "새로운 상황별 예문을 추가합니다.")
    public ResponseEntity<Void> addSituation(@RequestBody AdminStiuationPostRequest reqBody) {
        adminSituationService.addSituation(reqBody);
        return ResponseEntity.ok().build();
    }

    @GetMapping("")
    @Operation(summary = "상황별 예문 목록 조회", description = "등록된 상황별 예문의 목록을 조회합니다. 카테고리로 필터링이 가능합니다.")
    public Page<AdminSituationListResponse> getList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam List<Category> categories
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return adminSituationService.getSituationsByCategories(categories, pageable);
    }

    @GetMapping("/{situationId}")
    @Operation(summary = "상황별 예문 상세 조회", description = "특정 상황별 예문의 상세 정보를 조회합니다.")
    public AdminSituationDetailResponse getDetail(@PathVariable Long situationId) {
        return adminSituationService.getDetailedSituation(situationId);
    }

    @PutMapping("/{situationId}")
    @Operation(summary = "상황 수정", description = "특정 상황의 정보를 수정합니다.")
    public ResponseEntity<Void> updateSituation(@PathVariable Long situationId,
                                                @RequestBody SituationUpdateRequest request) {
        adminSituationService.updateSituation(situationId, request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/detail/{detailSituationId}")
    @Operation(summary = "상세 상황 수정", description = "특정 상세 상황의 정보를 수정합니다.")
    public ResponseEntity<Void> updateDetailSituation(@PathVariable Long detailSituationId,
                                                      @RequestBody DetailedSituationUpdateRequest request) {
        adminSituationService.updateDetailedSituation(detailSituationId, request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/sentence/{sentenceId}")
    @Operation(summary = "문장 수정", description = "특정 문장의 정보를 수정합니다.")
    public ResponseEntity<Void> updateSentence(@PathVariable Long sentenceId,
                                               @RequestBody SentenceUpdateRequest request) {
        adminSituationService.updateSentence(sentenceId, request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/image/{imageId}")
    @Operation(summary = "이미지 수정", description = "특정 이미지의 정보를 수정합니다.")
    public ResponseEntity<Void> updateImage(@PathVariable Long imageId, @RequestBody ImageUpdateRequest request) {
        adminSituationService.updateImage(imageId, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/detail/{detailSituationId}")
    @Operation(summary = "상세 상황 삭제", description = "특정 상세 상황을 삭제합니다.")
    public ResponseEntity<Void> deleteDetailSituation(@PathVariable Long detailSituationId) {
        adminSituationService.deleteDetailedSituation(detailSituationId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/sentence/{sentenceId}")
    @Operation(summary = "문장 삭제", description = "특정 문장을 삭제합니다.")
    public ResponseEntity<Void> deleteSentence(@PathVariable Long sentenceId) {
        adminSituationService.deleteSentence(sentenceId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/image/{imageId}")
    @Operation(summary = "이미지 삭제", description = "특정 이미지를 삭제합니다.")
    public ResponseEntity<Void> deleteImage(@PathVariable Long imageId) {
        adminSituationService.deleteImage(imageId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{situationId}")
    @Operation(summary = "상황 삭제", description = "특정 상황을 삭제합니다.")
    public ResponseEntity<Void> delete(@PathVariable Long situationId) {
        adminSituationService.deleteSituation(situationId);
        return ResponseEntity.ok().build();
    }
}
