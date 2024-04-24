package com.tangtang.polingo.news.dto;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class NewsDetailResponse {
    private Long id;
    private String imageUrl;
    private String title;
    private String link;
    private boolean isScraped;
    private List<SentenceDetail> sentences;

    @JsonGetter("is_scraped")
    public boolean isScraped() {
        return isScraped;
    }
}
