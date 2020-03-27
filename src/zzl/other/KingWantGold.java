package zzl.other;

import zzl.util.PrintConsoleUtil;

/**
 * 国王挖金子-动态规划
 *
 * @author zzl
 * @link {https://zhuanlan.zhihu.com/p/31628866}
 */
public class KingWantGold {
    public static void main(String[] args) {
        System.out.println(getMostGoldDfs(5, 10, new int[]{400, 500, 200, 300, 350}, new int[]{5, 5, 3, 4, 3}));
        System.out.println(getMostGold(5, 10, new int[]{400, 500, 200, 300, 350}, new int[]{5, 5, 3, 4, 3}));
    }

    /**
     * 动态规划：
     * 转移方程：
     * F(n,w) = 0                                         (n<=1, w<p[0])
     * F(n,w) = G[0]                                      (n==1, w>=p[0])
     * F(n,w) = F(n-1, w)                                 (n>1. w<p[n-1])
     * F(n,w) = Max(F(n-1, w), F(n-1, w-p[n-1]) + G(n-1)) (n>1, w>=p[n-1])
     *
     * @param numOfGold     金矿数量
     * @param totalPeople   总人数
     * @param gold          黄金的价值
     * @param consumePeople 需要参与的人数
     * @return
     */
    private static int getMostGoldDfs(int numOfGold, int totalPeople, int[] gold, int[] consumePeople) {
        if (numOfGold <= 1 && totalPeople < consumePeople[0]) {
            return 0;
        }
        if (numOfGold == 1 && totalPeople >= consumePeople[0]) {
            return gold[0];
        }
        /*
         * F(n,w) = F(n-1, w)                                 (n>1. w<p[n-1])
         * F(n,w) = Max(F(n-1, w), F(n-1, w-p[n-1]) + G(n-1)) (n>1, w>=p[n-1])
         */
        if (totalPeople < consumePeople[numOfGold - 1]) {
            return getMostGoldDfs(numOfGold - 1, totalPeople, gold, consumePeople);
        } else {
            return Integer.max(
                    getMostGoldDfs(numOfGold - 1, totalPeople, gold, consumePeople),
                    getMostGoldDfs(numOfGold - 1, totalPeople - consumePeople[numOfGold - 1], gold, consumePeople) + gold[numOfGold - 1]
            );
        }
    }

    private static int getMostGold(int numOfGold, int totalPeople, int[] gold, int[] consumePeople) {
        if (numOfGold <= 1 && totalPeople < consumePeople[0]) {
            return 0;
        }
        if (numOfGold == 1 && totalPeople >= consumePeople[0]) {
            return gold[0];
        }
        // 一般动态规划的宽度都要+1
        int[][] dp = new int[gold.length][totalPeople + 1];
        for (int i = 0; i < dp[0].length; i++) {
            // i就是当前的人数
            if (i >= consumePeople[0]) {
                dp[0][i] = gold[0];
            } else {
                dp[0][i] = 0;
            }
        }
        PrintConsoleUtil.printArray(dp);
        for (int i = 1; i < numOfGold; i++) {
            // 这里的是等于，要把当前人数算进去，人数不是从0开始计算的
            for (int j = 0; j <= totalPeople; j++) {
                /*
                 * F(n,w) = F(n-1, w)                                 (n>1. w<p[n-1])
                 * F(n,w) = Max(F(n-1, w), F(n-1, w-p[n-1]) + G(n-1)) (n>1, w>=p[n-1])
                 */
                if (j < consumePeople[i]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Integer.max(dp[i - 1][j], dp[i - 1][j - consumePeople[i]] + gold[i]);
                }
            }
            PrintConsoleUtil.printArray(dp);
        }
        return dp[numOfGold - 1][totalPeople];
    }
}
