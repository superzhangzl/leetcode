package zzl.leetcode;

import org.junit.Assert;
import zzl.base.annotation.Level;
import zzl.util.GenerateUtil;

import static zzl.base.enums.Difficulty.HARD;

/**
 * 买卖股票的最佳时机 III
 *
 * @author zzl
 * @date 2021-04-19
 * @link {https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/}
 */
@Level(HARD)
public class BestTimeToBuyAndSellStockIII {
    public static void main(String[] args) {
        int[] prices;
        int maxProfit;
        //
        prices = GenerateUtil.generateIntArray("3,3,5,0,0,3,1,4");
        maxProfit = new BestTimeToBuyAndSellStockIII().maxProfit(prices);
        Assert.assertEquals(maxProfit, 6);
        //
        prices = GenerateUtil.generateIntArray("1,2,3,4,5");
        maxProfit = new BestTimeToBuyAndSellStockIII().maxProfit(prices);
        Assert.assertEquals(maxProfit, 4);
        //
        prices = GenerateUtil.generateIntArray("1");
        maxProfit = new BestTimeToBuyAndSellStockIII().maxProfit(prices);
        Assert.assertEquals(maxProfit, 0);
        //
        prices = GenerateUtil.generateIntArray("1,3");
        maxProfit = new BestTimeToBuyAndSellStockIII().maxProfit(prices);
        Assert.assertEquals(maxProfit, 2);
        //
        prices = GenerateUtil.generateIntArray("4,3");
        maxProfit = new BestTimeToBuyAndSellStockIII().maxProfit(prices);
        Assert.assertEquals(maxProfit, 0);
        //
        prices = GenerateUtil.generateIntArray("3,2,6,5,0,3");
        maxProfit = new BestTimeToBuyAndSellStockIII().maxProfit(prices);
        Assert.assertEquals(maxProfit, 7);
    }

    /**
     * 动态规划
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int n = prices.length;
        /*
        动态规划的5个状态
         */
        // 什么都不操作的状态
        int doNothing = 0;
        // 第一次买入
        int buy_1 = -prices[0];
        // 第一次完成交易
        int sell_1 = Integer.MIN_VALUE;
        // 第二次买入
        int buy_2 = Integer.MIN_VALUE;
        // 第二次完成交易
        int sell_2 = Integer.MIN_VALUE;
        /*
        buy_1, sell_1, buy_2, sell_2的初始状态设置为最小值不能设置为0，设置为0可能导致第一次买入时状态设置有误；
        如：第一次买入时，所有的收益都是负值，而如果用0比较的时候，0就会覆盖这个负值导致判断错误。
         */
        for (int i = 1; i < n; i++) {
            // 单纯为了好理解添加，全程都是0，因为啥操作都没有不买不卖肯定收益全程为0
            doNothing = Math.max(doNothing, doNothing);
            // 第一次买入，由上一次状态和doNothing转移来，买入后变成负值
            buy_1 = Math.max(buy_1, 0 - prices[i]);
            // 第一次完成交易，由上一次状态和第一次买入转移来。（上一次状态代表本次啥都不操作）
            sell_1 = Math.max(sell_1, buy_1 + prices[i]);
            // 第二次买入，由上一次状态和上一次完成交易转移来
            buy_2 = Math.max(buy_2, sell_1 - prices[i]);
            // 第二次完成交易，由上一次状态和上一次买入转移来
            sell_2 = Math.max(sell_2, buy_2 + prices[i]);
        }
        // 比较第一次完成交易和第二次完成交易做比较
        // 如果buy_2的初始值和buy_1一样，直接返回sell_2即可，那种情况下，sell_1的状态会直接刷新到sell_2上，但是这种方式不好理解
        return Math.max(sell_1, sell_2);
    }
}
