package com.tangtang.polingo.news.admin.service;

import com.tangtang.polingo.global.constant.Language;
import com.tangtang.polingo.news.admin.dto.NewsPostRequest;
import com.tangtang.polingo.news.entity.News;
import com.tangtang.polingo.news.entity.NewsSentence;
import com.tangtang.polingo.news.repository.NewsRepository;
import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminNewsService {
    private final NewsRepository newsRepository;

    public void saveNews(NewsPostRequest reqBody) {
        News news = convertToEntity(reqBody);
        newsRepository.save(news);
    }

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

    public void deleteNews(Long newsId) {
        News news = newsRepository.findById(newsId)
                .orElseThrow(() -> new EntityNotFoundException("News not found with id: " + newsId));
        newsRepository.delete(news);
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
}
