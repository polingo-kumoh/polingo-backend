package com.tangtang.polingo.situation.admin.service;

import com.tangtang.polingo.global.constant.Language;
import com.tangtang.polingo.situation.admin.dto.AdminSituationDetailResponse;
import com.tangtang.polingo.situation.admin.dto.AdminSituationListResponse;
import com.tangtang.polingo.situation.admin.dto.AdminStiuationPostRequest;
import com.tangtang.polingo.situation.constants.SituationCategory;
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
@RequiredArgsConstructor
public class AdminSituationService {
    private final SituationRepository situationRepository;
    private final DetailedSituationRepository detailedSituationRepository;

    @Transactional
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

    public Page<AdminSituationListResponse> getSituationsByCategories(List<Category> categories, Pageable pageable) {
        return situationRepository.findByCategoryIn(categories, pageable)
                .map(situation -> new AdminSituationListResponse(situation.getId(), situation.getCategory().toString(), situation.getName()));
    }

    public AdminSituationDetailResponse getDetailedSituation(Long situationId) {
        Situation situation = situationRepository.findById(situationId)
                .orElseThrow(() -> new EntityNotFoundException("Situation not found for id: " + situationId));

        List<AdminSituationDetailResponse.DetailedSituation> detailedSituations = situation.getDetailedSituations().stream()
                .map(ds -> AdminSituationDetailResponse.DetailedSituation.builder()
                        .id(ds.getId())
                        .detailedName(ds.getName())
                        .images(ds.getImages().stream().map(SituationImage::getUrl).collect(Collectors.toList()))
                        .sentences(ds.getSentences().stream().map(sentence -> AdminSituationDetailResponse.Sentence.builder()
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

}
