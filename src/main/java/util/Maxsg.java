package util;

public class Maxsg {
    public static double getmaxsg(double s, double[][] g) {
        double max = 0;
        for (int i = 0; i < g.length; i++) {
            for (int j = 0; j < g[0].length; j++) {
                max = Math.max(max, Math.abs(s - g[i][j]));
            }
        }
        return max;
    }
}
