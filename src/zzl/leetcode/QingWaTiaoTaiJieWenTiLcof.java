package zzl.leetcode;

import org.junit.Assert;

/**
 * 剑指 Offer 10- II. 青蛙跳台阶问题
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/qing-wa-tiao-tai-jie-wen-ti-lcof/}
 */
public class QingWaTiaoTaiJieWenTiLcof {
    public static void main(String[] args) {
        int n = 2;
        int numWays;

        numWays = new QingWaTiaoTaiJieWenTiLcof().numWays(n);
        Assert.assertEquals(numWays, 2);
        //===
        numWays = new QingWaTiaoTaiJieWenTiLcof().numWays(7);
        Assert.assertEquals(numWays, 21);

    }


    private int numWays(int n) {
        // 优化dp
        int res = 1;
        int next = 1;
        for (int i = 0; i < n; i++) {
            int tmp = next;
            next = (res + next) % 1000000007;
            res = tmp;
            System.out.println((i + 1) + " :> " + res);
        }
        System.out.println();
        return res;
    }

    /**
     * 严格递归递归
     *
     * @param n
     * @return
     */
    public int numWays_digui(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return numWays_digui(n - 1) + numWays_digui(n - 2);
    }
}
