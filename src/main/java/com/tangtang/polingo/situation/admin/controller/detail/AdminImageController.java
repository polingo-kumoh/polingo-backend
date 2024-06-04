package com.tangtang.polingo.situation.admin.controller.detail;

import com.tangtang.polingo.situation.admin.dto.detail.ImageUpdateRequest;
import com.tangtang.polingo.situation.admin.service.detail.AdminImageService;
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

@Tag(name = "어드민 상황별 예문 이미지 API", description = "어드민용 상세 상활 별 이미지를 관리하는 컨트롤러입니다.")
@RestController
@RequestMapping("/api/admin/image")
@RequiredArgsConstructor
public class AdminImageController {
    private final AdminImageService adminImageService;

    @Operation(summary = "기존의 id에 해당하는 이미지를 변경합니다.", description = "변경하고자 하는 이미지의 id를 작성합니다.")
    @PutMapping("/{imageId}")
    public ResponseEntity<Void> updateImage(@PathVariable Long imageId, @RequestBody ImageUpdateRequest request) {
        adminImageService.updateImage(imageId, request);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "기존의 id에 해당하는 이미지를 삭제합니다.", description = "변경하고자 하는 이미지를 삭제합니다.")
    @DeleteMapping("/{imageId}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long imageId) {
        adminImageService.deleteImage(imageId);
        return ResponseEntity.ok().build();
    }
}
