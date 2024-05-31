package com.tangtang.polingo.situation.repository;

import com.tangtang.polingo.situation.entity.SituationImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<SituationImage, Long> {
}
