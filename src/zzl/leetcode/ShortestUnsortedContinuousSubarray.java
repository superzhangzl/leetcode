package zzl.leetcode;

import org.junit.Assert;
import zzl.base.annotation.Level;
import zzl.base.enums.Difficulty;
import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

import java.util.Arrays;
import java.util.Stack;

import static zzl.base.enums.Difficulty.MEDIUM;

/**
 * 最短无序连续子数组
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/shortest-unsorted-continuous-subarray/}
 */
@Level(MEDIUM)
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
        array = GenerateUtil.generateIntArray("2,1");
        length = new ShortestUnsortedContinuousSubarray().findUnsortedSubarray(array);
        Assert.assertEquals(length, 2);
    }


    /**
     * 单调栈，
     * 1. 升序遍历，先使得栈单调增，找到最小破坏栈递增的位置，
     * 2. 降序遍历，再使得栈单调减，找到最大破坏栈递减的位置。
     * 3. 用判断左右区间的长度
     *
     * @param nums
     * @return
     */
    public int findUnsortedSubarray(int[] nums) {
        // 单调栈，保存下标
        Stack<Integer> stack = new Stack<>();
        int left = 2;
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[i] < nums[stack.peek()]) {
                left = Math.min(left, stack.pop());
            }
            stack.push(i);
        }
        // 不能取min_value，否则return是做计算会出现下标越界的情况
        int right = -1;
        stack = new Stack<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                right = Math.max(right, stack.pop());
            }
            stack.push(i);
        }
        PrintConsoleUtil.printArray(nums);
        PrintConsoleUtil.printParams("left", left, "right", right);
        return Math.max(right - left + 1, 0);
    }


    /**
     * 思路：先排序，从左边找最小的值得位置；再从右边找最大值的位置，这样待排序的范围就能确定下来
     * 时间复杂度：O(nlog{n})
     * 空间复杂度：O(n)
     *
     * @param nums
     * @return
     */
    public int findUnsortedSubarray_sort(int[] nums) {
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
