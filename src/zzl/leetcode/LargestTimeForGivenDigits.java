package zzl.leetcode;

import org.junit.Assert;
import zzl.util.GenerateUtil;

/**
 * 给定数字能组成的最大时间
 * 给定一个由 4 位数字组成的数组，返回可以设置的符合 24 小时制的最大时间。
 * <p>
 * A.length == 4
 * 0 <= A[i] <= 9
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/largest-time-for-given-digits/}
 */
public class LargestTimeForGivenDigits {
    public static void main(String[] args) {
        int[] input = GenerateUtil.generateIntArray("1,2,3,4", ",");
        Assert.assertEquals(new LargestTimeForGivenDigits().largestTimeFromDigits(input), "23:41");
        input = GenerateUtil.generateIntArray("5,5,5,5", ",");
        Assert.assertEquals(new LargestTimeForGivenDigits().largestTimeFromDigits(input), "");
    }

    /**
     * 我佛了，还在想怎么匹配算法，瞅了一眼官方示例直接暴力解法，反正4个数不重复全排列也就24种可能
     *
     * @param A
     * @return
     */
    public String largestTimeFromDigits(int[] A) {
        int ans = -1;
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                if (j != i) {
                    for (int k = 0; k < 4; ++k) {
                        if (k != i && k != j) {
                            // 位置分别定义为 0 1 2 3 这里就是剩下的位置(总和减其他)
                            int l = 6 - i - j - k;
                            // 解析时间
                            int hours = 10 * A[i] + A[j];
                            int mins = 10 * A[k] + A[l];
                            if (hours < 24 && mins < 60)
                                // 转换为分钟的表示形式
                                ans = Math.max(ans, hours * 60 + mins);
                        }
                    }
                }
            }
        }
        return ans >= 0 ? String.format("%02d:%02d", ans / 60, ans % 60) : "";
    }
}
