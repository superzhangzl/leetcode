package zzl.leetcode;

import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

/**
 * 目标和
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/target-sum/}
 */
public class TargetSum {
    public static void main(String[] args) {
        int[] nums = GenerateUtil.generateIntArray("1, 1, 1, 1, 1");
        TargetSum targetSum = new TargetSum();
        int sumWays = targetSum.findTargetSumWays(nums, 3);
        System.out.println(sumWays);

    }

    /**
     * @param nums
     * @param S
     * @return
     * @link {https://leetcode-cn.com/problems/target-sum/solution/dong-tai-gui-hua-si-kao-quan-guo-cheng-by-keepal/}
     */
    public int findTargetSumWays(int[] nums, int S) {
        /*
         */
        int sum = 0;
        for (int num : nums) {
            sum += Math.abs(num);
        }
        // 如果累加起来都没有S大，那肯定就不会有命中了
        if (Math.abs(S) > Math.abs(sum)) {
            return 0;
        }
        int[][] dp = new int[nums.length][2 * sum + 1];

        if (nums[0] == 0) {
            // 0+0 和 0+0 是两个方法
            dp[0][sum] = 2;
        } else {
            // +nums[0] 和 -nums[0]算是两个情况
            dp[0][sum + nums[0]] = 1;
            dp[0][sum - nums[0]] = 1;
        }
        /*
        状态转移方程：
        dp[ i ][ j ] = dp[ i - 1 ][ j - nums[ i ] ] + dp[ i - 1 ][ j + nums[ i ] ]
        可以理解为，和为3的个数等于“和等于2的个数” 加上 “和等于1的个数“和“和等于-1的个数”
                那和为2的个数等于“和等于1的个数” 加上 “和等于1的个数”和“和等于-1的个数”
         */
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < 2 * sum + 1; j++) {
                // 边界
                int l = (j - nums[i]) >= 0 ? j - nums[i] : 0;
                int r = (j + nums[i]) < 2 * sum + 1 ? j + nums[i] : 0;
                dp[i][j] = dp[i - 1][l] + dp[i - 1][r];
            }
        }
        PrintConsoleUtil.printArray(dp);
        // sum+S 是为了保持偏移量，因为数组是对称的，实际的起始点的下标是[sum]。
        // 也可以以sum下标为0，左右正负添加1
        return dp[nums.length - 1][sum + S];
    }
}
