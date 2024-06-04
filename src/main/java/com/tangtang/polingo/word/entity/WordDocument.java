package com.tangtang.polingo.word.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "polingo_words")
@JsonIgnoreProperties(ignoreUnknown = true)
public class WordDocument {
    @Id
    private String id;

    @JsonProperty("english_word")
    private String englishWord;

    @JsonProperty("japanese_word")
    private String japaneseWord;
    private String description;
}
