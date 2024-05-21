package com.tangtang.polingo.situation.repository;

import com.tangtang.polingo.situation.entity.Category;
import com.tangtang.polingo.situation.entity.Situation;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SituationRepository extends JpaRepository<Situation, Long> {
    Optional<Situation> findByCategory(Category category);

    Optional<Situation> findByName(String today);
}
