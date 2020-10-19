package zzl.leetcode;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * 和为K的子数组
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/subarray-sum-equals-k/}
 */
public class SubarraySumEqualsK {
    public static void main(String[] args) {
        int[] nums = {1, 1, 1};
        int k = 2;
        Assert.assertEquals(new SubarraySumEqualsK().subarraySum(nums, 2), 2);
    }

    /**
     * 详见解题解析，使用map来保存前缀和的个数，相当于把每个阶段的数组和进行了保存，使用[i]-[i-1]==k来判断是否存在匹配的子数组
     *
     * @param nums
     * @param k
     * @return
     * @link {https://leetcode-cn.com/problems/subarray-sum-equals-k/solution/he-wei-kde-zi-shu-zu-by-leetcode-solution/}
     */
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        int sum = 0;
        Map<Integer, Integer> preMap = new HashMap<>();
        // 用于匹配K本身
        preMap.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (preMap.containsKey(sum - k)) {
                count += preMap.get(sum - k);
            }
            preMap.put(sum, preMap.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}
