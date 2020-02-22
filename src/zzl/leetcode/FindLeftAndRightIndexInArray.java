package zzl.leetcode;

import org.junit.Assert;

import java.util.Arrays;

/**
 * @author zzl
 * @link {https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/}
 */
public class FindLeftAndRightIndexInArray {
    public static void main(String[] args) {
        int[] searchRange = searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8);
        Assert.assertEquals(searchRange[0] + "," + searchRange[1], "3,4");
        searchRange = searchRange(new int[]{5, 7, 7, 8, 8, 10}, 6);
        Assert.assertEquals(searchRange[0] + "," + searchRange[1], "-1,-1");
        searchRange = searchRange(new int[]{1}, 1);
        Assert.assertEquals(searchRange[0] + "," + searchRange[1], "0,0");
        searchRange = searchRange(new int[]{2, 2}, 2);
        Assert.assertEquals(searchRange[0] + "," + searchRange[1], "0,1");
    }

    public static int[] searchRange(int[] nums, int target) {
        int oneOfIndex = Arrays.binarySearch(nums, target);
        System.out.println("one: " + oneOfIndex);
        if (oneOfIndex < 0) {
            return new int[]{-1, -1};
        }
        int leftIndex = oneOfIndex;
        int rightIndex = oneOfIndex;

        for (int i = oneOfIndex; i >= 0; i--) {
            if (nums[i] == target) {
                leftIndex = i;
            }

        }
        for (int j = oneOfIndex; j < nums.length; j++) {
            if (nums[j] == target) {
                rightIndex = j;
            }

        }
        System.out.println(leftIndex + ", " + rightIndex);
        return new int[]{leftIndex, rightIndex};
    }

}
