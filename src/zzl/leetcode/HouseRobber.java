package zzl.leetcode;

import org.junit.Assert;

/**
 * 打家劫舍
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/house-robber/}
 */
public class HouseRobber {
    public static void main(String[] args) {
        Assert.assertEquals(new HouseRobber().rob(new int[]{1, 2, 3, 1}), 4);
        Assert.assertEquals(new HouseRobber().rob(new int[]{2, 7, 9, 3, 1}), 12);
    }

    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        if (nums.length == 2) {
            return Integer.max(nums[0], nums[1]);
        }
        // 状态转移方程： f(x) = max(f(x-2) + [x], f(x-1))
        // f(-1) = f(0) = 0
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (i - 2 < 0) {
                dp[i] = Integer.max(0 + nums[i], dp[i - 1]);
            } else {
                dp[i] = Integer.max(dp[i - 2] + nums[i], dp[i - 1]);
            }
        }
        return dp[dp.length - 1];
    }
}
