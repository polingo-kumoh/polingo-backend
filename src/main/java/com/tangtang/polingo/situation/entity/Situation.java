package com.tangtang.polingo.situation.entity;

import com.tangtang.polingo.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Entity
@Getter
public class Situation extends BaseEntity {
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;
    private String name;

    @OneToMany(mappedBy = "situation")
    private List<DetailedSituation> detailedSituations = new ArrayList<>();
}
