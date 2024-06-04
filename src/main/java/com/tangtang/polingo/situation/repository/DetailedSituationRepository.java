package com.tangtang.polingo.situation.repository;

import com.tangtang.polingo.situation.entity.DetailedSituation;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailedSituationRepository extends JpaRepository<DetailedSituation, Long> {
    Optional<DetailedSituation> findByName(String name);

}
