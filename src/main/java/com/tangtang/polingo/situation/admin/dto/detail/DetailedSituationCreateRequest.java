package com.tangtang.polingo.situation.admin.dto.detail;

import java.util.List;
import lombok.Data;

@Data
public class DetailedSituationCreateRequest {
    private String detailedName;
    private List<String> images;
    private List<SentenceCreateRequest> sentences;
}

