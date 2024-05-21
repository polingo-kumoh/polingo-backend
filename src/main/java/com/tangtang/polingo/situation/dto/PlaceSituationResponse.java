package com.tangtang.polingo.situation.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PlaceSituationResponse {
    private String name;
    private String icon;
    private int count;
    private List<DetailSituationResponse> detailSituations;
}
