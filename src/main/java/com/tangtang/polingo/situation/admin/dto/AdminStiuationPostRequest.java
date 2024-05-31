package com.tangtang.polingo.situation.admin.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class AdminStiuationPostRequest {
    private String name;
    private String category;
    private String iconUrl;

    @JsonProperty("detailedSituations")
    private List<DetailedSituation> detailedSituations;

    @Data
    public static class DetailedSituation {
        @JsonProperty("detailedName")
        private String detailedName;
        private Long detaildSituationId;

        private List<String> images;
        private List<Sentence> sentences;
    }

    @Data
    public static class Sentence {
        @JsonProperty("origin_text")
        private String originText;

        @JsonProperty("translated_text")
        private String translatedText;

        private String language;
    }
}
