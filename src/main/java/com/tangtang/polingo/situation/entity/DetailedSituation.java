package com.tangtang.polingo.situation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Entity
@Getter
public class DetailedSituation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // 세부 상황의 이름 (예: "주문", "계산")

    @ManyToOne
    @JoinColumn(name = "situation_id")
    private Situation situation; // 속한 상위 카테고리

    @OneToMany(mappedBy = "detailedSituation")
    private List<SituationSentence> sentences = new ArrayList<>();

    @OneToMany(mappedBy = "detailedSituation")
    private List<SituationImage> images = new ArrayList<>();

    public boolean matches(String holidayName) {
        return this.name.equals(holidayName);
    }
}
