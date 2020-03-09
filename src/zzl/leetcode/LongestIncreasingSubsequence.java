package zzl.leetcode;

import org.junit.Assert;
import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

import java.util.Arrays;

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
        Assert.assertEquals(new LongestIncreasingSubsequence().lengthOfLISPlus(input), 4);
        input = GenerateUtil.generateIntArray("2,2", ",");
        Assert.assertEquals(new LongestIncreasingSubsequence().lengthOfLISPlus(input), 1);
        input = GenerateUtil.generateIntArray("4,10,4,3,8,9", ",");
        Assert.assertEquals(new LongestIncreasingSubsequence().lengthOfLISPlus(input), 3);
        input = GenerateUtil.generateIntArray("1,3,6,7,9,4,10,5,6", ",");
        Assert.assertEquals(new LongestIncreasingSubsequence().lengthOfLISPlus(input), 6);
    }

    /**
     * 动态规划+二分查找
     *
     * @param nums
     * @return
     * @link {https://leetcode-cn.com/problems/longest-increasing-subsequence/solution/zui-chang-shang-sheng-zi-xu-lie-dong-tai-gui-hua-2/}
     */
    public int lengthOfLISPlus(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        //tails[k] 的值代表长度为k+1子序列的尾部元素值。
        int[] tails = new int[nums.length];
        int res = 0;
        for (int num : nums) {
            int i = 0, j = res;
            while (i < j) {
                int m = (i + j) / 2;
                if (tails[m] < num) {
                    i = m + 1;
                } else {
                    j = m;
                }
            }
            tails[i] = num;
            PrintConsoleUtil.printArray(tails);
            if (res == j) {
                res++;
            }
        }
        // 同时tails表[0,res)就代表了最大子序列
        return res;
    }

    /**
     * 官方示例，但是很难懂
     *
     * @param nums
     * @return
     */
    public int lengthOfLISPlus1(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        int maxLength = 0;
        for (int i = 0; i < dp.length; i++) {
            int idx = Arrays.binarySearch(dp, 0, i, nums[i]);
            System.out.println(idx);
            if (idx < 0) {
                idx = -(idx + 1);
            }
            System.out.println("new: " + idx);
            dp[idx] = nums[i];
            if (idx == i) {
                maxLength++;
            }
            PrintConsoleUtil.printArray(nums);
            PrintConsoleUtil.printArray(dp);
        }
        return maxLength;
    }

    public int lengthOfLIS(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxLength = 1;
        for (int i = 1; i < dp.length; i++) {
            int maxval = 0;
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
