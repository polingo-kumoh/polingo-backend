package com.tangtang.polingo.news.dto;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class NewsDetailResponse {
    private Long id;
    private String imageUrl;
    private String title;
    private String link;
    private List<SentenceDetail> sentences;
}
