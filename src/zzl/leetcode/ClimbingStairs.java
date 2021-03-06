package zzl.leetcode;

import org.junit.Assert;

/**
 * 爬楼梯
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/climbing-stairs/}
 */
public class ClimbingStairs {
    public static void main(String[] args) {
        Assert.assertEquals(new ClimbingStairs().climbStairs(2), 2);
        Assert.assertEquals(new ClimbingStairs().climbStairs(3), 3);

    }

    /**
     * 优化后的动态规划，只使用了较少的变量，不用保存整个dp数组
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        int cur_i_1 = 1;
        int temp_i_2 = 1;
        int temp = 0;
        for (int i = 2; i <= n; i++) {
            temp = cur_i_1;
            cur_i_1 = cur_i_1 + temp_i_2;
            temp_i_2 = temp;
        }
        return cur_i_1;
    }

    /**
     * 动态规划1.0
     *
     * @param n
     * @return
     */
    public int climbStairsDP(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /**
     * 递归会超时
     *
     * @param n
     * @return
     */
    public int climbStairsWillTimeout(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return climbStairsWillTimeout(n - 1) + climbStairsWillTimeout(n - 2);
    }
}
