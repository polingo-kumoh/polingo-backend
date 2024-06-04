package com.tangtang.polingo.situation.admin.service.detail;



import com.tangtang.polingo.global.constant.Language;
import com.tangtang.polingo.situation.admin.dto.detail.DetailedSituationRequest;
import com.tangtang.polingo.situation.admin.dto.detail.SentenceCreateRequest;
import com.tangtang.polingo.situation.entity.DetailedSituation;
import com.tangtang.polingo.situation.entity.SituationImage;
import com.tangtang.polingo.situation.entity.SituationSentence;
import com.tangtang.polingo.situation.repository.DetailedSituationRepository;
import com.tangtang.polingo.situation.repository.ImageRepository;
import com.tangtang.polingo.situation.repository.SentenceRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AdminDetailedSituationService {
    private final DetailedSituationRepository detailedSituationRepository;
    private final SentenceRepository sentenceRepository;
    private final ImageRepository imageRepository;

    public void addImagesToDetailedSituation(Long detailedSituationId, List<String> imageUrls) {
        DetailedSituation detailedSituation = detailedSituationRepository.findById(detailedSituationId)
                .orElseThrow(() -> new EntityNotFoundException("Detailed situation not found for id: " + detailedSituationId));

        List<SituationImage> images = imageUrls.stream()
                .map(url -> SituationImage.builder()
                        .url(url)
                        .detailedSituation(detailedSituation)
                        .build())
                .collect(Collectors.toList());

        imageRepository.saveAll(images);
    }

    public void addSentencesToDetailedSituation(Long detailedSituationId, SentenceCreateRequest req) {
        DetailedSituation detailedSituation = detailedSituationRepository.findById(detailedSituationId)
                .orElseThrow(() -> new EntityNotFoundException("Detailed situation not found for id: " + detailedSituationId));

        log.info("req = {}", req.getLanguage());

        SituationSentence sentence = SituationSentence.builder()
                .sentence(req.getOriginText())
                .detailedSituation(detailedSituation)
                .translation(req.getTranslatedText())
                .language(Language.valueOf(req.getLanguage()))
                .build();

        sentenceRepository.save(sentence);
    }

    public void updateDetailedSituation(Long detailSituationId, DetailedSituationRequest request) {
        DetailedSituation detailedSituation = detailedSituationRepository.findById(detailSituationId)
                .orElseThrow(() -> new EntityNotFoundException("DetailedSituation not found"));
        detailedSituation.updateName(request.getDetailedName());
        detailedSituationRepository.save(detailedSituation);
    }

    public void deleteDetailedSituation(Long detailSituationId) {
        detailedSituationRepository.deleteById(detailSituationId);
    }
}
