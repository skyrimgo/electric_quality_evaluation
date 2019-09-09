<p align="center"><img width="100px" src="https://www.easyicon.net/api/resizeApi.php?id=1141879&size=128"></p>

## 理想灰关联投影评估模型
电能质量综合评估具有多指标属性特征，在评估过程中参在各指标因素相互影响、制约的特征关系。而灰色关联投影法将灰色系统理论与矢量投影原理相结合，能够全面整体地分析指标间的相互关系，避免单方向偏差，具有数学基础牢固、理论意义明确、算法可操作性强的特点。
### 灰关联系数
经无量纲标准化处理后，各项指标均转化为效益型指标。


因此：

将绝对正理想状态解固定为1；绝对负理想状态解固定为0。

#### 主要代码
```bash
public static double getRplus(int i, int j) {
    double[][] g = Transfer.transfer(CRO.getG());
    double min = Minsg.getminsg(1.0, g);
    double max = Maxsg.getmaxsg(1.0, g);
    return (min + max) / (Math.abs(1.0 - g[i][j]) + max);
}
```

### 求解正/负理想状态下的投影值
求解第i个观测样本与电能质量正负理想状态的灰关联系数。
#### 主要代码
```bash
public static double getVplus(int i) {
    double vplus = 0;
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
        vplus += Rplus.getRplus(i, j) * w_mix[j] * w_mix[j] / den;
    }
    return vplus;
}
```

### 求解优属度
根据模糊集合论思想将优属度视为权重，于是通过正负理想状态解的广义加权距离，可求得优属度值。
#### 主要代码
```bash
public static double getU(int i) {
    double vminus = Vminus.getVminus(i);
    double vplus = Vplus.getVplus(i);
    double v = V.getV(i);
    return (vminus - v) * (vminus - v) / ((vminus - v) * (vminus - v) + (vplus - v) * (vplus - v));
}
```
优属度越大，表明待评样本整体越接近正理想状态，越远离负理想状态，故根据确定的优属度等级区间可得到电能质量的综合评估结果。
