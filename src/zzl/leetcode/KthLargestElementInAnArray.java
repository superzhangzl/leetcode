package zzl.leetcode;

import org.junit.Assert;
import zzl.util.PrintConsoleUtil;

import java.util.Arrays;

/**
 * 数组中的第K个最大元素
 * 注：你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/kth-largest-element-in-an-array/}
 */
public class KthLargestElementInAnArray {
    public static void main(String[] args) {

        Assert.assertEquals(new KthLargestElementInAnArray().findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4), 4);
        Assert.assertEquals(new KthLargestElementInAnArray().findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2), 5);
    }

    public int findKthLargest(int[] nums, int k) {
        Arrays.parallelSort(nums);
        PrintConsoleUtil.printArray(nums);
        return nums[nums.length - k];
    }
}
