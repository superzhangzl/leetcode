package zzl.leetcode;

import org.junit.Assert;
import zzl.base.annotation.Level;
import zzl.util.GenerateUtil;

import java.util.Arrays;

import static zzl.base.enums.Difficulty.*;

/**
 * 分割等和子集
 *
 * @author zzl
 * @date 2021-04-20
 * @link {https://leetcode-cn.com/problems/partition-equal-subset-sum/}
 */
@Level(MEDIUM)
public class PartitionEqualSubsetSum {
    public static void main(String[] args) {
        int[] nums;
        boolean canPartition;
        //
        nums = GenerateUtil.generateIntArray("1,5,11,5");
        canPartition = new PartitionEqualSubsetSum().canPartition(nums);
        Assert.assertEquals(canPartition, true);
        //
        nums = GenerateUtil.generateIntArray("1,2,3,5");
        canPartition = new PartitionEqualSubsetSum().canPartition(nums);
        Assert.assertEquals(canPartition, false);
    }

    /**
     * 动态规划，子集背包问题
     * 可参考“目标和”算法
     * <p>
     * TODO: 待一维压缩
     *
     * @param nums
     * @return
     * @see TargetSum#fullBagDp(int[], int)
     */
    public boolean canPartition(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        // 如果和是奇数，那肯定不可能
        if (sum % 2 == 1) {
            return false;
        }
        int target = sum / 2;
        int len = nums.length;
        /*
        dp定义：选择前i个物品且目标为j时能否恰好装满，
         */
        boolean[][] dp = new boolean[len + 1][target + 1];

        for (int i = 0; i < len; i++) {
            dp[i][0] = true;
        }
        for (int i = 1; i < len + 1; i++) {
            for (int j = 1; j < target + 1; j++) {
                if (j - nums[i - 1] < 0) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[len][target];
    }
}
