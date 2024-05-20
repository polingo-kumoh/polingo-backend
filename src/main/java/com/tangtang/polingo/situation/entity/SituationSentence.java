package com.tangtang.polingo.situation.entity;

import com.tangtang.polingo.global.constant.Language;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Entity
@Getter
public class SituationSentence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String sentence; // 예문 내용

    @Column(columnDefinition = "TEXT")
    private String translation; // 예문의 번역

    @Enumerated(EnumType.STRING)
    private Language language;

    @ManyToOne
    @JoinColumn(name = "detailed_situation_id")
    private DetailedSituation detailedSituation; // 이 예문이 속한 세부 상황
}
