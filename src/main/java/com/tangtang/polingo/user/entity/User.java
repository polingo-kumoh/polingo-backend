package com.tangtang.polingo.user.entity;

import com.tangtang.polingo.global.constant.Language;
import com.tangtang.polingo.entity.WordSet;
import com.tangtang.polingo.global.constant.UserRole;
import com.tangtang.polingo.global.entity.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User extends BaseEntity {
    private String nickname;
    private String profileImage;
    //기본 언어 설정

    @Enumerated(EnumType.STRING)
    private Language language;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WordSet> wordSets = new ArrayList<>();
}
