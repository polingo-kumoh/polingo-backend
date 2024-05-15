package com.tangtang.polingo.situation.weather;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.tangtang.polingo.situation.weather.util.BaseTimeCalculator;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

public class BaseTimeCalculatorTest {

    @Test
    public void testBaseTimeCalculation() {
        // 01:15 시간대의 테스트
        LocalDateTime testTime1 = LocalDateTime.of(2024, 5, 15, 1, 15);
        assertEquals("0130", BaseTimeCalculator.execute(testTime1));

        // 14:45 시간대의 테스트
        LocalDateTime testTime2 = LocalDateTime.of(2024, 5, 15, 14, 45);
        assertEquals("1530", BaseTimeCalculator.execute(testTime2));

        // 23:59 시간대의 테스트
        LocalDateTime testTime3 = LocalDateTime.of(2024, 5, 15, 23, 59);
        assertEquals("2330", BaseTimeCalculator.execute(testTime3));

        // 00:00 시간대의 테스트
        LocalDateTime testTime4 = LocalDateTime.of(2024, 5, 15, 0, 0);
        assertEquals("0030", BaseTimeCalculator.execute(testTime4));
    }
}
