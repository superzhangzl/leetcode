package zzl.leetcode;

import org.junit.Assert;
import org.junit.Test;

/**
 * 爱生气的书店老板
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/grumpy-bookstore-owner/}
 */
public class GrumpyBookstoreOwner {
    public static void main(String[] args) {
        int[] customers = {1, 0, 1, 2, 1, 1, 7, 5};
        int[] grumpy = {0, 1, 0, 1, 0, 1, 0, 1};
        int X = 3;
        int maxSatisfied = new GrumpyBookstoreOwner().maxSatisfied(customers, grumpy, X);
        Assert.assertEquals(maxSatisfied, 16);
    }

    /**
     * @param customers
     * @param grumpy
     * @param X
     * @return
     * @link {https://leetcode-cn.com/problems/grumpy-bookstore-owner/solution/ai-sheng-qi-de-shu-dian-lao-ban-by-leetc-dloq/}
     */
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int total = 0;
        // 假设老板没有使用秘籍时，能获得的最大的满意度
        for (int i = 0; i < customers.length; i++) {
            // 老板没生气
            if (grumpy[i] == 0) {
                total += customers[i];
            }
        }
        // 计算初始滑动窗口的值，即这个窗口内的值计算的是不满意的人数，然后再滑动窗口，计算不满意的最大的人数，最后和原本就满意的总人数加起来
        int totalInWindow = 0;
        for (int i = 0; i < X; i++) {
            totalInWindow += customers[i] * grumpy[i];
        }

        // 这个时候从X下标开始，因为0~X已经在上一轮计算过了
        // maxTotalInWindow为滑动窗口内不满意的最大人数
        int maxTotalInWindow = totalInWindow;
        for (int i = X; i < customers.length; i++) {
            // 加上当前位置的值，就需要减去上一轮窗口的最靠前的值
            totalInWindow = totalInWindow + customers[i] * grumpy[i] - customers[i - X] * grumpy[i - X];
            maxTotalInWindow = Math.max(totalInWindow, maxTotalInWindow);
        }
        // 本来就满意的人数，加上使用秘籍以后的人数
        return maxTotalInWindow + total;
    }
}
