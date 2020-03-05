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
        int f_x_2 = 0;
        int f_x_1 = 0;
        for (int i = 0; i < nums.length; i++) {
            // 当前的f_x_1就是下一次的f_x_2
            int temp = f_x_1;
            f_x_1 = Integer.max(f_x_2 + nums[i], f_x_1);
            f_x_2 = temp;
        }
        return f_x_1;
    }
}
