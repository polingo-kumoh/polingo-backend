package com.tangtang.polingo.situation.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DetailSituationResponse {
    private String name;
    private int count;
    private List<SituationDTO> sentences;
}
