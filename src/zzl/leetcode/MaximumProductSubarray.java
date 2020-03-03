package zzl.leetcode;

import org.junit.Assert;

/**
 * 乘积最大子序列
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/maximum-product-subarray/}
 */
public class MaximumProductSubarray {
    public static void main(String[] args) {
        int[] nums = new int[]{2, 3, -2, 4};
        Assert.assertEquals(new MaximumProductSubarray().maxProduct(nums), 6);

    }

    /**
     * 遍历数组时计算当前最大值，不断更新
     * 令imax为当前最大值，则当前最大值为 imax = max(imax * nums[i], nums[i])
     * !**由于存在负数，那么会导致最大的变最小的，最小的变最大的。因此还需要维护当前最小值imin，imin = min(imin * nums[i], nums[i])**
     * 当负数出现时则imax与imin进行交换再进行下一步计算
     * 时间复杂度：O(n)
     *
     * @param nums
     * @return
     * @link {https://leetcode-cn.com/problems/maximum-product-subarray/solution/hua-jie-suan-fa-152-cheng-ji-zui-da-zi-xu-lie-by-g/}
     */
    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE, imax = 1, imin = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                int tmp = imax;
                imax = imin;
                imin = tmp;
            }
            imax = Math.max(imax * nums[i], nums[i]);
            imin = Math.min(imin * nums[i], nums[i]);
            System.out.println("imax:" + imax + ", imin" + imin);
            max = Math.max(max, imax);
        }
        return max;
    }
}
