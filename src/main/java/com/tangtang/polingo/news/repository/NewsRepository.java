package com.tangtang.polingo.news.repository;

import com.tangtang.polingo.global.constant.Language;
import org.springframework.data.domain.Page;
import com.tangtang.polingo.news.dto.NewsSummaryResponse;
import com.tangtang.polingo.news.entity.News;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    @Query("SELECT new com.tangtang.polingo.news.dto.NewsSummaryResponse(n.id, n.imageUrl, n.title, n.publishDate, (SELECT ns.originText FROM NewsSentence ns WHERE ns.news = n AND ns.id = (SELECT MIN(ns2.id) FROM NewsSentence ns2 WHERE ns2.news = n))) " +
            "FROM News n WHERE (:language IS NULL OR n.language = :language) ORDER BY n.publishDate DESC")
    Page<NewsSummaryResponse> findNewsSummariesByLanguage(@Param("language") Language language, Pageable pageable);
}


