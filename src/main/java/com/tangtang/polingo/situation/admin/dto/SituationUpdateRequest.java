package com.tangtang.polingo.situation.admin.dto;

import lombok.Data;

@Data
public class SituationUpdateRequest {
    private String name;
    private String category;
    private String iconUrl;
}
