package zzl.leetcode;

import org.junit.Assert;
import zzl.base.annotation.Level;
import zzl.util.GenerateUtil;

import static zzl.base.enums.Difficulty.MEDIUM;

/**
 * 等差数列划分
 *
 * @author zzl
 * @date 2021-08-10
 * @link {https://leetcode-cn.com/problems/arithmetic-slices/}
 */
@Level(MEDIUM)
public class ArithmeticSlices {
    public static void main(String[] args) {
        int[] nums;
        int numberOfArithmeticSlices;
        //
        nums = GenerateUtil.generateIntArray("1,2,3,4");
        numberOfArithmeticSlices = new ArithmeticSlices().numberOfArithmeticSlices(nums);
        Assert.assertEquals(numberOfArithmeticSlices, 3);
        //
        nums = GenerateUtil.generateIntArray("1");
        numberOfArithmeticSlices = new ArithmeticSlices().numberOfArithmeticSlices(nums);
        Assert.assertEquals(numberOfArithmeticSlices, 0);
    }

    /**
     * 双指针
     * 具体的，我们可以枚举 i 作为差值为 d 的子数组的左端点，然后通过「双指针」的方式找到当前等差并最长的子数组的右端点 j，令区间 [i, j] 长度为 len。
     * 先求满足等差数量的最大的子数组的长度，然后再缩小该子数组的长度。
     * eg：某等差数列[1,2,3,4,5]，长度为5，则有如下三种满足条件的情况
     * 1 => [1,2,3,4,5]
     * 2 => [1,2,3,4] [2,3,4,5]
     * 3 => [1,2,3] [2,3,4] [3,4,5]
     * 所以在长度为5时，满足条件子数组的长度为6
     *
     * @param nums
     * @return
     * @link {https://leetcode-cn.com/problems/arithmetic-slices/solution/gong-shui-san-xie-shuang-zhi-zhen-qiu-ji-ef1q/}
     */
    public int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n - 2; ) {
            // 新的等差数列区间的起点
            int j = i;
            int df = nums[i + 1] - nums[i];
            // 计算出等差的长度
            while (j + 1 < n && nums[j + 1] - nums[j] == df) {
                j++;
            }
            int len = j - i + 1;
            // 长度为len的子数组的数量
            int al = 1;
            // 长度为3的子数组的数量
            int an = len - 3 + 1;
            int cnt = (al + an) * an / 2;
            ans += cnt;
            i = j;
        }
        return ans;
    }
}
