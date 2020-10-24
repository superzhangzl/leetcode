package zzl.leetcode;

import org.junit.Assert;
import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

/**
 * 寻找重复数
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/find-the-duplicate-number/}
 */
public class FindTheDuplicateNumber {
    public static void main(String[] args) {
        int[] nums = GenerateUtil.generateIntArray("1,3,4,2,2", ",");
        Assert.assertEquals(new FindTheDuplicateNumber().findDuplicate(nums), 2);
        nums = GenerateUtil.generateIntArray("3,1,3,4,2", ",");
        Assert.assertEquals(new FindTheDuplicateNumber().findDuplicate(nums), 3);
        nums = GenerateUtil.generateIntArray("2,5,9,6,9,3,8,9,7,1", ",");
        Assert.assertEquals(new FindTheDuplicateNumber().findDuplicate(nums), 9);
        nums = GenerateUtil.generateIntArray("2,2,2,2,2,2,2", ",");
        Assert.assertEquals(new FindTheDuplicateNumber().findDuplicate(nums), 2);
    }

    /**
     * 要求：
     * 不能更改原数组（假设数组是只读的）。
     * - 不能排序
     * 只能使用额外的 O(1) 的空间。
     * - 不能使用新的空间做排序
     * - 不能使用哈希表
     * 时间复杂度小于 O(n2) 。
     * - 不能双重遍历
     * 数组中只有一个重复的数字，但它可能不止重复出现一次。
     * - ？
     *
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        // 校验数组满足条件
        int slow = 0;
        int fast = 0;
        slow = nums[slow];
        fast = nums[nums[fast]];
        while (slow != fast) {
            // 慢指针每次走一步
            slow = nums[slow];
            // 快指针每次走两步
            fast = nums[nums[fast]];
        }
        // 到这一步只能证明有环存在，但所在值不一定是重复数字
        int pre1 = 0;
        int pre2 = slow;
        // 不同位置的重复数字会指向相同的位置
        // 重复数字必然作为遍历时环的一个入口
        while (pre1 != pre2) {
//            PrintConsoleUtil.printArrayWithIndex(nums, pre1);
//            PrintConsoleUtil.printArrayWithIndex(nums, pre2);
            pre1 = nums[pre1];
            pre2 = nums[pre2];
//            PrintConsoleUtil.printArrayWithIndex(nums, pre1);
//            PrintConsoleUtil.printArrayWithIndex(nums, pre2);
//            System.out.println();
        }
        return pre1;
    }
}
