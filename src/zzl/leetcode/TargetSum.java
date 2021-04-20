package zzl.leetcode;

import org.junit.Assert;
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
        int[] nums;
        TargetSum targetSum = new TargetSum();
        int sumWays;
        //
        nums = GenerateUtil.generateIntArray("1, 1, 1, 1, 1");
        sumWays = targetSum.findTargetSumWays(nums, 3);
        Assert.assertEquals(sumWays, 5);
        //
        nums = GenerateUtil.generateIntArray("0,0,0,0,0,0,0,0,1");
        sumWays = targetSum.findTargetSumWays(nums, 1);
        Assert.assertEquals(sumWays, 256);

    }

    /**
     * 将目标和转换为完全背包问题
     * SUM(A) - SUM(B) = TARGET
     * SUM(A) = TARGET + SUM(B)
     * SUM(A) = (TARGET + SUM(B)) / 2
     * 即将问题转换为数组中存在几个子集A，是的A中元素和等于(TARGET + SUM(B)) / 2
     *
     * @param nums
     * @param S
     * @return
     * @link {《labuladong的算法小抄》 P211}
     */
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 特殊情况无法满足直接返回0
        if (sum < S || (sum + S) % 2 == 1) {
            return 0;
        }
        return fullBagDp(nums, (sum + S) / 2);
    }

    /**
     * 当前不做一维压缩，不好理解
     * TODO：待一维压缩，优化空间复杂度
     *
     * @param nums
     * @param target
     * @return
     */
    public int fullBagDp(int[] nums, int target) {
        int len = nums.length;
        int[][] dp = new int[len + 1][target + 1];
        for (int i = 0; i < len + 1; i++) {
            dp[i][0] = 1;
        }
        /*
        注意事项：每个数字只能被装一次，和完全背包有些区别
        i从1开始，因为i等于0的时候已经被选择过来，已经为0了，且j的遍历依赖上一轮的操作
         */
        for (int i = 1; i < len + 1; i++) {
            for (int j = 0; j <= target; j++) {
                if (j - nums[i - 1] >= 0) {
                    /*
                    dp为装满背包的总方法数：那就把两种情况对应的选择结果加起来（选择当前i还是不选择）
                     */
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]];
                } else {
                    // 背包空间不足，只能选择不装
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        PrintConsoleUtil.printArray(dp);
        return dp[len][target];
    }

    /**
     * @param nums
     * @param S
     * @return
     * @link {https://leetcode-cn.com/problems/target-sum/solution/dong-tai-gui-hua-si-kao-quan-guo-cheng-by-keepal/}
     */
    public int findTargetSumWays_bad(int[] nums, int S) {
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
