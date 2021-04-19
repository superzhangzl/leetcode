package zzl.leetcode;

import org.junit.Assert;
import zzl.base.annotation.Level;
import zzl.util.GenerateUtil;

import static zzl.base.enums.Difficulty.*;

/**
 * 买卖股票的最佳时机 II
 *
 * @author zzl
 * @date 2021-04-19
 * @link {https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/}
 */
@Level(SIMPLE)
public class BestTimeToBuyAndSellStockII {
    public static void main(String[] args) {
        int[] prices;
        int maxProfit;
        //
        prices = GenerateUtil.generateIntArray("7,1,5,3,6,4");
        maxProfit = new BestTimeToBuyAndSellStockII().maxProfit(prices);
        Assert.assertEquals(maxProfit, 7);
        //
        prices = GenerateUtil.generateIntArray("1,2,3");
        maxProfit = new BestTimeToBuyAndSellStockII().maxProfit(prices);
        Assert.assertEquals(maxProfit, 2);
    }

    /**
     * 动态规划
     *
     * @param prices
     * @return
     * @link {https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/solution/mai-mai-gu-piao-de-zui-jia-shi-ji-ii-by-leetcode-s/}
     * @see BestTimeToBuyAndSellStockWithCooldown#maxProfit(int[]) 中的画图表示状态转换
     */
    public int maxProfit(int[] prices) {
        /*
        有两种状态，持有股票和不持有股票
        dp的定义：
            dp[i][0]: 第i天不持有股票时的最大受益
            dp[i][1]: 第i天持有股票时的最大收益
        */
        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[prices.length - 1][0];
    }

    /**
     * LeetCode评论区解答：
     * <p>
     * 因为交易次数不受限，如果可以把所有的上坡全部收集到，一定是利益最大化的
     * 假设prices = [1, 2, 3]，按照处理逻辑，(2-1) + (3-2)等价于(3-1)
     *
     * @param prices
     * @return
     */
    public int maxProfit_best(int[] prices) {
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                maxProfit += (prices[i] - prices[i - 1]);
            }
        }
        return maxProfit;
    }
}
