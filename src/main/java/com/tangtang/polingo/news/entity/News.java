package com.tangtang.polingo.news.entity;

import com.tangtang.polingo.global.constant.Language;
import com.tangtang.polingo.global.entity.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class News extends BaseEntity {
    private String title;
    private String newsUrl;
    private String imageUrl;
    private LocalDateTime publishDate;

    @Enumerated(EnumType.STRING)
    private Language language;

    @OneToMany(mappedBy = "news", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NewsSentence> newsSentences = new ArrayList<>();

    @OneToMany(mappedBy = "news", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<NewsScrap> newsScraps = new ArrayList<>();

    public void updateNews(String title, String newsUrl, String imageUrl, LocalDateTime publishDate, Language language, List<NewsSentence> newSentences) {
        this.title = title;
        this.newsUrl = newsUrl;
        this.imageUrl = imageUrl;
        this.publishDate = publishDate;
        this.language = language;

        // 기존 문장들을 제거하고 새로운 문장 목록으로 대체
        this.newsSentences.clear();
        this.newsSentences.addAll(newSentences);
        newSentences.forEach(s -> s.addNews(this)); // 연관 관계 설정
    }
}
