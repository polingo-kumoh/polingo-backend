package com.tangtang.polingo.situation.service.weather;

import com.tangtang.polingo.global.constant.Language;
import com.tangtang.polingo.situation.dto.WeatherResponse;
import com.tangtang.polingo.situation.entity.Category;
import com.tangtang.polingo.situation.entity.Situation;
import com.tangtang.polingo.situation.entity.SituationImage;
import com.tangtang.polingo.situation.entity.SituationSentence;
import com.tangtang.polingo.situation.repository.SituationRepository;
import com.tangtang.polingo.situation.vo.Weather;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeatherHandler {
    private final WeatherClient weatherClient;
    private final SituationRepository situationRepository;

    public WeatherResponse getSituation(Language language, String lon, String lat) {
        Weather weather = weatherClient.getWeatherInfo(lon, lat);
        String description = weather.getDescription();
        String city = weather.getCityName();
        double temp = weather.getTemperature();

        log.info("날씨 = {}", description);

        SituationSentence sentence = findMatchingSentence(description, language)
                .orElseThrow(() -> new IllegalArgumentException("해당 날씨에 대한 데이터가 없습니다."));

        String imageUrl = findSituationImage(sentence);

        return new WeatherResponse(temp, city, sentence.getSentence(), sentence.getTranslation(), imageUrl);
    }

    private Optional<SituationSentence> findMatchingSentence(String description, Language language) {
        Situation situation = situationRepository.findByCategory(Category.WEATHER)
                .orElseThrow(() -> new IllegalArgumentException("날씨 데이터가 없습니다."));

        return situation.getDetailedSituations().stream()
                .filter(detailedSituation -> detailedSituation.matches(description))
                .flatMap(detailedSituation -> detailedSituation.getSentences().stream())
                .filter(sentence -> sentence.getLanguage() == language)
                .findFirst();
    }

    private String findSituationImage(SituationSentence sentence) {
        return sentence.getDetailedSituation().getImages().stream()
                .findFirst()
                .map(SituationImage::getUrl)
                .orElse(null);
    }
}