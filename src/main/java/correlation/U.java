package correlation;

public class U {
    public static double getU(int i) {
        double vminus = Vminus.getVminus(i);
        double vplus = Vplus.getVplus(i);
        double v = V.getV(i);
        return (vminus - v) * (vminus - v) / ((vminus - v) * (vminus - v) + (vplus - v) * (vplus - v));

    }
}
