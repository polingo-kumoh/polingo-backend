package com.tangtang.polingo.entity;

import com.tangtang.polingo.global.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Situation extends BaseEntity {
    private String category; // 상황을 나타내는 카테고리 (예: "식당", "공항")

    @OneToMany(mappedBy = "situation")
    private List<SituationSentence> sentences = new ArrayList<>();
}
