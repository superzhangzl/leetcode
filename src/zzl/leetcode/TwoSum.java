package zzl.leetcode;

import zzl.util.GenerateUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/two-sum/}
 */
public class TwoSum {
    public static void main(String[] args) {
        int[] nums = GenerateUtil.generateIntArray("2, 7, 11, 15", ",");
        int[] twoSum = new TwoSum().twoSum(nums, 9);
        System.out.println(twoSum[0] + ", " + twoSum[1]);
        nums = GenerateUtil.generateIntArray("3,2,4", ",");
        twoSum = new TwoSum().twoSum(nums, 6);
        System.out.println(twoSum[0] + ", " + twoSum[1]);
        nums = GenerateUtil.generateIntArray("0,4,3,0", ",");
        twoSum = new TwoSum().twoSum(nums, 0);
        System.out.println(twoSum[0] + ", " + twoSum[1]);
    }

    /**
     * leetcode上看到的大佬的nb解法
     * 使用二进制上的位来保存对应的结果，可参考《编程珠玑》上的位排序
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSumBest(int[] nums, int target) {
        int Volume = 2048;          //100000000000
        int bitMode = Volume - 1;     //011111111111
        int[] t = new int[Volume];  //store index+1, in order to skip default 0
        for (int i = 0; i < nums.length; i++) {
            int c = (target - nums[i]) & bitMode;
            if (t[c] != 0) return new int[]{t[c] - 1, i};
            t[nums[i] & bitMode] = i + 1;
        }
        return null;

    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> differenceWithIndexMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int difference = target - nums[i];
            // 使用当前数来看map中有没有对应的值
            if (differenceWithIndexMap.containsKey(nums[i])) {
                return new int[]{differenceWithIndexMap.get(nums[i]), i};
            }
            // 把当前数与target的的差值(就是另一个与之对应对应的值)放进去
            differenceWithIndexMap.put(difference, i);
            System.out.println(differenceWithIndexMap);
        }
        return new int[]{};
    }

    /**
     * 暴力解法
     * 时间复杂度O(n^2)
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSumStupid(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }
}
