package zzl.leetcode;

import org.junit.Assert;

/**
 * @author zzl
 * @link {https://leetcode-cn.com/problems/search-in-rotated-sorted-array/}
 */
public class SearchInRotatedSortedArray {
    public static void main(String[] args) {
        // 两部分有序的升序数组,O(log_n)
        Assert.assertEquals(search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0), 4);
        Assert.assertEquals(search(new int[]{4, 5, 6, 7, 0, 1, 2}, 3), -1);
        Assert.assertEquals(search(new int[]{3, 5, 1}, 3), 0);
        Assert.assertEquals(search(new int[]{5, 1, 3}, 3), 2);
        Assert.assertEquals(search(new int[]{5, 1, 3}, 5), 0);
        Assert.assertEquals(search(new int[]{5, 1, 3}, 1), 1);
        Assert.assertEquals(search(new int[]{4, 5, 6, 7, 8, 1, 2, 3}, 8), 4);
        Assert.assertEquals(search(new int[]{5, 1, 2, 3, 4}, 1), 1);
    }

    /**
     * nice，耗时最短
     * 需要注意的是区分拐点，也就是旋转的点，根据mid的值和边界值区分到底应该落在哪个范围内来调整大小
     *
     * @param nums
     * @param target
     * @return
     */
    public static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int leftIndex = 0;
        int rightIndex = nums.length - 1;
        while (leftIndex <= rightIndex) {
            int mid = (leftIndex + rightIndex) >>> 1;
            int midVal = nums[mid];
            String msg = String.format("target=%d, left: [%d]=>%d, right: [%d]=>%d, mid: [%d]=>%d", target, leftIndex, nums[leftIndex], rightIndex, nums[rightIndex], mid, midVal);
            System.out.println(msg);
            if (midVal == target) {
                return mid;
            }

            if (target > midVal) {
                if (midVal < nums[rightIndex]) {
                    // 有拐点，需要判断哪个区间内来调整下标的值
                    if (target > nums[rightIndex]) {
                        rightIndex = mid - 1;
                    } else {
                        leftIndex = mid + 1;
                    }
                } else {
                    // 默认二分的情况，相当于在一个递增区间内
                    leftIndex = mid + 1;
                }
            } else {
                if (midVal > nums[rightIndex]) {
                    // 有拐点
                    if (target > nums[rightIndex]) {
                        rightIndex = mid - 1;
                    } else {
                        leftIndex = mid + 1;
                    }
                } else {
                    // 默认二分的情况，相当于在一个递增区间内
                    rightIndex = mid - 1;
                }
            }
        }
        return -1;
    }
}
