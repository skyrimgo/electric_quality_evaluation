package cross;

/**
 * 计算第i个指标下的第j个观测样本的特征比e_{ij}
 */
public class CharacteristicRatio {
    public static double[][] getE(double[][] g) {
        double[][] e = new double[9][5];
        for (int i = 0; i < 9; i++) {
            double sum = 0;
            for (int j = 0; j < 5; j++) {
                sum += g[i][j];
            }
            for (int j = 0; j < 5; j++) {
                e[i][j] = g[i][j] / sum;
            }
        }
        return e;
    }
}
