package zzl.leetcode;

import org.junit.Assert;
import zzl.base.annotation.Level;
import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

import java.util.Arrays;

import static java.lang.Math.min;
import static zzl.base.enums.Difficulty.*;

/**
 * 合并石头的最低成本
 *
 * @author zzl
 * @date 2021-04-21
 * @link {https://leetcode-cn.com/problems/minimum-cost-to-merge-stones/}
 */
@Level(HARD)
public class MinimumCostToMergeStones {
    public static void main(String[] args) {
        int[] stones;
        int mergeStones;
        //
        stones = GenerateUtil.generateIntArray("3,2,4,1");
        mergeStones = new MinimumCostToMergeStones().mergeStones(stones, 2);
        Assert.assertEquals(mergeStones, 20);
        //
        stones = GenerateUtil.generateIntArray("3,2,4,1");
        mergeStones = new MinimumCostToMergeStones().mergeStones(stones, 3);
        Assert.assertEquals(mergeStones, -1);
        //
        stones = GenerateUtil.generateIntArray("3,5,1,2,6");
        mergeStones = new MinimumCostToMergeStones().mergeStones(stones, 3);
        Assert.assertEquals(mergeStones, 25);
    }

    /**
     * 动态规划
     * <p>
     * TODO: 暂存，待分析
     *
     * @param stones
     * @param K
     * @return
     * @link {https://www.acwing.com/solution/content/11876/}
     */
    public int mergeStones(int[] stones, int K) {
        int n = stones.length;
        if ((n - 1) % (K - 1) != 0) return -1;
        if (n < 2) return 0;
        int[][][] dp = new int[n + 1][n + 1][K + 1];
        // 前缀和数组
        int[] sum = new int[n + 1];
        // 全部初始化为max
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k <= K; k++) {
                    dp[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }
        for (int i = 1; i < n + 1; i++) {
            sum[i] = sum[i - 1] + stones[i - 1];
            dp[i][i][1] = 0;
        }
        for (int len = 2; len <= n; len++) {
            for (int i = 1; i + len - 1 <= n; i++) {
                int j = i + len - 1;
                for (int k = 2; k <= K; k++) {// 枚举连续堆数
                    for (int p = i; p < j; p++) {// 枚举分界点
                        dp[i][j][k] = min(dp[i][j][k], dp[i][p][k - 1] + dp[p + 1][j][1]);
                    }
                }
                dp[i][j][1] = min(dp[i][j][1], dp[i][j][K] + sum[j] - sum[i - 1]);
            }
        }
        return dp[1][n][1]; //把1到n堆合并成1堆的最小代价
    }
}
