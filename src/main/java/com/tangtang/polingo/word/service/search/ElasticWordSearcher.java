package com.tangtang.polingo.word.service.search;

import com.fasterxml.jackson.databind.JsonNode;
import com.tangtang.polingo.global.constant.Language;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class ElasticWordSearcher implements WordSearcher {
    private final RestTemplate restTemplate;
    @Value("${elasticsearch.base-uri}")
    private String baseUrl;

    @Override
    public Optional<String> search(Language code, String word) {
        String queryPath = "/polingo_words/_search";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String field = determineSearchField(code);
        String queryBody = String.format("{\"query\": {\"term\": {\"%s\": {\"value\": \"%s\"}}}}", field, word);
        HttpEntity<String> entity = new HttpEntity<>(queryBody, headers);

        ResponseEntity<JsonNode> response = restTemplate.exchange(
                baseUrl + queryPath, HttpMethod.POST, entity, JsonNode.class);

        return extractDescription(Objects.requireNonNull(response.getBody()));
    }

    private String determineSearchField(Language code) {
        if (code == Language.JAPANESE) {
            return "japanese_word";
        } else {
            return "english_word";
        }
    }

    private Optional<String> extractDescription(JsonNode jsonNode) {
        if (jsonNode.path("hits").path("total").path("value").asInt() == 0) {
            return Optional.empty();
        } else {
            String description = jsonNode.path("hits").path("hits").get(0).path("_source").path("description").asText();
            return Optional.of(description);
        }
    }
}
