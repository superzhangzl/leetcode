package zzl.leetcode;

import org.junit.Assert;
import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

/**
 * 零钱兑换 II
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/coin-change-2/}
 */
public class CoinChange2 {
    public static void main(String[] args) {
        int amount;
        int[] coins;
        int changeTotalCount;
        //
        amount = 5;
        coins = GenerateUtil.generateIntArray("1, 2, 5");
        changeTotalCount = new CoinChange2().change(amount, coins);
        Assert.assertEquals(changeTotalCount, 4);
        //
        amount = 3;
        coins = GenerateUtil.generateIntArray("2");
        changeTotalCount = new CoinChange2().change(amount, coins);
        Assert.assertEquals(changeTotalCount, 0);
        //
        amount = 10;
        coins = GenerateUtil.generateIntArray("10");
        changeTotalCount = new CoinChange2().change(amount, coins);
        Assert.assertEquals(changeTotalCount, 1);
    }

    /**
     * dp：一位数组进行空间压缩，并添加注释
     *
     * @param amount
     * @param coins
     * @return
     */
    public int change(int amount, int[] coins) {
        int n = coins.length;
        /*
        dp定义：只使用前i个物品，当背包容量为j时，有dp[i][j]种方法装满背包。
        即：使用前i个硬币，凑出金额j，有dp[i][j]种方法
         */
        int[] dp = new int[amount + 1];
        // 注：金额为0，不使用硬币这一种解法，即使用硬币个数为0这一种解法，所以设置为1；
        dp[0] = 1;
        /*
        每一个j只跟dp[i][]和dp[i-1][]有关，和j-1无关，将二维数组向下投影到一维即可实现状态压缩
         */
        System.out.print(String.format("[%2d]的面值 |", amount));
        for (int i = 0; i < amount + 1; i++) {
            System.out.print(String.format("%2d ", i));
        }
        System.out.println();
        System.out.println("-----------------------------------------------");
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= amount; j++) {
                // 此时金额j还没有通过前的i-1个硬币凑够
                // i从1开始，coins[i-1]对应的是i的面值
                if (j - coins[i] >= 0) {
                    dp[j] = dp[j] + dp[j - coins[i]];
                }
            }
            System.out.print(String.format("[%2d]种硬币 |", i + 1));
            PrintConsoleUtil.printArray(dp);
        }
        System.out.println();
        return dp[amount];
    }

    /**
     * dp：二维数组，未优化
     *
     * @param amount
     * @param coins
     * @return
     */
    public int change_bad(int amount, int[] coins) {
        int n = coins.length;
        /*
        dp定义：只使用前i个物品，当背包容量为j时，有dp[i][j]种方法装满背包。
        即：使用前i个硬币，凑出金额j，有dp[i][j]种方法
         */
        int[][] dp = new int[n + 1][amount + 1];
        // 注：金额为0，不使用硬币这一种解法，即使用硬币个数为0这一种解法，所以设置为1；
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= amount; j++) {
                // 此时金额j还没有通过前的i-1个硬币凑够
                // i从1开始，coins[i-1]对应的是i的面值
                if (j - coins[i - 1] >= 0) {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]];
                } else {
                    // 如果已经凑够了，那就保留之前的值即可，不用再凑了
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][amount];
    }
}
