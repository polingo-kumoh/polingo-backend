package com.tangtang.polingo.situation.admin.controller.situation;

import com.tangtang.polingo.situation.admin.dto.detail.AdminSituationDetailResponse;
import com.tangtang.polingo.situation.admin.dto.detail.DetailedSituationRequest;
import com.tangtang.polingo.situation.admin.dto.situation.AdminSituationListResponse;
import com.tangtang.polingo.situation.admin.dto.situation.AdminSituationPostRequest;
import com.tangtang.polingo.situation.admin.dto.situation.SituationUpdateRequest;
import com.tangtang.polingo.situation.admin.service.situation.AdminSituationService;
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


@Tag(name = "어드민 상황별 예문 API(situation)", description = "어드민용 상황(큰 상황)을 관리하는 컨트롤러입니다.")
@RestController
@RequestMapping("/api/admin/situation")
@RequiredArgsConstructor
public class AdminSituationController {
    private final AdminSituationService adminSituationService;

    @GetMapping
    @Operation(summary = "카테고리별 전체 상황 조회 API", description = "전체 상황 조회를 카테고리별로, 페이지네이션으로 제공합니다.")
    public Page<AdminSituationListResponse> getList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam List<Category> categories) {
        Pageable pageable = PageRequest.of(page, size);
        return adminSituationService.getSituationsByCategories(categories, pageable);
    }

    @PostMapping
    @Operation(summary = "상황 추가 API", description = "상황을 추가합니다.")
    public ResponseEntity<Void> addSituation(@RequestBody AdminSituationPostRequest reqBody) {
        adminSituationService.addSituation(reqBody);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "상활 상세조회 API", description = "id의 상황에 대한 모든 데이터를 조회합니다. </br>"
            + "이 API를 통해 이미지, 상세 상황, 문장에 접근하기 위한 id를 획득합니다.ㄴ ")
    @GetMapping("/{situationId}")
    public AdminSituationDetailResponse getDetail(@PathVariable Long situationId) {
        return adminSituationService.getDetailedSituation(situationId);
    }

    @Operation(summary = "상황 내용 변경 API", description = "상황 내용을 변경하기 위한 API입니다.")
    @PutMapping("/{situationId}")
    public ResponseEntity<Void> updateSituation(@PathVariable Long situationId, @RequestBody SituationUpdateRequest request) {
        adminSituationService.updateSituation(situationId, request);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "상황 삭제 API", description = "상황 삭제 API, 실 사용데이터에 사용금지")
    @DeleteMapping("/{situationId}")
    public ResponseEntity<Void> delete(@PathVariable Long situationId) {
        adminSituationService.deleteSituation(situationId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "상세 상황 추가 API", description = "상황 id에 대한 상세 상황을 추가합니다.")
    @PostMapping("/{situationId}/detail")
    public ResponseEntity<Void> addDetailSituaion(@PathVariable Long situationId, @RequestBody
                                                  DetailedSituationRequest req) {
        adminSituationService.addDetailSituation(situationId, req);
        return ResponseEntity.ok().build();
    }
}
