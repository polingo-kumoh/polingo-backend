package com.tangtang.polingo.news.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.tangtang.polingo.global.constant.Language;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class NewsSummaryResponse {
    private Long id;
    private String imageUrl;
    private String title;
    private LocalDateTime publishDate;
    private Language language;
    private boolean isScraped;
    private String firstSentence;

    public NewsSummaryResponse(Long id, String imageUrl, String title, LocalDateTime publishDate,
                               String firstSentence) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.title = title;
        this.publishDate = publishDate;
        this.isScraped = true;
        this.firstSentence = firstSentence;
    }

    @JsonGetter("is_scraped")
    public boolean isScraped() {
        return isScraped;
    }
}
