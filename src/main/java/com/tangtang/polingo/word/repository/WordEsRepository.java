package com.tangtang.polingo.word.repository;

import com.tangtang.polingo.word.entity.WordDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface WordEsRepository extends ElasticsearchRepository<WordDocument, String> {
}
