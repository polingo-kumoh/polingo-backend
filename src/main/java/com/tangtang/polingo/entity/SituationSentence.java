package com.tangtang.polingo.entity;

import com.tangtang.polingo.global.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class SituationSentence extends BaseEntity {
    private String sentence; // 예문 내용
    private String translation; // 예문의 번역

    @ManyToOne
    @JoinColumn(name = "detailed_situation_id")
    private DetailedSituation detailedSituation; // 이 예문이 속한 세부 상황
}
