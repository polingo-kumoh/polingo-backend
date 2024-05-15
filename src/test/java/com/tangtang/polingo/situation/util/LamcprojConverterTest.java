package com.tangtang.polingo.situation.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.tangtang.polingo.situation.vo.Coordinate;
import org.junit.jupiter.api.Test;

public class LamcprojConverterTest {

    @Test
    public void testLonLatToGrid() {
        double lon = 128.422855555555;
        double lat = 36.1040361111111;
        int expectedNx = 86;
        int expectedNy = 95;

        Coordinate result = LamcprojConverter.proceed(lon, lat, 0);

        assertEquals(expectedNx, result.getNx());
        assertEquals(expectedNy, result.getNy());
    }
}
