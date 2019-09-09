package cross;

/**
 * 得到交叉权重
 */
public class CRO {
    /* x数组存储实测数据，依次为：
     * 电压偏差，暂态压降，三相不平衡，电压波动，电压闪变，电压谐波，频率偏差，供电可靠性，服务性指标
     */

    //观测样本1
    final static double[] x1 = {3.212, 0.7963, 0.83, 1.33, 0.473, 1.72, 0.0922, 0.833, 0.832};
    //观测样本2
    final static double[] x2 = {6.68, 0.1589, 1.36, 1.53, 0.847, 4.28, 0.1562, 0.762, 0.713};
    //观测样本3
    final static double[] x3 = {4.35, 0.5156, 1.35, 1.95, 0.634, 2.67, 0.118, 0.796, 0.864};
    //观测样本4
    final static double[] x4 = {5.33, 0.5856, 1.74, 1.37, 0.826, 3.36, 0.1787, 0.74, 0.684};
    //观测样本5
    final static double[] x5 = {4.22, 0.4863, 1.83, 1.58, 0.828, 4.57, 0.1892, 0.764, 0.783};

    public static double[][] getG() {
        // 无量纲标准化测试数据。
        double[] g1 = Standardization.getG(x1);
        double[] g2 = Standardization.getG(x2);
        double[] g3 = Standardization.getG(x3);
        double[] g4 = Standardization.getG(x4);
        double[] g5 = Standardization.getG(x5);
        double[][] g = CombineG.getG(g1, g2, g3, g4, g5);
        return g;
    }

    public double[] get_cro_w() {


        // 无量纲标准化测试数据。
        double[] g1 = Standardization.getG(x1);
        double[] g2 = Standardization.getG(x2);
        double[] g3 = Standardization.getG(x3);
        double[] g4 = Standardization.getG(x4);
        double[] g5 = Standardization.getG(x5);
        double[][] g = CombineG.getG(g1, g2, g3, g4, g5);
        System.out.println("g:");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(String.format("%.4f", g[i][j]) + "\t");
            }
            System.out.println();
        }
        // 至此，无量纲标准化完成。

        // 求解最优化模型，得出权重值。
        double[][] e = CharacteristicRatio.getE(g);
        System.out.println("e:");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(String.format("%.4f", e[i][j]) + "\t");
            }
            System.out.println();
        }
        double den = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    den += CrossD.getD(e, i, j, k);
                }
            }
        }

        double[] w_bi = new double[9];
        for (int i = 0; i < 9; i++) {
            double num = 0;
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    num += CrossD.getD(e, i, j, k);
                }
            }
            w_bi[i] = num / den;
        }
        // 至此，客观权重值求解完毕。
        System.out.println("\n客观权重值:");
        for (int i = 0; i < 9; i++) {
            System.out.print(String.format("%.4f", w_bi[i]) + "\t");
        }
        return w_bi;
    }
}
