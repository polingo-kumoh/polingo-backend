package com.tangtang.polingo.user.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleResponse {
    private String id;
    private String email;
    private String name;
}

