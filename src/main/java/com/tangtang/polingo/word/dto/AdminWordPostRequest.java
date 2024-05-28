package com.tangtang.polingo.word.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class AdminWordPostRequest {

    private String id;

    @JsonProperty("english_word")
    private String englishWord;

    @JsonProperty("japanese_word")
    private String japaneseWord;

    private String description;
}
