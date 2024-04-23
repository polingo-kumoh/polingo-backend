package com.tangtang.polingo.news.service;

import com.tangtang.polingo.global.constant.Language;
import org.springframework.data.domain.Page;
import com.tangtang.polingo.news.dto.NewsDetailResponse;
import com.tangtang.polingo.news.dto.NewsSummaryResponse;
import com.tangtang.polingo.news.dto.SentenceDetail;
import com.tangtang.polingo.news.entity.NewsSentence;
import com.tangtang.polingo.news.repository.NewsRepository;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class NewsService {
    private final NewsRepository newsRepository;
    public Page<NewsSummaryResponse> getNewsSummaries(String languageCode, Pageable pageable) {
        Language language = Language.fromCode(languageCode);
        return newsRepository.findNewsSummariesByLanguage(language, pageable);
    }

    public NewsDetailResponse getNewsDetail(Long newsId) {
        return newsRepository.findById(newsId)
                .map(news -> NewsDetailResponse.builder()
                        .id(news.getId())
                        .imageUrl(news.getImageUrl())
                        .title(news.getTitle())
                        .link(news.getNewsUrl())
                        .sentences(createSentenceDetails(news.getNewsSentences()))
                        .build())
                .orElse(null);
    }

    private List<SentenceDetail> createSentenceDetails(List<NewsSentence> newsSentences) {
        return newsSentences.stream()
                .map(sentence -> SentenceDetail.builder()
                        .sentenceId(sentence.getId())
                        .originalText(sentence.getOriginText())
                        .grammar(sentence.getGrammars())
                        .translatedText(sentence.getTranslatedText())
                        .build())
                .collect(Collectors.toList());
    }
}
