package cross;

public class CrossD {
    public static double getD(double[][] e, int i, int j, int k) {
        // d为第i个指标下观测样本j与k之间的交叉熵
        double d = Math.abs(e[i][j] * Math.log(e[i][j] / e[i][k])) + Math.abs(e[i][k] * Math.log(e[i][k] / e[i][j]));
        return d;
    }
}
