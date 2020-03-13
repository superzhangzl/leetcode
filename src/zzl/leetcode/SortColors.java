package zzl.leetcode;

import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

/**
 * 颜色分类
 * 不能使用代码库中的排序函数来解决这道题。
 * 而且通过排序算法，时间复杂度也是O(nlogn)
 * 该题目标是在O(n)的时间范围内使用O(1)的空间复杂度来解决
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/sort-colors/}
 */
public class SortColors {
    public static void main(String[] args) {
        int[] input = GenerateUtil.generateIntArray("2,0,2,1,1,0", ",");
        new SortColors().sortColors(input);
        PrintConsoleUtil.printArray(input);
        input = GenerateUtil.generateIntArray("2,0,1", ",");
        new SortColors().sortColors(input);
        PrintConsoleUtil.printArray(input);
    }

    /**
     * 我们用三个指针（p0, p2 和curr）来分别追踪0的最右边界，2的最左边界和当前考虑的元素
     * 本解法的思路是沿着数组移动 curr 指针，若nums[curr] = 0，则将其与 nums[p0]互换；若 nums[curr] = 2 ，则与 nums[p2]互换。
     *
     * @param nums
     * @link {https://leetcode-cn.com/problems/sort-colors/solution/yan-se-fen-lei-by-leetcode/}
     */
    public void sortColors(int[] nums) {
        int numZeroIndex = 0;
        int numTwoIndex = nums.length - 1;
        int currentIndex = 0;
        while (currentIndex <= numTwoIndex) {
            /*
            若 nums[curr] = 0 ：交换第 curr个 和 第p0个 元素，并将指针都向右移。
            若 nums[curr] = 2 ：交换第 curr个和第 p2个元素，并将 p2指针左移 。
            若 nums[curr] = 1 ：将指针curr右移。
             */
            if (nums[currentIndex] == 0) {
                swap(nums, currentIndex, numZeroIndex);
                numZeroIndex++;
                currentIndex++;
            } else if (nums[currentIndex] == 2) {
                swap(nums, currentIndex, numTwoIndex);
                numTwoIndex--;
            } else {
                currentIndex++;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
