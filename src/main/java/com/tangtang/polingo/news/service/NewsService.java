package com.tangtang.polingo.news.service;

import com.tangtang.polingo.global.constant.Language;
import com.tangtang.polingo.news.dto.NewsDetailResponse;
import com.tangtang.polingo.news.dto.NewsPostRequest;
import com.tangtang.polingo.news.dto.NewsSummaryResponse;
import com.tangtang.polingo.news.dto.SentenceDetail;
import com.tangtang.polingo.news.entity.News;
import com.tangtang.polingo.news.entity.NewsScrap;
import com.tangtang.polingo.news.entity.NewsSentence;
import com.tangtang.polingo.news.repository.NewsRepository;
import com.tangtang.polingo.news.repository.NewsScrapRepository;
import com.tangtang.polingo.user.entity.User;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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


    public Page<NewsSummaryResponse> getNewsSummaries(Optional<String> languageCode, Pageable pageable, User user) {
        if (languageCode.isPresent() && !languageCode.get().isEmpty()) {
            Language language = Language.fromCode(languageCode.get());
            return newsRepository.findNewsSummariesByLanguage(user, language, pageable);
        }
        return newsRepository.findNewsSummaries(user, pageable);
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

    @Transactional
    public void saveNews(NewsPostRequest reqBody) {
        News news = convertToEntity(reqBody);
        newsRepository.save(news);
    }
    private News convertToEntity(NewsPostRequest reqBody) {
        List<NewsSentence> sentences = reqBody.getSentenceRequests().stream()
                .map(s -> NewsSentence.builder()
                        .originText(s.getOriginText())
                        .translatedText(s.getTranslatedText())
                        .grammars(s.getGrammers())
                        .build())
                .collect(Collectors.toList());

        News news = News.builder()
                .title(reqBody.getArticleTitle())
                .newsUrl(reqBody.getArticleUrl())
                .imageUrl(reqBody.getArticleImage())
                .publishDate(LocalDateTime.now())
                .language(Language.valueOf(reqBody.getLanguageCode().toUpperCase()))
                .newsSentences(sentences)
                .build();

        sentences.forEach(s -> s.addNews(news)); // 연관 관계 설정
        return news;
    }

    @Transactional
    public void updateNews(Long newsId, NewsPostRequest reqBody) {
        News updatedNews = convertToEntity(reqBody);

        News news = newsRepository.findById(newsId)
                .orElseThrow(() -> new EntityNotFoundException("News not found with id: " + newsId));

        // 도메인 메소드를 통한 업데이트 수행
        news.updateNews(
                updatedNews.getTitle(),
                updatedNews.getNewsUrl(),
                updatedNews.getImageUrl(),
                updatedNews.getPublishDate(),
                updatedNews.getLanguage(),
                updatedNews.getNewsSentences()
        );

        newsRepository.save(news); // JPA가 변경된 내용을 자동으로 감지하고 저장
    }

}
