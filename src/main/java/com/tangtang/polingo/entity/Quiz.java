package com.tangtang.polingo.entity;

import com.tangtang.polingo.global.entity.BaseEntity;
import com.tangtang.polingo.wordset.entity.WordSet;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Quiz extends BaseEntity {
    private String question;
    private String answer;
    private boolean isCorrect;

    @ManyToOne
    @JoinColumn(name = "wordset_id")
    private WordSet wordSet;
}

