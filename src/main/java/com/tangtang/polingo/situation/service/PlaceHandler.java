package com.tangtang.polingo.situation.service;


import com.tangtang.polingo.global.constant.Language;
import com.tangtang.polingo.situation.dto.DetailSituationResponse;
import com.tangtang.polingo.situation.dto.PlaceSituationResponse;
import com.tangtang.polingo.situation.dto.SituationDTO;
import com.tangtang.polingo.situation.entity.Category;
import com.tangtang.polingo.situation.entity.DetailedSituation;
import com.tangtang.polingo.situation.entity.Situation;
import com.tangtang.polingo.situation.entity.SituationSentence;
import com.tangtang.polingo.situation.repository.SituationRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlaceHandler {

    private final SituationRepository situationRepository;

    public List<PlaceSituationResponse> getSituation(Language language) {
        List<Situation> situations = situationRepository.findByCategory(Category.PLACE);

        return situations.stream()
                .map(situation -> mapToPlaceSituationResponse(situation, language))
                .collect(Collectors.toList());
    }

    private PlaceSituationResponse mapToPlaceSituationResponse(Situation situation, Language language) {
        List<DetailSituationResponse> detailSituations = situation.getDetailedSituations().stream()
                .map(detailSituation -> mapToDetailSituationResponse(detailSituation, language))
                .collect(Collectors.toList());

        return new PlaceSituationResponse(
                situation.getName(),
                situation.getIcon(),
                detailSituations.size(),
                detailSituations
        );
    }

    private DetailSituationResponse mapToDetailSituationResponse(DetailedSituation detailedSituation,
                                                                 Language language) {
        List<SituationDTO> sentences = detailedSituation.getSentences().stream()
                .filter(sentence -> sentence.getLanguage() == language)
                .map(this::mapToSituationDTO)
                .collect(Collectors.toList());

        return new DetailSituationResponse(
                detailedSituation.getName(),
                sentences.size(),
                sentences
        );
    }

    private SituationDTO mapToSituationDTO(SituationSentence sentence) {
        return new SituationDTO(sentence.getSentence(), sentence.getTranslation(), null);
    }
}

