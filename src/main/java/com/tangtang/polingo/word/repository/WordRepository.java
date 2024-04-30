package com.tangtang.polingo.word.repository;

import com.tangtang.polingo.word.entity.Word;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface WordRepository extends CrudRepository<Word, Long> {
    Optional<Word> findByText(String text);
}
