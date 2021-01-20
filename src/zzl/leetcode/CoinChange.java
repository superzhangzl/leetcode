package zzl.leetcode;

import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

import java.util.Arrays;

/**
 * 零钱兑换
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/coin-change/}
 */
public class CoinChange {
    public static void main(String[] args) {
        int[] coins = GenerateUtil.generateIntArray("1, 2, 5");
        CoinChange coinChange = new CoinChange();
        System.out.println(coinChange.coinChange(coins, 11));

    }


    /**
     * 官方解题思路：动态规划
     * 
     *
     * @param coins
     * @param amount
     * @return
     * @link {https://leetcode-cn.com/problems/coin-change/solution/322-ling-qian-dui-huan-by-leetcode-solution/}
     */
    public int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
