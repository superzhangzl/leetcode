package zzl.leetcode;

import org.junit.Assert;

/**
 * 完全平方数
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/perfect-squares/}
 */
public class PerfectSquares {
    public static void main(String[] args) {
        int n = 12;
        Assert.assertEquals(new PerfectSquares().numSquares(n), 3);
        n = 13;
        Assert.assertEquals(new PerfectSquares().numSquares(n), 2);
    }

    protected boolean isSquare(int n) {
        int sq = (int) Math.sqrt(n);
        return n == sq * sq;
    }

    /**
     * 数学公式
     * 猜想：任何一个正整数可以分解为4个整数平方之和
     * 当n满足 $$n!=4^k(8m+7)$$时，n可以表示为三个平方之和
     * <p>
     * https://leetcode-cn.com/problems/perfect-squares/solution/wan-quan-ping-fang-shu-by-leetcode/
     *
     * @param n
     * @return
     */
    public int numSquares(int n) {
        // four-square and three-square theorems.
        while (n % 4 == 0)
            n /= 4;
        if (n % 8 == 7)
            return 4;

        if (this.isSquare(n))
            return 1;
        // enumeration to check if the number can be decomposed into sum of two squares.
        for (int i = 1; i * i <= n; ++i) {
            if (this.isSquare(n - i * i))
                return 2;
        }
        // bottom case of three-square theorem.
        return 3;
    }

}
