package com.tangtang.polingo.quiz.repository;

import com.tangtang.polingo.quiz.entity.UserWord;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserWordRepository extends JpaRepository<UserWord, Long> {
    void deleteByUserIdAndWordId(Long userId, Long wordId);
    boolean existsByUserIdAndWordId(Long userId, Long wordId);
    List<UserWord> findByUserId(Long userId);

    Optional<UserWord> findByUserIdAndWordId(Long userId, Long wordId);
}
