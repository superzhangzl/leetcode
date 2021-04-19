package zzl.leetcode;

import org.junit.Assert;
import zzl.base.annotation.Level;
import zzl.util.GenerateUtil;

import static zzl.base.enums.Difficulty.HARD;

/**
 * 买卖股票的最佳时机 IV
 *
 * @author zzl
 * @date 2021-04-19
 * @link {https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/}
 */
@Level(HARD)
public class BestTimeToBuyAndSellStockIv {
    public static void main(String[] args) {
        int[] prices;
        int maxProfit;
        //
        prices = GenerateUtil.generateIntArray("2,4,1");
        maxProfit = new BestTimeToBuyAndSellStockIv().maxProfit(2, prices);
        Assert.assertEquals(maxProfit, 2);
        System.out.println("===");
        //
        prices = GenerateUtil.generateIntArray("3,2,6,5,0,3");
        maxProfit = new BestTimeToBuyAndSellStockIv().maxProfit(2, prices);
        Assert.assertEquals(maxProfit, 7);
    }

    /**
     * 推理过程详见{@link BestTimeToBuyAndSellStockIII#maxProfit(int[])}
     * 算法III是当前算法的特殊情况，即k=2时的特例，推导思路是相同的
     * 注：
     * 最大收益的dp和i无关，所以二维数组即可，加上i所在的维度，会导致后续迭代时，状态值更新错误
     *
     * @param k
     * @param prices
     * @return
     */
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        if (k < 1) {
            return 0;
        }
        int n = prices.length;
        // 定义为二维数组即可，不需要加i的分量
        int[][] dp = new int[k][2];
        for (int i = 0; i < k; i++) {
            if (i == 0) {
                dp[i][0] = -prices[0];
            } else {
                dp[i][0] = Integer.MIN_VALUE;
            }
            dp[i][1] = Integer.MIN_VALUE;
        }
        for (int i = 1; i < n; i++) {
            // k=1时的特例，人工处理一下
            dp[0][0] = Math.max(dp[0][0], 0 - prices[i]);
            dp[0][1] = Math.max(dp[0][1], dp[0][0] + prices[i]);
            for (int j = 1; j < k; j++) {
                dp[j][0] = Math.max(dp[j][0], dp[j - 1][1] - prices[i]);
                dp[j][1] = Math.max(dp[j][1], dp[j][0] + prices[i]);
            }
        }
        int res = Integer.MIN_VALUE;
        // 返回所有k次中收益最大的一个结果
        for (int i = 0; i < k; i++) {
            res = Math.max(res, dp[i][1]);
        }
        return res;
    }
}
