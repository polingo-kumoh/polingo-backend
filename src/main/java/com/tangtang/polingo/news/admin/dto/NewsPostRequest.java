package com.tangtang.polingo.news.admin.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class NewsPostRequest {
    @JsonProperty("article_title")
    private String articleTitle;

    @JsonProperty("article_image")
    private String articleImage;

    @JsonProperty("article_url")
    private String articleUrl;

    @JsonProperty("article_content_sentence")
    private List<SentenceRequest> sentenceRequests;


    @JsonProperty("language")
    private String languageCode;

    @Data
    public static class SentenceRequest {
        @JsonProperty("origin_text")
        private String originText;

        @JsonProperty("translated_text")
        private String translatedText;

        @JsonProperty("grammers")
        private String grammers;
    }
}
