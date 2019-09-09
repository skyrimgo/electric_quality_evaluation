import ahp.AHP;
import comprehensive.MIX;
import correlation.U;
import cross.*;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            double u = U.getU(i);
            String level;
            if (u >= 0.9673) {
                level = "1";
            } else if (u >= 0.7283) {
                level = "2";
            } else if (u >= 0.4128) {
                level = "3";
            } else {
                level = "4";
            }
            System.out.println("\n*********************level:" + level+";u="+u);
        }
    }
}
