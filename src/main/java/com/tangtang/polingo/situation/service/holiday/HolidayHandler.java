package com.tangtang.polingo.situation.service.holiday;

import com.tangtang.polingo.global.constant.Language;
import com.tangtang.polingo.situation.dto.DateResponse;
import com.tangtang.polingo.situation.entity.Category;
import com.tangtang.polingo.situation.entity.DetailedSituation;
import com.tangtang.polingo.situation.entity.Situation;
import com.tangtang.polingo.situation.entity.SituationImage;
import com.tangtang.polingo.situation.entity.SituationSentence;
import com.tangtang.polingo.situation.repository.DetailedSituationRepository;
import com.tangtang.polingo.situation.repository.SituationRepository;
import com.tangtang.polingo.situation.vo.Holiday;
import java.time.LocalDate;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class HolidayHandler {
    private final HolidayMap holidayMap;
    private final DetailedSituationRepository detailedSituationRepository;

    public DateResponse getSituation(Language language, LocalDate date) {
        LocalDate today = Optional.ofNullable(date).orElse(LocalDate.now());
        String countryCode = language.getCountryCode();

        Holiday holiday = holidayMap.getHoliday(countryCode, today)
                .orElseThrow(() -> new IllegalArgumentException("오늘 날짜에 해당하는 공휴일 데이터가 없습니다."));
        log.info("holiday = {}", holiday);

        SituationSentence sentence = findMatchingSentence(holiday, language)
                .orElseThrow(() -> new IllegalArgumentException("해당 공휴일에 대한 데이터가 없습니다."));

        return createSituationResponse(sentence);
    }

    private Optional<SituationSentence> findMatchingSentence(Holiday holiday, Language language) {
        String holidayName = getHolidayName(holiday);
        DetailedSituation detailedSituation = detailedSituationRepository.findByName(holidayName)
                .orElseThrow(() -> new IllegalArgumentException("공휴일 데이터가 없습니다."));

        return detailedSituation.getSentences().stream()
                .filter(sentence -> sentence.getLanguage() == language)
                .findFirst();
    }

    private String getHolidayName(Holiday holiday) {
        return Optional.ofNullable(holiday.getLocalName()).orElse(holiday.getName());
    }

    private String findSituationImage(SituationSentence sentence) {
        return sentence.getDetailedSituation().getImages().stream()
                .findFirst()
                .map(SituationImage::getUrl)
                .orElse(null);
    }

    private DateResponse createSituationResponse(SituationSentence sentence) {
        String imageUrl = findSituationImage(sentence);
        return new DateResponse(sentence.getSentence(), sentence.getTranslation(), imageUrl);
    }
}
