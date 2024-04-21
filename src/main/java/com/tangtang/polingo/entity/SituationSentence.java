package com.tangtang.polingo.entity;

import com.tangtang.polingo.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity
public class SituationSentence extends BaseEntity {
    @Column(columnDefinition = "TEXT")
    private String sentence; // 예문 내용

    @Column(columnDefinition = "TEXT")
    private String translation; // 예문의 번역

    @ManyToOne
    @JoinColumn(name = "detailed_situation_id")
    private DetailedSituation detailedSituation; // 이 예문이 속한 세부 상황
}
