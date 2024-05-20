package com.tangtang.polingo.situation.service;

import com.tangtang.polingo.global.constant.Language;
import com.tangtang.polingo.situation.cache.HolidayMap;
import com.tangtang.polingo.situation.dto.SituationResponse;
import com.tangtang.polingo.situation.entity.Category;
import com.tangtang.polingo.situation.entity.Situation;
import com.tangtang.polingo.situation.entity.SituationImage;
import com.tangtang.polingo.situation.entity.SituationSentence;
import com.tangtang.polingo.situation.repository.SituationRepository;
import com.tangtang.polingo.situation.vo.Holiday;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class HolidayHandler implements SituationHandler {
    private final HolidayMap holidayMap;
    private final SituationRepository situationRepository;

    @Override
    public boolean supports(Category category) {
        return Category.DATE.equals(category);
    }

    @Override
    public SituationResponse getSituation(Language language, LocalDate date) {
        LocalDate today = Optional.ofNullable(date).orElse(LocalDate.now());
        String countryCode = language.getCountryCode();

        List<Holiday> holidays = holidayMap.getHolidays(countryCode, today);
        log.info("holidays = {}", holidays);

        return holidays.stream()
                .map(holiday -> findMatchingSituation(holiday, language)
                        .map(this::createSituationResponse)
                        .orElseThrow(() -> new IllegalArgumentException("해당 공휴일에 대한 데이터가 없습니다.")))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("오늘 날짜에 해당하는 공휴일 데이터가 없습니다."));
    }

    private Optional<SituationSentence> findMatchingSituation(Holiday holiday, Language language) {
        Situation situation = situationRepository.findByCategory(Category.DATE)
                .orElseThrow(() -> new IllegalArgumentException("공휴일 데이터가 없습니다."));

        String holidayName = getHolidayName(holiday);
        return situation.getDetailedSituations().stream()
                .filter(detailedSituation -> detailedSituation.matches(holidayName, language))
                .flatMap(detailedSituation -> detailedSituation.getSentences().stream())
                .findFirst();
    }

    private String getHolidayName(Holiday holiday) {
        return Optional.ofNullable(holiday.getLocalName()).orElse(holiday.getName());
    }

    private String findSituationImage(SituationSentence sentence) {
        return sentence.getDetailedSituation().getImages().stream()
                .findFirst()
                .map(SituationImage::getUrl)
                .orElseThrow(() -> new IllegalArgumentException("이미지를 찾을 수 없습니다."));
    }

    private SituationResponse createSituationResponse(SituationSentence sentence) {
        String imageUrl = findSituationImage(sentence);
        return new SituationResponse("공휴일", sentence.getSentence(), imageUrl);
    }
}
