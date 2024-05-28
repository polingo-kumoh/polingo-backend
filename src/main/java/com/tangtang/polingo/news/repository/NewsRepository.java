package com.tangtang.polingo.news.repository;

import com.tangtang.polingo.global.constant.Language;
import com.tangtang.polingo.news.dto.NewsSummaryResponse;
import com.tangtang.polingo.news.entity.News;
import com.tangtang.polingo.user.entity.User;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    @Query("SELECT new com.tangtang.polingo.news.dto.NewsSummaryResponse(" +
            "n.id, n.imageUrl, n.title, n.publishDate, n.language, " +
            "(SELECT COUNT(ns) > 0 FROM NewsScrap ns WHERE ns.news = n AND ns.user = :user), " +
            "(SELECT ns.originText FROM NewsSentence ns WHERE ns.news = n AND ns.id = (SELECT MIN(ns2.id) FROM NewsSentence ns2 WHERE ns2.news = n))"
            +
            ") " +
            "FROM News n " +
            "WHERE (:language IS NULL OR n.language = :language) " +
            "GROUP BY n.id, n.imageUrl, n.title, n.publishDate " +
            "ORDER BY n.publishDate DESC")
    Page<NewsSummaryResponse> findNewsSummariesByLanguage(@Param("user") User user,
                                                          @Param("language") Language language, Pageable pageable);



    @Query("SELECT new com.tangtang.polingo.news.dto.NewsSummaryResponse(" +
            "n.id, n.imageUrl, n.title, n.publishDate, n.language, " +
            "(SELECT COUNT(ns) > 0 FROM NewsScrap ns WHERE ns.news = n AND ns.user = :user), " +
            "(SELECT ns.originText FROM NewsSentence ns WHERE ns.news = n AND ns.id = (SELECT MIN(ns2.id) FROM NewsSentence ns2 WHERE ns2.news = n))"
            +
            ") " +
            "FROM News n " +
            "GROUP BY n.id, n.imageUrl, n.title, n.publishDate " +
            "ORDER BY n.publishDate DESC")
    Page<NewsSummaryResponse> findNewsSummaries(@Param("user") User user, Pageable pageable);

    @Query("SELECT n FROM News n LEFT JOIN FETCH n.newsSentences WHERE n.id = :newsId")
    Optional<News> findNewsByIdWithSentences(@Param("newsId") Long newsId);

    @Query("SELECT new com.tangtang.polingo.news.dto.NewsSummaryResponse(" +
            "n.id, n.imageUrl, n.title, n.publishDate, " +
            "(SELECT ns.originText FROM NewsSentence ns WHERE ns.news = n AND ns.id = (SELECT MIN(ns2.id) FROM NewsSentence ns2 WHERE ns2.news = n))) "
            +
            "FROM News n " +
            "JOIN n.newsScraps ns " +
            "WHERE ns.user = :user AND (:language IS NULL OR n.language = :language) " +
            "ORDER BY n.publishDate DESC")
    Page<NewsSummaryResponse> findScrapedNewsByUserAndLanguage(@Param("user") User user,
                                                               @Param("language") Language language, Pageable pageable);

}


