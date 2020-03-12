package zzl.leetcode;

import zzl.util.PrintConsoleUtil;

import java.util.Arrays;
import java.util.Collections;

/**
 * 下一个排列
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/next-permutation/}
 */
public class NextPermutation {
    public static void main(String[] args) {
        int[] input = {1, 2, 3};
        new NextPermutation().nextPermutation(input);
        PrintConsoleUtil.printArray(input);// 1,3,2
        input = new int[]{3, 2, 1};
        new NextPermutation().nextPermutation(input);
        PrintConsoleUtil.printArray(input);// 1, 2, 3
        input = new int[]{1, 1, 5};
        new NextPermutation().nextPermutation(input);
        PrintConsoleUtil.printArray(input); // 1, 5, 1
        input = new int[]{1, 2, 3, 4, 6, 5};
        new NextPermutation().nextPermutation(input);
        PrintConsoleUtil.printArray(input); // 1, 2, 3, 5, 4, 6
    }

    /**
     * 1. 在尽可能靠右的低位进行交换，需要从后向前查找
     * 2. 将一个 尽可能小的「大数」 与前面的「小数」交换。比如 123465，下一个排列应该把 5 和 4 交换而不是把 6 和 4 交换
     * 3. 将「大数」换到前面后，需要将「大数」后面的所有数重置为升序，升序排列就是最小的排列。
     * eg:
     * 以 123465 为例：首先按照上一步，交换 5 和 4，得到 123564；然后需要将 5 之后的数重置为升序，得到 123546。
     * 显然 123546 比 123564 更小，123546 就是 123465 的下一个排列
     *
     * @param nums
     * @link {https://leetcode-cn.com/problems/next-permutation/solution/xia-yi-ge-pai-lie-by-leetcode/}
     * @link {https://leetcode-cn.com/problems/next-permutation/solution/xia-yi-ge-pai-lie-suan-fa-xiang-jie-si-lu-tui-dao-/}
     */
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        // 从右往左，找到不满足降序的序列
        while (i >= 0 && nums[i + 1] <= nums[i]) {
            i--;
        }
        // 如果这个位置大于0，说明在中间
        if (i >= 0) {
            int j = nums.length - 1;
            // 找尽可能比[i]小的「大数」
            while (j >= 0 && nums[j] <= nums[i]) {
                j--;
            }
            // [i]与[j]交换
            swap(nums, i, j);
        }
        // 反转[i]之后的为升序
        reverse(nums, i + 1);
    }

    private void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
