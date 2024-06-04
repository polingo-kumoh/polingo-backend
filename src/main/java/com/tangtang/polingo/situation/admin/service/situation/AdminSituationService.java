package com.tangtang.polingo.situation.admin.service.situation;

import com.tangtang.polingo.global.constant.Language;
import com.tangtang.polingo.situation.admin.dto.detail.AdminSituationDetailResponse;
import com.tangtang.polingo.situation.admin.dto.detail.DetailedSituationRequest;
import com.tangtang.polingo.situation.admin.dto.situation.AdminSituationListResponse;
import com.tangtang.polingo.situation.admin.dto.situation.AdminSituationPostRequest;
import com.tangtang.polingo.situation.admin.dto.situation.SituationUpdateRequest;
import com.tangtang.polingo.situation.entity.Category;
import com.tangtang.polingo.situation.entity.DetailedSituation;
import com.tangtang.polingo.situation.entity.Situation;
import com.tangtang.polingo.situation.entity.SituationImage;
import com.tangtang.polingo.situation.entity.SituationSentence;
import com.tangtang.polingo.situation.repository.DetailedSituationRepository;
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

    public void addSituation(AdminSituationPostRequest reqBody) {
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
    public void deleteSituation(Long situationId) {
        situationRepository.deleteById(situationId);
    }

    public void updateSituation(Long situationId, SituationUpdateRequest request) {
        Situation situation = situationRepository.findById(situationId)
                .orElseThrow(() -> new EntityNotFoundException("Situation not found"));
        situation.update(request.getName(), Category.valueOf(request.getCategory()), request.getIconUrl());
        situationRepository.save(situation);
    }

    public void addDetailSituation(Long situationId, DetailedSituationRequest req) {
        Situation situation = situationRepository.findById(situationId)
                .orElseThrow(() -> new EntityNotFoundException("Situation not found"));

        DetailedSituation detailedSituation = DetailedSituation.builder()
                .name(req.getDetailedName())
                .situation(situation)
                .build();

        detailedSituationRepository.save(detailedSituation);
    }
}
