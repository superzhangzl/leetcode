package zzl.leetcode;

import org.junit.Assert;
import zzl.base.annotation.Level;

import static zzl.base.enums.Difficulty.*;

/**
 * 整数拆分
 *
 * @author zzl
 * @date 2021-06-21
 * @link {https://leetcode-cn.com/problems/integer-break/}
 */
@Level(MEDIUM)
public class IntegerBreak {
    public static void main(String[] args) {
        int res;
        //
        res = new IntegerBreak().integerBreak(2);
        Assert.assertEquals(res, 1);
        //
        res = new IntegerBreak().integerBreak(10);
        Assert.assertEquals(res, 36);
    }

    /**
     * 动态规划
     *
     * @param n
     * @return
     * @link {https://leetcode-cn.com/problems/integer-break/solution/zheng-shu-chai-fen-by-leetcode-solution/}
     */
    public int integerBreak(int n) {
        /*
        dp[i] 表示将正整数 i 拆分成至少两个正整数的和之后，这些正整数的最大乘积。
        0和1都不能拆分，因此 dp[0]=dp[1]=0
        当i>2时
        将 i 拆分成 j 和 i-j 的和，且 i-j 不再拆分成多个正整数，此时的乘积是 j×(i−j)；
        将 i 拆分成 j 和 i-j 的和，且 i-j 继续拆分成多个正整数，此时的乘积是 j×dp[i−j]。
         */
        int[] dp = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            int cur = 0;
            for (int j = 1; j < i; j++) {
                int anotherPart = i - j;
                cur = Math.max(cur, Math.max(j * anotherPart, j * dp[anotherPart]));
            }
            dp[i] = cur;
        }
        return dp[n];
    }
}
