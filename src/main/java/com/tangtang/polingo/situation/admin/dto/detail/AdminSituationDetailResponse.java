package com.tangtang.polingo.situation.admin.dto.detail;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminSituationDetailResponse {

    private Long id;
    private String name;
    private String category;

    @JsonProperty("detailedSituations")
    private List<DetailedSituation> detailedSituations;

    @Data
    @Builder
    public static class DetailedSituation {
        private Long id;

        @JsonProperty("detailedName")
        private String detailedName;

        private List<Image> images;
        private List<Sentence> sentences;
    }

    @Data
    @Builder
    public static class Sentence {
        private Long id;

        @JsonProperty("origin_text")
        private String originText;

        @JsonProperty("translated_text")
        private String translatedText;

        private String language;
    }

    @Data
    @Builder
    public static class Image {
        private Long id;
        private String url;
    }
}
