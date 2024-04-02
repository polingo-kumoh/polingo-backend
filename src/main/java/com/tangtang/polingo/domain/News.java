package com.tangtang.polingo.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
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
