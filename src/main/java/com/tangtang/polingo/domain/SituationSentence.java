package com.tangtang.polingo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class SituationSentence extends BaseEntity {
    private String sentence; // 예문 내용
    private String translation; // 예문의 번역

    @ManyToOne
    @JoinColumn(name = "situation_id")
    private Situation situation; // 이 예문이 속한 상황(카테고리)
}
