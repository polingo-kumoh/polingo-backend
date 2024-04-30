package com.tangtang.polingo.wordset.entity;

import com.tangtang.polingo.global.constant.Language;
import com.tangtang.polingo.global.entity.BaseEntity;
import com.tangtang.polingo.user.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class WordSet extends BaseEntity {
    private String name;

    @Enumerated(EnumType.STRING)
    private Language language;

    private boolean isDefault;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;

    public void updateName(String name) {
        this.name = name;
    }

    public void updateDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public boolean getIsDefault() {
        return isDefault;
    }
}
