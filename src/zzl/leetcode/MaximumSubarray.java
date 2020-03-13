package zzl.leetcode;

import org.junit.Assert;
import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

/**
 * 最大子序和
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/maximum-subarray/}
 */
public class MaximumSubarray {
    public static void main(String[] args) {
        int[] input = GenerateUtil.generateIntArray("-2,1,-3,4,-1,2,1,-5,4", ",");
        Assert.assertEquals(new MaximumSubarray().maxSubArray(input), 6);
        input = GenerateUtil.generateIntArray("-1,-2", ",");
        Assert.assertEquals(new MaximumSubarray().maxSubArray(input), -1);
    }

    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            /*
            每次保存到当前位置为止的最大的和，即要么累计当前的值，要么抛弃从当前位置重新累加
            因为是要累加和，要是只记录[0,i]最大的值，后面会不好累加，无法判断上一个元素是否被加进去了
            dp[i] = max(num[i]+dp[i-1],num[i])
             */
            // 当前元素位置的最大和
            dp[i] = Integer.max(nums[i] + dp[i - 1], nums[i]);
            max = Integer.max(max, dp[i]);
        }
        PrintConsoleUtil.printArray(nums);
        PrintConsoleUtil.printArray(dp);
        return max;
    }
}
