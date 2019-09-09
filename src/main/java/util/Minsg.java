package util;

public class Minsg {
    public static double getminsg(double s,double[][]g){
        double min=1;
        for (int i=0;i<g.length;i++){
            for (int j=0;j<g[0].length;j++){
                min=Math.min(min,Math.abs(s-g[i][j]));
            }
        }
        return min;
    }
}
