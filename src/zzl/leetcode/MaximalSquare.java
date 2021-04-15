package zzl.leetcode;

import org.junit.Assert;
import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

/**
 * 最大正方形
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/maximal-square/}
 */
public class MaximalSquare {
    public static void main(String[] args) {
        String input = "1 0 1 0 0\n" +
                "1 0 1 1 1\n" +
                "1 1 1 1 1\n" +
                "1 0 0 1 0";
        char[][] matrix = GenerateUtil.generateBinaryCharArray(input, " ");
        Assert.assertEquals(new MaximalSquare().maximalSquare(matrix), 4);
        Assert.assertEquals(new MaximalSquare().maximalSquare(new char[][]{{}}), 0);
        Assert.assertEquals(new MaximalSquare().maximalSquare(new char[][]{{'1'}}), 1);
    }

    /**
     * @param matrix
     * @return
     * @link {https://leetcode-cn.com/problems/maximal-square/solution/zui-da-zheng-fang-xing-by-leetcode/}
     */
    public int maximalSquare(char[][] matrix) {
        int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
        int[][] dp = new int[rows + 1][cols + 1];
        int maxsqlen = 0;
        /*
        动态规划：
        dp表中记录了从当前点开始，可以构成正方形的边长
        dp[i][j] = min(dp[i-1][j], dp[i-1][j-1], dp[i][j-1]) + 1
        同时记录最大变长.maxLen = max(dp[i][j], maxLen)
         */
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
                    maxsqlen = Math.max(maxsqlen, dp[i][j]);
                }
            }
        }
        return maxsqlen * maxsqlen;
    }

    public int maximalSquareBetter(char[][] matrix) {
        int rows = matrix.length, cols = rows > 0 ? matrix[0].length : 0;
        // 优化的动态规划，使用一维数组
        int[] dp = new int[cols + 1];
        int maxsqlen = 0, prev = 0;
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                int temp = dp[j];
                if (matrix[i - 1][j - 1] == '1') {
                    dp[j] = Math.min(Math.min(dp[j - 1], prev), dp[j]) + 1;
                    maxsqlen = Math.max(maxsqlen, dp[j]);
                } else {
                    dp[j] = 0;
                }
                prev = temp;
            }
        }
        return maxsqlen * maxsqlen;
    }
}
