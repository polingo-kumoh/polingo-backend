package com.tangtang.polingo.news.repository;

import com.tangtang.polingo.news.entity.NewsScrap;
import com.tangtang.polingo.user.entity.User;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface NewsScrapRepository extends CrudRepository<NewsScrap, Long> {
    Optional<NewsScrap> findByUserAndNewsId(User user, Long newsId);
}
