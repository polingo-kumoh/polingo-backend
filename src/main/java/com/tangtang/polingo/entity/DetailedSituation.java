package com.tangtang.polingo.entity;

import com.tangtang.polingo.global.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class DetailedSituation extends BaseEntity {
    private String name; // 세부 상황의 이름 (예: "주문", "계산")

    @ManyToOne
    @JoinColumn(name = "situation_id")
    private Situation situation; // 속한 상위 카테고리

    @OneToMany(mappedBy = "detailedSituation")
    private List<SituationSentence> sentences = new ArrayList<>();
}
