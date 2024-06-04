package com.tangtang.polingo.situation.admin.controller.detail;

import com.tangtang.polingo.situation.admin.dto.detail.DetailedSituationRequest;
import com.tangtang.polingo.situation.admin.dto.detail.ImageUrlsRequest;
import com.tangtang.polingo.situation.admin.dto.detail.SentenceCreateRequest;
import com.tangtang.polingo.situation.admin.service.detail.AdminDetailedSituationService;
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

@Slf4j
@Tag(name = "어드민 상황별 예문 API(detail-situation)", description = "어드민용 상황(상세 상황)을 관리하는 컨트롤러입니다.")
@RestController
@RequestMapping("/api/admin/detailed-situation")
@RequiredArgsConstructor
public class AdminDetailedSituationController {
    private final AdminDetailedSituationService adminDetailedSituationService;

    @Operation(summary = "상세 상황 이미지 추가 API", description = "상세 상황에 대해서 이미지를 추가하는 API입니다.")
    @PostMapping("/{detailSituationId}/images")
    public ResponseEntity<Void> addImagesToDetailedSituation(
            @PathVariable Long detailSituationId,
            @RequestBody ImageUrlsRequest req) {
        adminDetailedSituationService.addImagesToDetailedSituation(detailSituationId, req.imageList());
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "상세 상황 문장 추가 API", description = "상세 상황에 대해서 문장을 추가하는 API입니다.")
    @PostMapping("/{detailSituationId}/sentance")
    public ResponseEntity<Void> addSentenceToDetailedSituation(
            @PathVariable Long detailSituationId,
            @RequestBody SentenceCreateRequest sentenceRequests) {
        log.info("req = {}", sentenceRequests);
        adminDetailedSituationService.addSentencesToDetailedSituation(detailSituationId, sentenceRequests);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "상세 상황을 변경하는 API입니다.", description = "상세 상황의 명칭을 변경합니다.")
    @PutMapping("/{detailSituationId}")
    public ResponseEntity<Void> update(
            @PathVariable Long detailSituationId,
            @RequestBody DetailedSituationRequest req) {
        log.info("req = {}", req);
        adminDetailedSituationService.updateDetailedSituation(detailSituationId, req);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "상세 상황을 삭제하는 API입니다.", description = "상세 상황을 삭제합니다.")
    @DeleteMapping("/{detailSituationId}")
    public ResponseEntity<Void> delete(@PathVariable Long detailSituationId) {
        adminDetailedSituationService.deleteDetailedSituation(detailSituationId);
        return ResponseEntity.ok().build();
    }
}
