package zzl.leetcode;

import org.junit.Assert;
import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

/**
 * 二维区域和检索 - 矩阵不可变
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/range-sum-query-2d-immutable/}
 */
public class RangeSumQuery2dImmutable {

    public static void main(String[] args) {
        int[][] input = GenerateUtil.generateBinaryIntArray(
                "3, 0, 1, 4, 2\n" +
                        "5, 6, 3, 2, 1\n" +
                        "1, 2, 0, 1, 5\n" +
                        "4, 1, 0, 1, 7\n" +
                        "1, 0, 3, 0, 5", ",");
//        PrintConsoleUtil.printArray(input);
        NumMatrix numMatrix = new NumMatrix(input);
        /*
        sumRegion(2, 1, 4, 3) -> 8
        sumRegion(1, 1, 2, 2) -> 11
        sumRegion(1, 2, 2, 4) -> 12
         */
        Assert.assertEquals(numMatrix.sumRegion(2, 1, 4, 3), 8);
        Assert.assertEquals(numMatrix.sumRegion(1, 1, 2, 2), 11);
        Assert.assertEquals(numMatrix.sumRegion(1, 2, 2, 4), 12);
        numMatrix = new NumMatrix(new int[][]{});
        /*
        sumRegion(2, 1, 4, 3) -> 8
        sumRegion(1, 1, 2, 2) -> 11
        sumRegion(1, 2, 2, 4) -> 12
         */
        Assert.assertEquals(numMatrix.sumRegion(2, 1, 4, 3), 8);
        Assert.assertEquals(numMatrix.sumRegion(1, 1, 2, 2), 11);
        Assert.assertEquals(numMatrix.sumRegion(1, 2, 2, 4), 12);
        Assert.assertEquals(numMatrix.sumRegion(1, 2, 2, 4), 12);
    }

    static class NumMatrix {
        int height;
        int width;
        private int[][] cache;

        public NumMatrix(int[][] matrix) {
            if (matrix == null || matrix.length == 0) {
                this.cache = new int[][]{};
                return;
            }
            this.height = matrix.length;
            this.width = matrix[0].length;
            this.cache = new int[height][width];
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (j == 0) {
                        this.cache[i][j] = matrix[i][j];
                    } else {
                        this.cache[i][j] = matrix[i][j] + cache[i][j - 1];
                    }
                }
            }
            PrintConsoleUtil.printArray(cache);
        }

        /**
         * 注：会多次调用 sumRegion 方法。
         * 单纯缓存所有结果会超内存限制，暴力求解会超时
         *
         * @param row1
         * @param col1
         * @param row2
         * @param col2
         * @return
         */
        public int sumRegion(int row1, int col1, int row2, int col2) {
            if (cache.length == 0) {
                return 0;
            }
            //上图子矩阵左上角 (row1, col1) = (2, 1) ，右下角(row2, col2) = (4, 3)，该子矩形内元素的总和为 8。
            System.out.println(String.format("(%d, %d) => (%d, %d)", row1, col1, row2, col2));
            int sum = 0;
            for (int i = row1; i <= row2; i++) {
                if (col1 - 1 >= 0) {
                    sum += (cache[i][col2] - cache[i][col1 - 1]);
                } else {
                    sum += (cache[i][col2]);
                }
            }
            return sum;
        }
    }
}
