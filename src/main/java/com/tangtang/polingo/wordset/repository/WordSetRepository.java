package com.tangtang.polingo.wordset.repository;

import com.tangtang.polingo.global.constant.Language;
import com.tangtang.polingo.user.entity.User;
import com.tangtang.polingo.wordset.entity.WordSet;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WordSetRepository extends JpaRepository<WordSet, Long> {
    List<WordSet> findAllByUser(User user);

    Optional<WordSet> findByUserAndLanguageAndIsDefaultTrue(User user, Language language);

    @Query("SELECT wsw.wordSet.id, COUNT(wsw.word.id) FROM WordSetWord wsw WHERE wsw.wordSet.user = :user GROUP BY wsw.wordSet.id")
    List<Object[]> countWordsInWordSetsByUser(@Param("user") User user);
}
