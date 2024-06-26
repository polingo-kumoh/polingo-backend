package com.tangtang.polingo.situation.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DetailedSituation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // 세부 상황의 이름 (예: "주문", "계산")

    @ManyToOne
    @JoinColumn(name = "situation_id")
    private Situation situation; // 속한 상위 카테고리

    @OneToMany(mappedBy = "detailedSituation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SituationSentence> sentences = new ArrayList<>();

    @OneToMany(mappedBy = "detailedSituation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SituationImage> images = new ArrayList<>();

    public void addImages(List<SituationImage> images) {
        images.forEach(i -> i.assignImage(this));
        this.images = images;
    }

    public void addSentences(List<SituationSentence> sentences) {
        this.sentences = sentences;
    }

    public void updateName(String detailedName) {
        this.name = detailedName;
    }
}
