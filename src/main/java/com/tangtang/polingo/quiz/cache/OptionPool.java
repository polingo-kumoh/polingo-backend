package com.tangtang.polingo.quiz.cache;

import java.util.ArrayList;
import java.util.List;

public class OptionPool {
    private final List<String> options;
    private int currentIndex = 0;

    public OptionPool(List<String> options) {
        this.options = new ArrayList<>(options);
    }

    public List<String> getNextOptions(int count) {
        if (currentIndex + count > options.size()) {
            throw new IllegalStateException("더이상 가져올 수 없습니다.");
        }

        List<String> selectedOptions = options.subList(currentIndex, currentIndex + count);
        currentIndex += count;

        // 반환 시 복사본 생성
        return new ArrayList<>(selectedOptions);
    }
}

