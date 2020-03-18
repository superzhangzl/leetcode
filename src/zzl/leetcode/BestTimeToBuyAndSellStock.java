package zzl.leetcode;

import org.junit.Assert;
import zzl.util.GenerateUtil;

/**
 * 买卖股票的最佳时机
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/}
 */
public class BestTimeToBuyAndSellStock {
    public static void main(String[] args) {
        int[] input = GenerateUtil.generateIntArray("7,1,5,3,6,4", ",");
        Assert.assertEquals(new BestTimeToBuyAndSellStock().maxProfit(input), 5);
    }

    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            }
            if (prices[i] - minPrice > maxProfit) {
                maxProfit = prices[i] - minPrice;
            }
        }
        return maxProfit;
    }
}
