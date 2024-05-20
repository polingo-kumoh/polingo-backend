package com.tangtang.polingo.situation.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Holiday {
    @JsonProperty("date")
    private String date;

    @JsonProperty("localName")
    private String localName;

    @JsonProperty("name")
    private String name;
}
