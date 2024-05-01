package com.tangtang.polingo.quiz.cache;

import com.tangtang.polingo.word.repository.WordRepository;
import jakarta.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OptionCache {
    private final WordRepository wordRepository;
    private List<String> descriptionCache;

    @PostConstruct
    public void initCache() {
        Pageable limit = PageRequest.of(0, 100);
        this.descriptionCache = new ArrayList<>(wordRepository.findAllDescriptions(limit));
    }

    public List<String> getShuffledOptions(int count) {
        List<String> shuffledDescriptions = new ArrayList<>(descriptionCache);
        Collections.shuffle(shuffledDescriptions);
        return shuffledDescriptions.subList(0, Math.min(count, shuffledDescriptions.size()));
    }
}
