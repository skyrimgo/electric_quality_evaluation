package correlation;

import cross.CRO;
import util.Maxsg;
import util.Minsg;
import util.Transfer;

public class Rplus {
    public static double getRplus(int i, int j) {
        double[][] g = Transfer.transfer(CRO.getG());
        double min = Minsg.getminsg(1.0, g);
        double max = Maxsg.getmaxsg(1.0, g);
        return (min + max) / (Math.abs(1.0 - g[i][j]) + max);
    }
}
