package correlation;

import cross.CRO;
import util.Maxsg;
import util.Minsg;
import util.Transfer;

public class Rminus {
    public static double getRminus(int i, int j) {
        double[][] g = Transfer.transfer(CRO.getG());
        double min = Minsg.getminsg(0.0, g);
        double max = Maxsg.getmaxsg(0.0, g);
        return (min + max) / (Math.abs(0.0 - g[i][j]) + max);
    }
}
