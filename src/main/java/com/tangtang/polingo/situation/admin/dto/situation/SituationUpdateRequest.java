package com.tangtang.polingo.situation.admin.dto.situation;

import lombok.Data;

@Data
public class SituationUpdateRequest {
    private String name;
    private String category;
    private String iconUrl;
}
