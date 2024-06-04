package com.tangtang.polingo.situation.admin.service.detail;

import com.tangtang.polingo.situation.admin.dto.detail.ImageUpdateRequest;
import com.tangtang.polingo.situation.entity.SituationImage;
import com.tangtang.polingo.situation.repository.ImageRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminImageService {
    private final ImageRepository imageRepository;

    public void updateImage(Long imageId, ImageUpdateRequest request) {
        SituationImage image = imageRepository.findById(imageId)
                .orElseThrow(() -> new EntityNotFoundException("Image not found"));
        image.updateUrl(request.getUrl());
        imageRepository.save(image);
    }

    public void deleteImage(Long imageId) {
        imageRepository.deleteById(imageId);
    }
}
