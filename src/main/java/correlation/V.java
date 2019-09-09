package correlation;

import ahp.AHP;
import comprehensive.MIX;
import cross.CRO;

public class V {
    public static double getV(int i) {
        double vminus = 0;
        // 得到主观权重
        AHP ahp = new AHP();
        double[] w_ahp = ahp.get_ahp_w();
        // 得到客观权重
        CRO cro = new CRO();
        double[] w_cro = cro.get_cro_w();
        double[] w_mix = MIX.get_mix_w(w_ahp, w_cro);
        double den = 0;
        for (int j = 0; j < 9; j++) {
            den += w_mix[j] * w_mix[j];
        }
        den = Math.sqrt(den);
        for (int j = 0; j < 9; j++) {
            vminus +=  w_mix[j] * w_mix[j] / den;
        }
        return vminus;
    }

}
