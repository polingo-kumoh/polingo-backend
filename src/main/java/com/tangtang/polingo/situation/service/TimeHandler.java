package com.tangtang.polingo.situation.service;

import com.tangtang.polingo.global.constant.Language;
import com.tangtang.polingo.situation.dto.SituationDTO;
import com.tangtang.polingo.situation.entity.Situation;
import com.tangtang.polingo.situation.entity.SituationImage;
import com.tangtang.polingo.situation.entity.SituationSentence;
import com.tangtang.polingo.situation.repository.SituationRepository;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TimeHandler {
    private final SituationRepository situationRepository;

    public List<SituationDTO> getSituation(Language language) {
        String timeOfDay = getTimeOfDay();

        Situation situation = situationRepository.findByName(timeOfDay)
                .orElseThrow(() -> new IllegalArgumentException("해당 시간대에 대한 데이터가 없습니다."));

        return situation.getDetailedSituations().stream()
                .flatMap(detailedSituation -> detailedSituation.getSentences().stream())
                .filter(sentence -> sentence.getLanguage() == language)
                .map(this::mapToSituationDTO)
                .collect(Collectors.toList());
    }

    private String getTimeOfDay() {
        LocalTime now = LocalTime.now();
        if (now.isBefore(LocalTime.of(12, 0))) {
            return "MORNING";
        } else if (now.isBefore(LocalTime.of(17, 0))) {
            return "LUNCH";
        } else {
            return "DINNER";
        }
    }

    private SituationDTO mapToSituationDTO(SituationSentence sentence) {
        String imageUrl = findSituationImage(sentence);
        log.info("situationSentence = {}", sentence);
        SituationDTO dto = new SituationDTO(sentence.getSentence(), sentence.getTranslation(), imageUrl);
        log.info("dto = {}", dto);
        return dto;
    }

    private String findSituationImage(SituationSentence sentence) {
        return sentence.getDetailedSituation().getImages().stream()
                .findFirst()
                .map(SituationImage::getUrl)
                .orElse(null);
    }
}
