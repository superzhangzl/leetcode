package zzl.leetcode;

import org.junit.Assert;

import java.util.Arrays;

/**
 * @author zzl
 * @link {https://leetcode-cn.com/problems/unique-paths/}
 */
public class UniquePaths {
    public static void main(String[] args) {
//        Assert.assertEquals(new UniquePaths().uniquePaths(3, 2), 3);
//        Assert.assertEquals(new UniquePaths().uniquePaths(7, 3), 28);
        Assert.assertEquals(new UniquePaths().uniquePaths(10, 10), 48620);
        Assert.assertEquals(new UniquePaths().uniquePaths(23, 12), 193536720);
    }
    /**
     * 组合问题，向下的个数是固定的，向右然后拐弯的个数不确定，比如向右走2步就拐，或者走3步再拐弯
     * C_{m+n-2}^{m-1}
     * 但是这个写法会溢出
     *
     * @param m
     * @param n
     * @return
     */


    /**
     * 变相的杨辉三角，最通俗的解法
     *
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < n; i++) dp[0][i] = 1;
        for (int i = 0; i < m; i++) dp[i][0] = 1;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                System.out.print(String.format("%2d ", dp[i][j]));
            }
            System.out.println();
        }
        System.out.println();
        return dp[m - 1][n - 1];
    }

    /**
     * @param m
     * @param n
     * @return
     * @link {https://leetcode-cn.com/problems/unique-paths/solution/dong-tai-gui-hua-by-powcai-2/}
     */
    public int uniquePaths2(int m, int n) {
        int[] cur = new int[n];
        Arrays.fill(cur, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                cur[j] += cur[j - 1];
            }
        }
        return cur[n - 1];
    }
}
