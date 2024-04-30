package com.tangtang.polingo.wordset.repository;

import com.tangtang.polingo.global.constant.Language;
import com.tangtang.polingo.user.entity.User;
import com.tangtang.polingo.wordset.entity.WordSet;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordSetRepository extends JpaRepository<WordSet, Long> {
    List<WordSet> findAllByUser(User user);

    Optional<WordSet> findByUserAndLanguageAndIsDefaultTrue(User user, Language language);
}
