package com.tangtang.polingo.situation.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class AdminSituationDetailResponse {

    private Long id;
    private String name;
    private String category;

    @JsonProperty("detailedSituations")
    private List<DetailedSituation> detailedSituations;

    @Data
    public static class DetailedSituation {
        private Long id;

        @JsonProperty("detailedName")
        private String detailedName;

        private List<String> images;
        private List<Sentence> sentences;
    }

    @Data
    public static class Sentence {
        private Long id;

        @JsonProperty("origin_text")
        private String originText;

        @JsonProperty("translated_text")
        private String translatedText;

        private String language;
    }

}
