<p align="center"><img width="100px" src="https://www.easyicon.net/api/resizeApi.php?id=1141870&size=128"></p>

## 求解综合权重
综合主观权重值和客观权重值，采用加法原理得出各项指标的综合权重。
#### 实现代码
```bash
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
```
#### 综合权重
权值依次对应电压偏差，暂态压降，三相不平衡，电压波动，电压闪变，电压谐波，频率偏差，供电可靠性，服务性指标。
```
0.1160	0.1267	0.1324	0.1416	0.0730	0.0610	0.0944	0.0943	0.1605
```
