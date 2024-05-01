package com.tangtang.polingo.wordset.repository;

import com.tangtang.polingo.word.entity.Word;
import com.tangtang.polingo.wordset.entity.WordSet;
import com.tangtang.polingo.wordset.entity.WordSetWord;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface WordSetWordRepository extends CrudRepository<WordSetWord, Long> {
    boolean existsByWordSetAndWord(WordSet wordSet, Word word);

    List<WordSetWord> findAllByWordSet(WordSet wordSet);

    Optional<WordSetWord> findByWordSetIdAndWordId(Long wordSetId, Long wordId);

    @Query("SELECT wsw FROM WordSetWord wsw WHERE wsw.wordSet.id = :wordSetId AND wsw.word.id IN :wordIds")
    List<WordSetWord> findByWordSetIdAndWordIds(@Param("wordSetId") Long wordSetId,
                                                @Param("wordIds") List<Long> wordIds);
}
