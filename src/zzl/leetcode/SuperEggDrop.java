package zzl.leetcode;

import org.junit.Assert;

import java.util.HashMap;

/**
 * 鸡蛋掉落
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/super-egg-drop/}
 */
public class SuperEggDrop {
    public static void main(String[] args) {
        int superEggDrop;
        //
        superEggDrop = new SuperEggDrop().superEggDrop(1, 2);
        Assert.assertEquals(superEggDrop, 2);
        //
        superEggDrop = new SuperEggDrop().superEggDrop(2, 6);
        Assert.assertEquals(superEggDrop, 3);
        //
        superEggDrop = new SuperEggDrop().superEggDrop(3, 14);
        Assert.assertEquals(superEggDrop, 4);
        //
        superEggDrop = new SuperEggDrop().superEggDrop(4, 5000);
        Assert.assertEquals(superEggDrop, 19);
    }

    public int superEggDrop(int k, int n) {
        return dp(k, n);
    }


    private HashMap<String, Integer> cache = new HashMap<>();

    /**
     * 该解法会超时
     */
    public int dp(int k, int n) {
        if (k == 1) {
            return n;
        }
        if (n == 0) {
            return 0;
        }
        String key = k + "," + n;
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        int res = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            // 最坏情况下，最少扔的次数
            res = Math.min(
                    res,
                    Math.max(
                            dp(k - 1, i - 1), // 碎了
                            dp(k, n - i)         // 没碎
                    ) + 1); // 在i层时候扔了一次
        }
        cache.put(key, res);
        return res;
    }
}
