package com.tangtang.polingo.word.admin.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.Refresh;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.DeleteRequest;
import co.elastic.clients.elasticsearch.core.DeleteResponse;
import co.elastic.clients.elasticsearch.core.GetRequest;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.UpdateRequest;
import co.elastic.clients.elasticsearch.core.UpdateResponse;
import co.elastic.clients.elasticsearch.core.search.TotalHits;
import com.tangtang.polingo.word.dto.AdminWordPostRequest;
import com.tangtang.polingo.word.dto.AdminWordResponse;
import com.tangtang.polingo.word.entity.WordDocument;
import jakarta.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminWordService {

    private final ElasticsearchClient elasticsearchClient;

    public Page<AdminWordResponse> getList(String searchQuery, Pageable pageable) throws IOException {
        SearchRequest searchRequest;

        if (searchQuery != null && !searchQuery.isEmpty()) {
            String field = isJapanese(searchQuery) ? "japanese_word" : "english_word";

            Query termQuery = Query.of(q -> q.term(t -> t.field(field).value(searchQuery)));

            searchRequest = SearchRequest.of(sr -> sr
                    .index("polingo_words")
                    .query(termQuery)
                    .source(sourceConfigBuilder -> sourceConfigBuilder.filter(
                            filterBuilder -> filterBuilder.includes("english_word", "japanese_word", "description",
                                    "id")))
                    .from((int) pageable.getOffset())
                    .size(pageable.getPageSize()));
        } else {
            searchRequest = SearchRequest.of(sr -> sr
                    .index("polingo_words")
                    .trackTotalHits(upTo -> upTo.count(100000))
                    .query(Query.of(q -> q.matchAll(ma -> ma)))
                    .source(sourceConfigBuilder -> sourceConfigBuilder.filter(
                            filterBuilder -> filterBuilder.includes("english_word", "japanese_word", "description",
                                    "id")))
                    .from((int) pageable.getOffset())
                    .size(pageable.getPageSize()));
        }

        log.info("expr = {}", searchRequest);

        SearchResponse<WordDocument> searchResponse = elasticsearchClient.search(searchRequest, WordDocument.class);

        TotalHits total = searchResponse.hits().total();
        long totalHits = total != null ? total.value() : 0;

        List<AdminWordResponse> wordResponses = searchResponse.hits().hits().stream()
                .map(hit -> {
                    WordDocument document = hit.source();
                    if (document != null) {
                        document.setId(hit.id());
                    }
                    return document;
                })
                .filter(Objects::nonNull)
                .map(this::mapToResponse)
                .collect(Collectors.toList());

        return new PageImpl<>(wordResponses, pageable, totalHits);
    }

    public void add(AdminWordPostRequest reqBody) throws IOException {
        WordDocument wordDocument = mapToDocument(reqBody);

        IndexRequest<WordDocument> request = IndexRequest.of(i -> i
                .index("polingo_words")
                .document(wordDocument)
                .refresh(Refresh.True));

        IndexResponse response = elasticsearchClient.index(request);
        log.info("Added document with ID: {}", response.id());
    }


    public void update(String wordId, AdminWordPostRequest reqBody) throws IOException {
        GetRequest getRequest = GetRequest.of(g -> g.index("polingo_words").id(wordId));
        GetResponse<WordDocument> getResponse = elasticsearchClient.get(getRequest, WordDocument.class);

        if (!getResponse.found()) {
            throw new EntityNotFoundException("Word not found");
        }

        WordDocument wordDocument = getResponse.source();
        if (wordDocument != null) {
            wordDocument.setEnglishWord(reqBody.getEnglishWord());
            wordDocument.setJapaneseWord(reqBody.getJapaneseWord());
            wordDocument.setDescription(reqBody.getDescription());

            UpdateRequest<WordDocument, WordDocument> updateRequest = UpdateRequest.of(u -> u
                    .index("polingo_words")
                    .id(wordId)
                    .doc(wordDocument)
                    .refresh(Refresh.True));

            UpdateResponse<WordDocument> updateResponse = elasticsearchClient.update(updateRequest, WordDocument.class);
            log.info("Updated document with ID: {}", updateResponse.id());
        }
    }

    public void delete(String wordId) throws IOException {
        DeleteRequest deleteRequest = DeleteRequest.of(d -> d.index("polingo_words").id(wordId));
        DeleteResponse deleteResponse = elasticsearchClient.delete(deleteRequest);
        log.info("Deleted document with ID: {}", deleteResponse.id());
    }

    private WordDocument mapToDocument(AdminWordPostRequest reqBody) {
        WordDocument wordDocument = new WordDocument();
        wordDocument.setEnglishWord(reqBody.getEnglishWord());
        wordDocument.setJapaneseWord(reqBody.getJapaneseWord());
        wordDocument.setDescription(reqBody.getDescription());
        return wordDocument;
    }

    private AdminWordResponse mapToResponse(WordDocument document) {
        AdminWordResponse response = new AdminWordResponse();
        response.setId(document.getId());
        response.setEnglishWord(document.getEnglishWord());
        response.setJapaneseWord(document.getJapaneseWord());
        response.setDescription(document.getDescription());
        return response;
    }

    private boolean isJapanese(String input) {
        for (char c : input.toCharArray()) {
            if (Character.UnicodeBlock.of(c) == Character.UnicodeBlock.HIRAGANA ||
                    Character.UnicodeBlock.of(c) == Character.UnicodeBlock.KATAKANA ||
                    Character.UnicodeBlock.of(c) == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS) {
                return true;
            }
        }
        return false;
    }
}
