/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ahp;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.EigenDecomposition;
import org.apache.commons.math3.linear.RealVector;

/**
 * @author chris
 */
public class AHP {
    /*
    public void ahptest(double[][] bbt) {
        int dim = bbt.length;
        EigenDecomposition evd2;
        evd2 = new EigenDecomposition(
                new Array2DRowRealMatrix(bbt));
        double[] eigenvalues = evd2.getRealEigenvalues();
        // 得到正交矩阵，列是原矩阵的特征向量
        RealMatrix uHatrm = evd2.getV();
        double[][] uHat = new double[dim][];
        for (int i = 0; i < dim; i++) {
            uHat[i] = uHatrm.getRow(i);
        }
    }*/

    public double[] get_ahp_w() {
        // 四维矩阵
        int nrV = 9;
        double RI[] = {0.0, 0.0, 0.58, 0.9, 1.12, 1.24, 1.32, 1.41, 1.45, 1.49};

        double[][] matrix = new double[nrV][];
        for (int i = 0; i < nrV; i++) {
            matrix[i] = new double[nrV];
        }

        // diagonal
        // 主对角线元素为1
        for (int i = 0; i < nrV; i++) {
            matrix[i][i] = 1.0;
        }
        // double[] w_ahp = {9.6, 10, 10, 13, 7, 8.5, 14.4, 12, 15.5};
        double[] w_ahp = {6, 7, 7, 8, 3, 1, 3, 5, 10};
        // 右上三角
        for (int i = 0; i <= 7; i++) {
            for (int j = i + 1; j <= 8; j++) {
                matrix[i][j] = w_ahp[i] / w_ahp[j] * (0.9 + 0.1 * Math.random());
            }
        }

        // (i,k) is 1/(k,i)
        for (int k = 0; k < nrV; k++) {
            for (int i = 0; i < nrV; i++) {
                matrix[i][k] = 1.0 / matrix[k][i];
            }
        }
        // 显示判断矩阵
        System.out.println("判断矩阵：");
        for (int k = 0; k < nrV; k++) {
            for (int i = 0; i < nrV; i++) {
                System.out.print(String.format("%.3f", matrix[k][i]) + "\t");
            }
            System.out.println();
        }
        // 计算特征分解
        System.out.println("\n最大特征值的特征向量归一化（主观权重向量）：");
        EigenDecomposition evd = new EigenDecomposition(new Array2DRowRealMatrix(matrix));

        double sum = 0;
        double[] res = new double[9];
        for (int i = 0; i < 1; i++) {
            RealVector v = evd.getEigenvector(i);
            for (double d : v.toArray()) {
                sum += d;
            }
            //System.out.println(sum);
            for (double xx : v.toArray()) {
                System.out.print(String.format("%.4f", xx / sum) + "\t");
            }
            for (int j = 0; j < 9; j++) {
                res[j] = v.toArray()[j] / sum;
            }
            System.out.println();
            //System.out.println(v);
        }
        ;

        int evIdx = 0;
        //System.out.println("特征值：");
        for (int i = 0; i < evd.getRealEigenvalues().length; i++) {
            //System.out.println(evd.getRealEigenvalues()[i]);
            evIdx = (evd.getRealEigenvalue(i) > evd.getRealEigenvalue(evIdx)) ? i : evIdx;
        }

        // 得到最大的特征值
        System.out.println("\n最大的特征值：" + evd.getRealEigenvalue(evIdx));

        double ci = (evd.getRealEigenvalue(evIdx) - (double) nrV) / (double) (nrV - 1);
        // Consistency Index
        System.out.println("\n一致性指标: " + ci);

        // Consistency Ratio
        System.out.println("\n一致性比例: " + ci / RI[nrV] * 100 + "%");

        return res;
    }
}
