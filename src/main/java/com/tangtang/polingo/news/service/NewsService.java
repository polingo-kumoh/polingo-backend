package com.tangtang.polingo.news.service;

import com.tangtang.polingo.global.constant.Language;
import com.tangtang.polingo.news.dto.NewsDetailResponse;
import com.tangtang.polingo.news.dto.NewsSummaryResponse;
import com.tangtang.polingo.news.dto.SentenceDetail;
import com.tangtang.polingo.news.entity.News;
import com.tangtang.polingo.news.entity.NewsScrap;
import com.tangtang.polingo.news.repository.NewsRepository;
import com.tangtang.polingo.news.repository.NewsScrapRepository;
import com.tangtang.polingo.user.entity.User;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NewsService {
    private final NewsRepository newsRepository;
    private final NewsScrapRepository newsScrapRepository;

    public Page<NewsSummaryResponse> getNewsSummaries(String languageCode, Pageable pageable, User user) {
        Language language = Language.fromCode(languageCode);
        return newsRepository.findNewsSummariesByLanguage(user, language, pageable);
    }

    public NewsDetailResponse getNewsDetail(Long newsId, User user) {
        News news = newsRepository.findNewsByIdWithSentences(newsId)
                .orElseThrow(() -> new EntityNotFoundException("News not found with ID: " + newsId));

        boolean isScraped = newsScrapRepository.existsByUserAndNews(user, news);
        List<SentenceDetail> sentences = news.getNewsSentences().stream()
                .map(ns -> SentenceDetail.builder()
                        .sentenceId(ns.getId())
                        .originalText(ns.getOriginText())
                        .translatedText(ns.getTranslatedText())
                        .build())
                .collect(Collectors.toList());

        return NewsDetailResponse.builder()
                .id(news.getId())
                .imageUrl(news.getImageUrl())
                .title(news.getTitle())
                .link(news.getNewsUrl())
                .isScraped(isScraped)
                .sentences(sentences)
                .build();
    }

    @Transactional
    public void scrapNews(User user, Long newsId) {
        News news = newsRepository.findById(newsId).orElse(null);
        assert news != null;

        if (isNewsAlreadyScrappedByUser(news, user)) {
            throw new IllegalStateException("사용자가 이미 해당 뉴스를 스크랩했습니다.");
        }

        NewsScrap newsScrap = NewsScrap.builder()
                .news(news)
                .user(user)
                .build();

        newsScrapRepository.save(newsScrap);
    }

    public Page<NewsSummaryResponse> getScrapedNews(User user, String languageCode, Pageable pageable) {
        Language language = Language.fromCode(languageCode);
        return newsRepository.findScrapedNewsByUserAndLanguage(user, language, pageable);
    }

    @Transactional
    public void unscrapNews(User user, Long newsId) {
        NewsScrap newsScrap = newsScrapRepository.findByUserAndNewsId(user, newsId)
                .orElseThrow(() -> new IllegalArgumentException("스크랩되지 않은 뉴스입니다."));

        newsScrapRepository.delete(newsScrap);
    }

    private boolean isNewsAlreadyScrappedByUser(News news, User user) {
        return news.getNewsScraps().stream()
                .anyMatch(newsScrap -> newsScrap.getUser().equals(user));
    }
}
