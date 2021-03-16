package zzl.leetcode;

import org.junit.Assert;
import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

import java.util.Arrays;

/**
 * K 站中转内最便宜的航班
 * 有 n 个城市通过 m 个航班连接。每个航班都从城市 u 开始，以价格 w 抵达 v。
 * <p>
 * 现在给定所有的城市和航班，以及出发城市 src 和目的地 dst，
 * 你的任务是找到从 src 到 dst *最多*经过 k站中转的最便宜的价格。如果没有这样的路线，则输出 -1。
 * <p>
 * TODO: DFS
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/cheapest-flights-within-k-stops/}
 */
public class CheapestFlightsWithinKStops {
    public static void main(String[] args) {
        int n;
        int[][] edges;
        int src;
        int dst;
        int k;
        int cheapestPrice;
        //====
        n = 3;
        edges = GenerateUtil.generateBinaryIntArrayBetter("[[0,1,100],[1,2,100],[0,2,500]]");
        src = 0;
        dst = 2;
        k = 2;
        cheapestPrice = new CheapestFlightsWithinKStops().findCheapestPrice(n, edges, src, dst, k);
        Assert.assertEquals(cheapestPrice, 200);
        //====
        n = 3;
        edges = GenerateUtil.generateBinaryIntArrayBetter("[[0,1,100],[1,2,100],[0,2,500]]");
        src = 0;
        dst = 2;
        k = 0;
//        cheapestPrice = new CheapestFlightsWithinKStops().findCheapestPrice(n, edges, src, dst, k);
//        Assert.assertEquals(cheapestPrice, 500);
    }

    /**
     * 动态规划
     *
     * @param n
     * @param flights
     * @param src
     * @param dst
     * @param K
     * @return
     * @link {https://leetcode-cn.com/problems/cheapest-flights-within-k-stops/solution/k-zhan-zhong-zhuan-nei-zui-bian-yi-de-hang-ban-b-2/}
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        // dp[i][k]是经过k个中转站后到达站 i 的最小费用
        int[][] dp = new int[n][K + 1];

        // 循环初始化整个二维数组。
        for (int i = 0; i < n; ++i) Arrays.fill(dp[i], Integer.MAX_VALUE);

        // 利用flights中的信息初始化src可直达的班次
        for (int[] flight : flights) {
            if (flight[0] == src) {
                dp[flight[1]][0] = flight[2];
            }
        }

        // 循环初始化数组中dst == src的行
        for (int i = 0; i <= K; i++) {
            dp[src][i] = 0;
        }
        PrintConsoleUtil.printArray(flights);
        System.out.println();
        //动态规划状态转移方程，开始填表
        //直达的已经初始化了（即k = 0的情况），现在从k = 1 的开始，即只有一个中转站开始
        for (int k = 1; k <= K; k++) {
            for (int[] flight : flights) {
                //结合题目理解
                // 官方注释：每一次松弛操作的结果是互相独立的，因此只有在上一轮（第 i - 1 轮）得到单源最短路径的顶点，才需要执行松弛操作
                // src到flight[0]上一轮是能到达，才进行
                PrintConsoleUtil.printParams("from", flight[0], "to", flight[1], "cost", flight[2], "max_k", k);
                if (dp[flight[0]][k - 1] != Integer.MAX_VALUE) {
                    // 题目要求是最多不超过k次中转，那用[到上一个站点的费用 + 上一个站点到目的站点的费用] 和 [src就近已经能直达目的站点(通过其他路径)的费用]作比较
                    dp[flight[1]][k] = Math.min(dp[flight[1]][k], dp[flight[0]][k - 1] + flight[2]);
                }
                PrintConsoleUtil.printArray(dp);
            }
        }
        return dp[dst][K] == Integer.MAX_VALUE ? -1 : dp[dst][K];
    }
}
