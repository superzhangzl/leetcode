package zzl.leetcode;

import org.junit.Assert;

import java.util.Arrays;

/**
 * @author zzl
 * @link {https://leetcode-cn.com/problems/unique-paths-ii/}
 */
public class UniquePathsII {
    public static void main(String[] args) {
        int count = new UniquePathsII().uniquePathsWithObstacles(new int[][]{
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        });
        Assert.assertEquals(count, 2);
        count = new UniquePathsII().uniquePathsWithObstacles(new int[][]{
                {0, 1, 0},
        });
        Assert.assertEquals(count, 0);
        count = new UniquePathsII().uniquePathsWithObstacles(new int[][]{
                {1},
        });
        Assert.assertEquals(count, 0);
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        for (int i = 0; i < n; i++) {
            // 初始化时候遇到障碍直接中断，后续的路也是没办法走的
            if (obstacleGrid[0][i] == 1) {
                break;
            }
            dp[0][i] = 1;
        }
        for (int i = 0; i < m; i++) {
            // 初始化时候遇到障碍直接中断，后续的路也是没办法走的
            if (obstacleGrid[i][0] == 1) {
                break;
            }
            dp[i][0] = 1;
        }


        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                System.out.print(String.format("%2d ", dp[i][j]));
            }
            System.out.println();
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 遇到障碍就直接跳过
                if (obstacleGrid[i][j] == 1) {
                    continue;
                }
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
}
