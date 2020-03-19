package zzl.leetcode;

import org.junit.Assert;
import zzl.util.GenerateUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 多数元素
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/majority-element/}
 */
public class MajorityElement {
    public static void main(String[] args) {
        int[] input = GenerateUtil.generateIntArray("3,2,3", ",");
        Assert.assertEquals(new MajorityElement().majorityElement(input), 3);
        input = GenerateUtil.generateIntArray("2,2,1,1,1,2,2", ",");
        Assert.assertEquals(new MajorityElement().majorityElement(input), 2);
        input = GenerateUtil.generateIntArray("1", ",");
        Assert.assertEquals(new MajorityElement().majorityElement(input), 1);
    }

    /**
     * 因为多数元素过半，先排序，取中位数
     *
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
    }

    /**
     * 最直观的，使用Hash统计每个数字出现的次数
     *
     * @param nums
     * @return
     */
    public int majorityElementBad(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        int maxCount = Integer.MIN_VALUE;
        int maxCountElement = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                Integer count = map.get(nums[i]) + 1;
                map.put(nums[i], count);
                if (count > maxCount) {
                    maxCount = count;
                    maxCountElement = nums[i];
                }
            } else {
                map.put(nums[i], 1);
                if (1 > maxCount) {
                    maxCount = 1;
                    maxCountElement = nums[i];
                }
            }
        }
        return maxCountElement;
    }
}
