package zzl.leetcode;

import org.junit.Assert;

/**
 * @author zzl
 * @link {https://leetcode-cn.com/problems/house-robber-ii/}
 */
public class HouseRobberII {
    public static void main(String[] args) {
        Assert.assertEquals(new HouseRobberII().rob(new int[]{2, 3, 2}), 3);
        Assert.assertEquals(new HouseRobberII().rob(new int[]{1, 2, 3, 1}), 4);
    }

    /**
     * 需要注意的是首尾是个环
     * 首尾二选一，那就判断两次取最大，即：max([0,len-1],[1,len])
     *
     * @param nums
     * @return
     * @link {https://leetcode-cn.com/problems/house-robber-ii/solution/213-da-jia-jie-she-iidong-tai-gui-hua-jie-gou-hua-/}
     * @lint zzl.leetcode.HouseRobber
     */
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
        int f_x_2 = 0;
        int f_x_1 = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            // 当前的f_x_1就是下一次的f_x_2
            int temp = f_x_1;
            f_x_1 = Integer.max(f_x_2 + nums[i], f_x_1);
            f_x_2 = temp;
        }
        int money = f_x_1;
        f_x_2 = 0;
        f_x_1 = 0;
        for (int i = 1; i < nums.length; i++) {
            // 当前的f_x_1就是下一次的f_x_2
            int temp = f_x_1;
            f_x_1 = Integer.max(f_x_2 + nums[i], f_x_1);
            f_x_2 = temp;
        }

        return Integer.max(money, f_x_1);
    }
}
