package zzl.leetcode;

import org.junit.Assert;
import zzl.base.annotation.Level;

import static zzl.base.enums.Difficulty.*;

/**
 * 汉明距离
 *
 * @author zzl
 * @date 2021-04-20
 * @link {https://leetcode-cn.com/problems/hamming-distance/}
 */
@Level(SIMPLE)
public class HammingDistance {
    public static void main(String[] args) {
        int hammingDistance;
        hammingDistance = new HammingDistance().hammingDistance(1, 4);
        Assert.assertEquals(hammingDistance, 2);

    }

    /**
     * 位运算
     *
     * @param x
     * @param y
     * @return
     */
    public int hammingDistance(int x, int y) {
        /*
        解法二：
        int xor = x ^ y;
        int cnt = 0;
        while (xor != 0) {
            if ((xor % 2) == 1) cnt++;
            xor = xor >> 1;
        }
        return cnt;
         */
        return Integer.bitCount(x ^ y);
    }
}
