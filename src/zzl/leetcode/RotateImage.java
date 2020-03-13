package zzl.leetcode;

import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

/**
 * 旋转图像
 * 给定一个 n × n 的二维矩阵表示一个图像。将图像顺时针旋转 90 度。
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/rotate-image/}
 */
public class RotateImage {
    public static void main(String[] args) {
        int[][] input = GenerateUtil.generateBinaryIntArray(
                "1,2,3\n" +
                        "4,5,6\n" +
                        "7,8,9", ",");
        new RotateImage().rotate(input);
        PrintConsoleUtil.printArray(input);
        input = GenerateUtil.generateBinaryIntArray(
                "5, 1, 9,11\n" +
                        " 2, 4, 8,10\n" +
                        "13, 3, 6, 7\n" +
                        "15,14,12,16", ",");
        new RotateImage().rotate(input);
        PrintConsoleUtil.printArray(input);
    }

    /**
     * 先转置矩阵，然后翻转每一行
     *
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;

        // transpose matrix
        // 沿着对角线翻转
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int tmp = matrix[j][i];
                matrix[j][i] = matrix[i][j];
                matrix[i][j] = tmp;
            }
        }
        PrintConsoleUtil.printArray(matrix);
        // reverse each row
        // 每一行翻转
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = tmp;
            }
        }
        PrintConsoleUtil.printArray(matrix);
    }

}
