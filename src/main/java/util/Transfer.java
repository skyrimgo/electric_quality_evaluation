package util;

/**
 * 完成转置操作
 */

public class Transfer {
    public static double[][] transfer(double g[][]) {
        int m = g.length;
        int n = g[0].length;
        double[][] t_g = new double[n][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                t_g[j][i] = g[i][j];
            }
        }
        return t_g;
    }
}
