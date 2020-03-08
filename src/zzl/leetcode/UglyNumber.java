package zzl.leetcode;

import org.junit.Assert;

/**
 * 丑数
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/ugly-number/}
 */
public class UglyNumber {
    public static void main(String[] args) {
        Assert.assertEquals(new UglyNumber().isUgly(1), true);
        Assert.assertEquals(new UglyNumber().isUgly(6), true);
        Assert.assertEquals(new UglyNumber().isUgly(8), true);
        Assert.assertEquals(new UglyNumber().isUgly(14), false);
    }

    public boolean isUgly(int num) {
        if (num < 1) {
            return false;
        }
        int temp = 0;
        while (num != 1 && temp != num) {
            temp = num;
            System.out.println((num / 5.0) - (num / 5) == 0);
            // double -> int 会出现精度损失，刚好用来判断是否整除
            if ((num / 5.0) - (num / 5) == 0) {
                num /= 5;
//                continue;
            }
            System.out.println((num / 3.0) - (num / 3) == 0);
            if ((num / 3.0) - (num / 3) == 0) {
                num /= 3;
//                continue;
            }
            System.out.println((num / 2.0) - (num / 2) == 0);
            if ((num / 2.0) - (num / 2) == 0) {
                num /= 2;
//                continue;
            }
            System.out.println(num);
        }
        return num == 1;
    }
}
