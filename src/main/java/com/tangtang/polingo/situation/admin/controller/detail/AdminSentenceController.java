package com.tangtang.polingo.situation.admin.controller.detail;

import com.tangtang.polingo.situation.admin.dto.detail.SentenceUpdateRequest;
import com.tangtang.polingo.situation.admin.service.detail.AdminSentenceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "어드민 상황별 예문 문장 API", description = "어드민용 상세 상활 별 문장 관리하는 컨트롤러입니다.")
@RestController
@RequestMapping("/api/admin/sentence")
@RequiredArgsConstructor
public class AdminSentenceController {
    private final AdminSentenceService adminSentenceService;

    @Operation(summary = "기존의 id에 해당하는 문장을 변경합니다,", description = "변경하고자 하는 문장의 id 작성")
    @PutMapping("/{sentenceId}")
    public ResponseEntity<Void> updateSentence(@PathVariable Long sentenceId, @RequestBody SentenceUpdateRequest request) {
        adminSentenceService.updateSentence(sentenceId, request);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "기존의 id에 해당하는 문장을 삭제합니다,", description = "문장 삭제")
    @DeleteMapping("/{sentenceId}")
    public ResponseEntity<Void> deleteSentence(@PathVariable Long sentenceId) {
        adminSentenceService.deleteSentence(sentenceId);
        return ResponseEntity.ok().build();
    }
}
