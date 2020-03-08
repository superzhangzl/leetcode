package zzl.leetcode;

import org.junit.Assert;

/**
 * 第n个丑数
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/ugly-number-ii/}
 */
public class UglyNumberII {

    public static void main(String[] args) {
        Assert.assertEquals(new UglyNumberII().nthUglyNumber(10), 12);
        Assert.assertEquals(new UglyNumberII().nthUglyNumber(1), 1);
    }

    public int nthUglyNumber(int n) {
        int[] nums = new int[n];
        nums[0] = 1;

        int i2 = 0, i3 = 0, i5 = 0;
        int temp = 1;

        for (int i = 1; i < n; i++) {
            // nth = 2^a * 3^b * 5*c
            temp = Math.min(Math.min(nums[i2] * 2, nums[i3] * 3), nums[i5] * 5);
            nums[i] = temp;
            if (temp == nums[i2] * 2) {
                i2++;
            }
            if (temp == nums[i3] * 3) {
                i3++;
            }
            if (temp == nums[i5] * 5) {
                i5++;
            }
        }
        return nums[n - 1];
    }

}
