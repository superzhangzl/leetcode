package zzl.leetcode;

import org.junit.Assert;
import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

import java.util.Stack;

/**
 * 最长上升子序列
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/longest-increasing-subsequence/}
 */
public class LongestIncreasingSubsequence {
    public static void main(String[] args) {
        // => 2,3,7,101
        int[] input = GenerateUtil.generateIntArray("10,9,2,5,3,7,101,18", ",");
        Assert.assertEquals(new LongestIncreasingSubsequence().lengthOfLIS(input), 4);
        input = GenerateUtil.generateIntArray("2,2", ",");
        Assert.assertEquals(new LongestIncreasingSubsequence().lengthOfLIS(input), 1);
        input = GenerateUtil.generateIntArray("4,10,4,3,8,9", ",");
        Assert.assertEquals(new LongestIncreasingSubsequence().lengthOfLIS(input), 3);
        input = GenerateUtil.generateIntArray("1,3,6,7,9,4,10,5,6", ",");
        Assert.assertEquals(new LongestIncreasingSubsequence().lengthOfLIS(input), 6);
    }

    /**
     * 动态规划
     *
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxLength = 1;
        for (int i = 1; i < dp.length; i++) {
            int maxval = 0;
            // 记录从[0,i)中比i小的数字所在的dp
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    maxval = Math.max(maxval, dp[j]);
                }
            }
            // 将当前的位置+1
            dp[i] = maxval + 1;
            maxLength = Math.max(maxLength, dp[i]);
        }
        return maxLength;
    }
}
