package zzl.leetcode;

import org.junit.Assert;
import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

import java.util.Arrays;

/**
 * 戳气球
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/burst-balloons/}
 */
public class BurstBalloons {
    public static void main(String[] args) {
        int maxCoins;
        int[] nums;
        //
        nums = GenerateUtil.generateIntArray("3,1,5,8");
        maxCoins = new BurstBalloons().maxCoins(nums);
        Assert.assertEquals(maxCoins, 167);
        //
        nums = GenerateUtil.generateIntArray("1,5");
        maxCoins = new BurstBalloons().maxCoins(nums);
        Assert.assertEquals(maxCoins, 10);
    }

    /**
     * 动态规划
     *
     * @param nums
     * @return
     */
    public int maxCoins(int[] nums) {
        // 构造两头值为1的虚拟数组
        int len = nums.length;
        int[] newNums = new int[len + 2];
        newNums[0] = 1;
        newNums[len + 1] = 1;
        System.arraycopy(nums, 0, newNums, 1, len);
        len = len + 2;

        /*
        dp定义为：戳破(i,j)之间所有的气球所获得的最大分值
         */
        int[][] dp = new int[len][len];
        // dp不用更新初始值，不影响计算
//        for (int i = 0; i < newNums.length; i++) {
//            dp[i][i] = newNums[i];
//        }
        // i从下到上
        for (int i = len - 1; i >= 0; i--) {
            // j从左到右
            for (int j = i + 1; j < len; j++) {
                // 定义k为(i,j)之间被最后一个戳破的气球
                for (int k = i + 1; k < j; k++) {
                    // 择优做选择，选择最后戳破能获得最大分值的那个气球
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k][j] + newNums[i] * newNums[k] * newNums[j]);
                }
            }
        }
//        PrintConsoleUtil.printArray(dp);
        // 因为是开区间，所以两头的不会被选择，[0,len-1]为实际上原数组所对应的能获得的最大分值
        return dp[0][len - 1];
    }
}
