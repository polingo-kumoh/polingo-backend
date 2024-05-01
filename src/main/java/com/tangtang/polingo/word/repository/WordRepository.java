package com.tangtang.polingo.word.repository;

import com.tangtang.polingo.word.entity.Word;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface WordRepository extends CrudRepository<Word, Long> {
    Optional<Word> findByText(String text);

    @Query("SELECT w.description FROM Word w")
    List<String> findAllDescriptions(Pageable limit);
}
