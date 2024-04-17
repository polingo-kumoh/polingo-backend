package com.tangtang.polingo.user.entity;

import com.tangtang.polingo.entity.WordSet;
import com.tangtang.polingo.global.constant.Language;
import com.tangtang.polingo.global.entity.BaseEntity;
import com.tangtang.polingo.user.constant.LoginType;
import com.tangtang.polingo.user.constant.UserRole;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity(name = "users")
@SuperBuilder
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {
    private String nickname;

    @Enumerated(EnumType.STRING)
    private Language language;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Enumerated(EnumType.STRING)
    private LoginType loginType;

    //OAuth2제공자로부터 넘어오는 ID값
    private String providerId;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WordSet> wordSets = new ArrayList<>();

    public void updateNIckName(String name) {
        this.nickname = name;
    }

    public void updateLanguage(Language language) {
        this.language = language;
    }

}
