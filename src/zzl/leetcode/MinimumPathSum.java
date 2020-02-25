package zzl.leetcode;

import org.junit.Assert;

/**
 * @author zzl
 * @link {https://leetcode-cn.com/problems/minimum-path-sum/}
 */
public class MinimumPathSum {
    public static void main(String[] args) {
        int count = new MinimumPathSum().minPathSum(new int[][]{
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        });
        Assert.assertEquals(count, 7);
    }

    public int minPathSum(int[][] grid) {
        int weight = grid.length;
        int height = grid[0].length;
        // 直接在原数组基础上执行
        // 注：看到一个耗时更短的，将最上一行和最左一列拿出来单独执行
        //  然后遍历的时候直接从(1,1)坐标开始
        for (int i = 0; i < weight; i++) {
            for (int j = 0; j < height; j++) {
                if (i == 0 && j == 0) {
                    grid[i][j] = grid[i][j];
                } else if (i == 0 && j != 0) {
                    grid[i][j] = grid[i][j] + grid[i][j - 1];
                } else if (i != 0 && j == 0) {
                    grid[i][j] = grid[i][j] + grid[i - 1][j];
                } else if (i != 0 && j != 0) {
                    grid[i][j] = grid[i][j] + Math.min(grid[i - 1][j], grid[i][j - 1]);
                }
            }
        }
        return grid[weight - 1][height - 1];
    }

    public int minPathSumOld(int[][] grid) {
        int weight = grid.length;
        int height = grid[0].length;
        int[][] dp = new int[weight][height];
        // 使用额外的二维数组来存储对应的位置的最小值
        for (int i = 0; i < weight; i++) {
            for (int j = 0; j < height; j++) {
                System.out.print(String.format("%2d ", grid[i][j]));
            }
            System.out.println();
        }
        System.out.println();
        for (int i = 0; i < weight; i++) {
            for (int j = 0; j < height; j++) {
                if (i == 0 && j == 0) {
                    // 初始位置
                    dp[i][j] = grid[i][j];
                } else if (i == 0 && j != 0) {
                    // 最上一行，只能加左边的值
                    dp[i][j] = grid[i][j] + dp[i][j - 1];
                } else if (i != 0 && j == 0) {
                    // 最左一列，只能加上面的值
                    dp[i][j] = grid[i][j] + dp[i - 1][j];
                } else if (i != 0 && j != 0) {
                    // 其他位置，可以选上边或者左边中最小的一个
                    dp[i][j] = grid[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        for (int i = 0; i < weight; i++) {
            for (int j = 0; j < height; j++) {
                System.out.print(String.format("%2d ", dp[i][j]));
            }
            System.out.println();
        }
        return dp[weight - 1][height - 1];
    }
}
