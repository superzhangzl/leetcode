package zzl.leetcode;

import org.junit.Assert;
import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

import java.util.Arrays;

/**
 * 三个数的最大乘积
 * 给定一个整型数组，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
 * 注：
 * 给定的整型数组长度范围是[3,10^4]，数组中所有的元素范围是[-1000, 1000]。
 * 输入的数组中任意三个数的乘积不会超出32位有符号整数的范围。
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/maximum-product-of-three-numbers/}
 */
public class MaximumProductOfThreeNumbers {
    public static void main(String[] args) {
        int[] input = GenerateUtil.generateIntArray("1,2,3", ",");
        int res = new MaximumProductOfThreeNumbers().maximumProduct(input);
        Assert.assertEquals(res, 6);
        input = GenerateUtil.generateIntArray("-1,-2,-3,-4", ",");
        res = new MaximumProductOfThreeNumbers().maximumProduct(input);
        Assert.assertEquals(res, -6);
        input = GenerateUtil.generateIntArray("-1,-2,-3,4", ",");
        res = new MaximumProductOfThreeNumbers().maximumProduct(input);
        Assert.assertEquals(res, 24);
        input = GenerateUtil.generateIntArray("1,2,3,4", ",");
        res = new MaximumProductOfThreeNumbers().maximumProduct(input);
        Assert.assertEquals(res, 24);
        input = GenerateUtil.generateIntArray("-1,-2,3,4", ",");
        res = new MaximumProductOfThreeNumbers().maximumProduct(input);
        Assert.assertEquals(res, 8);
        input = GenerateUtil.generateIntArray("-1,2,3,4", ",");
        res = new MaximumProductOfThreeNumbers().maximumProduct(input);
        Assert.assertEquals(res, 24);
        input = GenerateUtil.generateIntArray("-1000,2,3,1000", ",");
        res = new MaximumProductOfThreeNumbers().maximumProduct(input);
        Assert.assertEquals(res, 6000);
    }

    /**
     * 要考虑负数的情况，负负得正
     *
     * @param nums
     * @return
     */
    public int maximumProduct(int[] nums) {
        if (nums.length == 3) {
            return nums[0] * nums[1] * nums[2];
        }
        Arrays.sort(nums);
        PrintConsoleUtil.printArray(nums);
        // 直接比较大小即可，不用计算分割点
        return Math.max(nums[0] * nums[1] * nums[nums.length - 1], nums[nums.length - 1] * nums[nums.length - 2] * nums[nums.length - 3]);
//        int spiltPos = -1;
//        // 计算正负数的分割点
//        for (int i = 1; i < nums.length; i++) {
//            if (nums[i - 1] < 0 && nums[i] >= 0) {
//                spiltPos = i;
//            } else if (nums[i - 1] > 0) {
//                break;
//            }
//        }
//        System.out.println("splitPos: " + spiltPos);
//        // 当存在正负数的分割点的时候，且负数的个数大于2，可能存在负负得正的情况
//        if (spiltPos != 0 && spiltPos > 1) {
//            // 需要比较一下，最左端两个负数乘积再乘以正数最大值  和  最后边三个正数最大值  之间比较
//            int left = nums[0] * nums[1] * nums[nums.length - 1];
//            int right = nums[nums.length - 1] * nums[nums.length - 2] * nums[nums.length - 3];
//            return left >= right ? left : right;
//        } else {
//            return nums[nums.length - 1] * nums[nums.length - 2] * nums[nums.length - 3];
//        }
    }
}
