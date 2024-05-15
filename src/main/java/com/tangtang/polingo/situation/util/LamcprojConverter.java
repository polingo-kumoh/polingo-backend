package com.tangtang.polingo.situation.util;

import com.tangtang.polingo.situation.vo.Coordinate;

public class LamcprojConverter {

    private static final int NX = 149; // X축 격자점 수
    private static final int NY = 253; // Y축 격자점 수

    private static final double Re = 6371.00877;
    private static final double grid = 5.0;
    private static final double slat1 = 30.0;
    private static final double slat2 = 60.0;
    private static final double olon = 126.0;
    private static final double olat = 38.0;
    private static final double xo = 210 / grid;
    private static final double yo = 675 / grid;
    private static boolean first = false;

    public static Coordinate proceed(double lon, double lat, int code) {
        double x = 0, y = 0;
        return mapConv(lon, lat, x, y, code);
    }

    private static Coordinate mapConv(double lon, double lat, double x, double y, int code) {
        if (code == 0) {
            double[] result = lamcproj(lon, lat, true);
            x = result[0];
            y = result[1];
        } else if (code == 1) {
            double[] result = lamcproj(x, y, false);
            lon = result[0];
            lat = result[1];
        }
        return new Coordinate((int) Math.floor(x + 1.5), (int) Math.floor(y + 1.5));
    }

    private static double[] lamcproj(double lon, double lat, boolean isLonLatToGrid) {
        final double PI = Math.asin(1.0) * 2.0;
        final double DEGRAD = PI / 180.0;
        final double RADDEG = 180.0 / PI;

        if (!first) {
            first = true;
        }

        double re = Re / grid;
        double slat1_rad = slat1 * DEGRAD;
        double slat2_rad = slat2 * DEGRAD;
        double olon_rad = olon * DEGRAD;
        double olat_rad = olat * DEGRAD;

        double sn = Math.tan(PI * 0.25 + slat2_rad * 0.5) / Math.tan(PI * 0.25 + slat1_rad * 0.5);
        sn = Math.log(Math.cos(slat1_rad) / Math.cos(slat2_rad)) / Math.log(sn);
        double sf = Math.tan(PI * 0.25 + slat1_rad * 0.5);
        sf = Math.pow(sf, sn) * Math.cos(slat1_rad) / sn;
        double ro = Math.tan(PI * 0.25 + olat_rad * 0.5);
        ro = re * sf / Math.pow(ro, sn);

        if (isLonLatToGrid) {
            double ra = Math.tan(PI * 0.25 + lat * DEGRAD * 0.5);
            ra = re * sf / Math.pow(ra, sn);
            double theta = lon * DEGRAD - olon_rad;
            if (theta > PI) {
                theta -= 2.0 * PI;
            }
            if (theta < -PI) {
                theta += 2.0 * PI;
            }
            theta *= sn;
            double x = ra * Math.sin(theta) + xo;
            double y = ro - ra * Math.cos(theta) + yo;
            return new double[]{x, y};
        } else {
            double xn = lon - xo;
            double yn = ro - lat + yo;
            double ra = Math.sqrt(xn * xn + yn * yn);
            double alat = Math.pow(re * sf / ra, 1.0 / sn);
            alat = 2.0 * Math.atan(alat) - PI * 0.5;

            double theta;
            if (Math.abs(xn) <= 0.0) {
                theta = 0.0;
            } else {
                theta = Math.atan2(xn, yn);
            }
            double alon = theta / sn + olon_rad;
            return new double[]{alon * RADDEG, alat * RADDEG};
        }
    }
}
