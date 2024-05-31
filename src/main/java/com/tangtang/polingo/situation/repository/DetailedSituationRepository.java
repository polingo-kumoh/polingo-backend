package com.tangtang.polingo.situation.repository;

import com.tangtang.polingo.situation.entity.DetailedSituation;
import com.tangtang.polingo.situation.entity.Situation;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailedSituationRepository extends JpaRepository<DetailedSituation, Long> {
    Optional<DetailedSituation> findByName(String name);

    Optional<DetailedSituation> findByNameAndSituation(String detailedName, Situation situation);
}
