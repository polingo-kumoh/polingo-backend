package com.tangtang.polingo.situation.service;

import com.tangtang.polingo.global.constant.Language;
import com.tangtang.polingo.situation.dto.SituationDTO;
import com.tangtang.polingo.situation.entity.Situation;
import com.tangtang.polingo.situation.entity.SituationImage;
import com.tangtang.polingo.situation.entity.SituationSentence;
import com.tangtang.polingo.situation.repository.SituationRepository;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeekHandler {
    private final SituationRepository situationRepository;

    public List<SituationDTO> getSituation(Language language) {
        String today = LocalDate.now().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH).toUpperCase();
        Situation situation = situationRepository.findByName(today)
                .orElseThrow(() -> new IllegalArgumentException("요일 데이터가 없습니다."));

        return situation.getDetailedSituations().stream()
                .flatMap(detailedSituation -> detailedSituation.getSentences().stream())
                .filter(sentence -> sentence.getLanguage() == language)
                .map(this::mapToSituationDTO)
                .collect(Collectors.toList());
    }

    private SituationDTO mapToSituationDTO(SituationSentence sentence) {
        String imageUrl = findSituationImage(sentence);
        return new SituationDTO(sentence.getSentence(), sentence.getTranslation(), imageUrl);
    }

    private String findSituationImage(SituationSentence sentence) {
        return sentence.getDetailedSituation().getImages().stream()
                .findFirst()
                .map(SituationImage::getUrl)
                .orElse(null);
    }
}
