package com.tangtang.polingo.situation.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class Holiday {
    @JsonProperty("date")
    private String date;

    @JsonProperty("localName")
    private String localName;

    @JsonProperty("name")
    private String name;

    @JsonProperty("countryCode")
    private String countryCode;

    @JsonProperty("fixed")
    private boolean fixed;

    @JsonProperty("global")
    private boolean global;

    @JsonProperty("types")
    private List<String> types;
}
