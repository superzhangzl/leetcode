package zzl.leetcode;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/3sum/}
 */
public class ThreeNumberSum {
    public static void main(String[] args) {
        Assert.assertEquals(threeSum(new int[]{3}), new ArrayList<>());
        Assert.assertEquals(threeSum(new int[]{0, 0, 0}), Arrays.asList(Arrays.asList(0, 0, 0)));
        Assert.assertEquals(threeSum(new int[]{-1, 0, 1}), Arrays.asList(Arrays.asList(-1, 0, 1)));
        Assert.assertEquals(threeSum(new int[]{-1, 0, 1, 2, -1, -4}), Arrays.asList(Arrays.asList(-1, -1, 2), Arrays.asList(-1, 0, 1)));
    }

    /**
     * 特判，对于数组长度 nn，如果数组为 nullnull 或者数组长度小于 33，返回 [][]。
     * 对数组进行排序。
     * 遍历排序后数组：
     * 若 nums[i]>0nums[i]>0：因为已经排序好，所以后面不可能有三个数加和等于 00，直接返回结果。
     * 对于重复元素：跳过，避免出现重复解
     * 令左指针 L=i+1L=i+1，右指针 R=n-1R=n−1，当 L<RL<R 时，执行循环：
     * 当 nums[i]+nums[L]+nums[R]==0nums[i]+nums[L]+nums[R]==0，执行循环，判断左界和右界是否和下一位置重复，去除重复解。并同时将 L,RL,R 移到下一位置，寻找新的解
     * 若和大于 00，说明 nums[R]nums[R] 太大，RR 左移
     * 若和小于 00，说明 nums[L]nums[L] 太小，LL 右移
     * <p>
     * 链接：https://leetcode-cn.com/problems/3sum/solution/pai-xu-shuang-zhi-zhen-zhu-xing-jie-shi-python3-by/
     * <p>
     * todo 还有优化空间
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        // 初始条件不满足直接返回
        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }
        // 等于3时直接判断即可
        if (nums.length == 3) {
            return nums[0] + nums[1] + nums[2] == 0 ? Arrays.asList(Arrays.asList(nums[0], nums[1], nums[2])) : new ArrayList<>();
        }
        Arrays.sort(nums);
        // 排序后，若最小值大于0或者最大值小于0直接返回不执行判断，不要加等于0，万一全为0呢
        if (nums[0] > 0 || nums[nums.length - 1] < 0) {
            return new ArrayList<>();
        }
        int sum;
        int leftIndex;
        int rightIndex;
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            leftIndex = i + 1;
            rightIndex = nums.length - 1;
            System.out.println(i);
            while (leftIndex < rightIndex) {
                if (nums[i] > 0) {
                    return result;
                }
                if (i > 0 && nums[i] == nums[i - 1]) {
                    break;
                }
                sum = nums[leftIndex] + nums[rightIndex] + nums[i];
                if (sum > 0) {
                    rightIndex--;
                }
                if (sum < 0) {
                    leftIndex++;
                }
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[leftIndex], nums[rightIndex]));
                    while (leftIndex < rightIndex && nums[leftIndex] == nums[leftIndex + 1]) {
                        leftIndex++;
                    }
                    while (leftIndex < rightIndex && nums[rightIndex] == nums[rightIndex - 1]) {
                        rightIndex--;
                    }
                    leftIndex++;
                    rightIndex--;
                }
            }
        }
        return result;
    }
}
