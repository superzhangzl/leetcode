package zzl.leetcode;

import org.junit.Assert;
import zzl.base.annotation.Level;
import zzl.util.GenerateUtil;

import java.util.Arrays;

import static zzl.base.enums.Difficulty.*;

/**
 * 有效三角形的个数
 *
 * @author zzl
 * @date 2021-08-04
 * @link {https://leetcode-cn.com/problems/valid-triangle-number/}
 */
@Level(MEDIUM)
public class ValidTriangleNumber {
    public static void main(String[] args) {
        int[] nums;
        int triangleNumber;
        //
        nums = GenerateUtil.generateIntArray("2,2,3,4");
        triangleNumber = new ValidTriangleNumber().triangleNumber(nums);
        Assert.assertEquals(triangleNumber, 3);

    }

    /**
     * 三角形成立条件：两边之和大于第三边
     * a + b > c
     * a + c > b
     * b + c > a
     *
     * @param nums
     * @return
     */
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        // nums[i]最大数
        for (int i = 0; i < nums.length; i++) {
            // nums[j]次大数
            for (int j = i - 1; j >= 0; j--) {
                // nums[k]最小数
                int k = 0;
                // 由于有序，找到最近一个最满足三角形成立条件的位置即可，[k,j)之间位置的元素是都满足条件的
                while (k < j && nums[k] + nums[j] <= nums[i]) {
                    k++;
                }
                // 加上j-k的长度，即这些数是都能满足条件的，否则就+=0不变
                ans += (j - k);
            }
        }
        return ans;
    }
}
