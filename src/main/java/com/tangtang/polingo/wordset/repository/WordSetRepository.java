package com.tangtang.polingo.wordset.repository;

import com.tangtang.polingo.wordset.entity.WordSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordSetRepository extends JpaRepository<WordSet, Long> {
}
