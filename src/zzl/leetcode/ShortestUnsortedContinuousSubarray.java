package zzl.leetcode;

import org.junit.Assert;
import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

import java.util.Arrays;

/**
 * @author zzl
 * @link {https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray/}
 */
public class ShortestUnsortedContinuousSubarray {
    public static void main(String[] args) {
        int[] array = GenerateUtil.generateIntArray("2,6,4,8,10,9,15");
        int length = new ShortestUnsortedContinuousSubarray().findUnsortedSubarray(array);
        Assert.assertEquals(length, 5);
        array = GenerateUtil.generateIntArray("1,2,3,4");
        length = new ShortestUnsortedContinuousSubarray().findUnsortedSubarray(array);
        Assert.assertEquals(length, 0);
        array = GenerateUtil.generateIntArray("1");
        length = new ShortestUnsortedContinuousSubarray().findUnsortedSubarray(array);
        Assert.assertEquals(length, 0);
    }


    /**
     * 思路：先排序，从左边找最小的值得位置；再从右边找最大值的位置，这样待排序的范围就能确定下来
     * 时间复杂度：O(nlog{n})
     * 空间复杂度：O(n)
     *
     * @param nums
     * @return
     */
    public int findUnsortedSubarray(int[] nums) {
        int[] copy = Arrays.copyOf(nums, nums.length);
        Arrays.sort(copy);
        PrintConsoleUtil.printArray(copy);
        int leftIndex = 0;
        int rightIndex = nums.length - 1;
        while (leftIndex < nums.length) {
            if (nums[leftIndex] == copy[leftIndex]) {
                leftIndex++;
            } else {
                break;
            }
        }
        System.out.println("leftIndex :> " + leftIndex);
        if (leftIndex == nums.length) {
            return 0;
        }

        while (rightIndex >= 0) {
            if (nums[rightIndex] == copy[rightIndex]) {
                rightIndex--;
            } else {
                break;
            }
        }
        System.out.println("rightIndex :> " + rightIndex);
        return rightIndex - leftIndex + 1;
    }

}
