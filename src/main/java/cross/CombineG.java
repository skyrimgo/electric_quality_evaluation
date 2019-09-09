package cross;

/**
 * 获取标准化后的评估矩阵G
 */
public class CombineG {
    public static double[][] getG(double[] g1, double[] g2, double[] g3, double[] g4, double[] g5) {

        double[][] g = new double[9][5];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                switch (j) {
                    case 0:
                        g[i][j] = g1[i];
                        break;
                    case 1:
                        g[i][j] = g2[i];
                        break;
                    case 2:
                        g[i][j] = g3[i];
                        break;
                    case 3:
                        g[i][j] = g4[i];
                        break;
                    case 4:
                        g[i][j] = g5[i];
                        break;
                    default:
                        break;
                }

            }

        }
        return g;
    }
}
