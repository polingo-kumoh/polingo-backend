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
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
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
}
