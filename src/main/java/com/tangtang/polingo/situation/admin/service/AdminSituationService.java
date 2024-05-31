package com.tangtang.polingo.situation.admin.service;

import com.tangtang.polingo.global.constant.Language;
import com.tangtang.polingo.situation.admin.dto.AdminSituationDetailResponse;
import com.tangtang.polingo.situation.admin.dto.AdminSituationListResponse;
import com.tangtang.polingo.situation.admin.dto.AdminStiuationPostRequest;
import com.tangtang.polingo.situation.admin.dto.DetailedSituationUpdateRequest;
import com.tangtang.polingo.situation.admin.dto.ImageUpdateRequest;
import com.tangtang.polingo.situation.admin.dto.SentenceUpdateRequest;
import com.tangtang.polingo.situation.admin.dto.SituationUpdateRequest;
import com.tangtang.polingo.situation.entity.Category;
import com.tangtang.polingo.situation.entity.DetailedSituation;
import com.tangtang.polingo.situation.entity.Situation;
import com.tangtang.polingo.situation.entity.SituationImage;
import com.tangtang.polingo.situation.entity.SituationSentence;
import com.tangtang.polingo.situation.repository.DetailedSituationRepository;
import com.tangtang.polingo.situation.repository.ImageRepository;
import com.tangtang.polingo.situation.repository.SentenceRepository;
import com.tangtang.polingo.situation.repository.SituationRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminSituationService {
    private final SituationRepository situationRepository;
    private final DetailedSituationRepository detailedSituationRepository;
    private final SentenceRepository sentenceRepository;
    private final ImageRepository imageRepository;

    public void addSituation(AdminStiuationPostRequest reqBody) {
        Situation situation = situationRepository.findByName(reqBody.getName())
                .orElseGet(() -> {
                    Situation newSituation = Situation.builder()
                            .name(reqBody.getName())
                            .category(Category.valueOf(reqBody.getCategory()))
                            .build();
                    return situationRepository.save(newSituation);
                });

        reqBody.getDetailedSituations().forEach(detail -> {
            DetailedSituation detailedSituation = DetailedSituation.builder()
                    .name(detail.getDetailedName())
                    .situation(situation)
                    .build();
            detailedSituation = detailedSituationRepository.save(detailedSituation);

            DetailedSituation finalDetailedSituation = detailedSituation;
            List<SituationImage> images = detail.getImages().stream()
                    .map(url -> SituationImage.builder()
                            .url(url)
                            .detailedSituation(finalDetailedSituation)
                            .build())
                    .collect(Collectors.toList());

            DetailedSituation finalDetailedSituation1 = detailedSituation;
            List<SituationSentence> sentences = detail.getSentences().stream()
                    .map(sentence -> SituationSentence.builder()
                            .sentence(sentence.getOriginText())
                            .translation(sentence.getTranslatedText())
                            .language(Language.valueOf(sentence.getLanguage()))
                            .detailedSituation(finalDetailedSituation1)
                            .build())
                    .collect(Collectors.toList());

            detailedSituation.addImages(images);
            detailedSituation.addSentences(sentences);
            detailedSituationRepository.save(detailedSituation);
        });

        situationRepository.save(situation);
    }

    @Transactional(readOnly = true)
    public Page<AdminSituationListResponse> getSituationsByCategories(List<Category> categories, Pageable pageable) {
        return situationRepository.findByCategoryIn(categories, pageable)
                .map(situation -> new AdminSituationListResponse(situation.getId(), situation.getCategory().toString(),
                        situation.getName()));
    }

    @Transactional(readOnly = true)
    public AdminSituationDetailResponse getDetailedSituation(Long situationId) {
        Situation situation = situationRepository.findById(situationId)
                .orElseThrow(() -> new EntityNotFoundException("Situation not found for id: " + situationId));

        List<AdminSituationDetailResponse.DetailedSituation> detailedSituations = situation.getDetailedSituations()
                .stream()
                .map(ds -> AdminSituationDetailResponse.DetailedSituation.builder()
                        .id(ds.getId())
                        .detailedName(ds.getName())
                        .images(ds.getImages().stream()
                                .map(img -> AdminSituationDetailResponse.Image.builder()
                                        .id(img.getId())
                                        .url(img.getUrl())
                                        .build())
                                .collect(Collectors.toList()))
                        .sentences(ds.getSentences().stream()
                                .map(sentence -> AdminSituationDetailResponse.Sentence.builder()
                                        .id(sentence.getId())
                                        .originText(sentence.getSentence())
                                        .translatedText(sentence.getTranslation())
                                        .language(sentence.getLanguage().toString())
                                        .build())
                                .collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());

        return AdminSituationDetailResponse.builder()
                .id(situation.getId())
                .name(situation.getName())
                .category(situation.getCategory().toString())
                .detailedSituations(detailedSituations)
                .build();
    }

    // 상세 상황 수정
    public void updateDetailedSituation(Long detailSituationId, DetailedSituationUpdateRequest request) {
        DetailedSituation detailedSituation = detailedSituationRepository.findById(detailSituationId)
                .orElseThrow(() -> new EntityNotFoundException("DetailedSituation not found"));
        detailedSituation.updateName(request.getDetailedName());
        detailedSituationRepository.save(detailedSituation);
    }

    // 상세 상황 삭제
    public void deleteDetailedSituation(Long detailSituationId) {
        detailedSituationRepository.deleteById(detailSituationId);
    }

    // 문장 수정
    public void updateSentence(Long sentenceId, SentenceUpdateRequest request) {
        SituationSentence sentence = sentenceRepository.findById(sentenceId)
                .orElseThrow(() -> new EntityNotFoundException("Sentence not found"));
        sentence.update(request.getOriginText(), request.getTranslatedText(), Language.valueOf(request.getLanguage()));
        sentenceRepository.save(sentence);
    }

    // 문장 삭제
    public void deleteSentence(Long sentenceId) {
        sentenceRepository.deleteById(sentenceId);
    }

    public void deleteSituation(Long situationId) {
        situationRepository.deleteById(situationId);
    }

    public void updateImage(Long imageId, ImageUpdateRequest request) {
        SituationImage image = imageRepository.findById(imageId)
                .orElseThrow(() -> new EntityNotFoundException("Image not found"));
        image.updateUrl(request.getUrl());
        imageRepository.save(image);
    }

    public void deleteImage(Long imageId) {
        imageRepository.deleteById(imageId);
    }

    public void updateSituation(Long situationId, SituationUpdateRequest request) {
        Situation situation = situationRepository.findById(situationId)
                .orElseThrow(() -> new EntityNotFoundException("Situation not found"));
        situation.update(request.getName(), Category.valueOf(request.getCategory()), request.getIconUrl());
        situationRepository.save(situation);
    }
}
