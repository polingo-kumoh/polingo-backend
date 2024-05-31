package com.tangtang.polingo.situation.repository;

import com.tangtang.polingo.situation.entity.SituationSentence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SentenceRepository extends JpaRepository<SituationSentence, Long> {
}
