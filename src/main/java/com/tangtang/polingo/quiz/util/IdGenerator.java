package com.tangtang.polingo.quiz.util;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class IdGenerator {
    private final Set<Integer> usedOptionIds = new HashSet<>();
    private final Random random = new Random();
    private int quizIdCounter = 1;

    public int generateQuizId() {
        return quizIdCounter++;
    }

    public int generateOptionId() {
        int optionId;
        do {
            optionId = 1 + random.nextInt(4);
        } while (!usedOptionIds.add(optionId));
        return optionId;
    }

    public void reset() {
        usedOptionIds.clear();
    }
}
