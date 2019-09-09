package comprehensive;

import java.util.Arrays;

/**
 * 基于主观权重和客观权重得到综合权重
 */
public class MIX {
    public static double[] get_mix_w(double[] ahp, double[] cro) {
        double[] t_ahp = new double[ahp.length];
        for (int i = 0; i < ahp.length; i++) {
            t_ahp[i] = ahp[i];
        }
        Arrays.sort(t_ahp);
        double m = ahp.length;
        double sum = 0;
        for (int i = 1; i <= m; i++) {
            sum += i * t_ahp[i - 1];
        }
        sum -= (m + 1.0) / m;
        double k1 = sum / (m - 1);
        double k2 = 1.0 - k1;
        double[] mix_w = new double[ahp.length];
        System.out.println("\n综合权重值：");
        for (int i = 0; i < m; i++) {
            mix_w[i] = k1 * ahp[i] + k2 * cro[i];
            System.out.print(String.format("%.4f", mix_w[i]) + "\t");
        }
        return mix_w;
    }
}
