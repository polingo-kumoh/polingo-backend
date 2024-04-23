package com.tangtang.polingo.news.dto;

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
    private String firstSentence;
}
