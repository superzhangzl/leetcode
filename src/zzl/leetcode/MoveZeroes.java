package zzl.leetcode;

import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

/**
 * 移动零
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 必须在原数组上操作，不能拷贝额外的数组。
 * 尽量减少操作次数。
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/move-zeroes/}
 */
public class MoveZeroes {
    public static void main(String[] args) {
        int[] input = GenerateUtil.generateIntArray("0,1,0,3,12", ",");
        PrintConsoleUtil.printArray(input);
        new MoveZeroes().moveZeroes(input);
        PrintConsoleUtil.printArray(input);
        System.out.println("==========================================================================================");
        input = GenerateUtil.generateIntArray("0,0,3,12", ",");
        PrintConsoleUtil.printArray(input);
        new MoveZeroes().moveZeroes(input);
        PrintConsoleUtil.printArray(input);
        System.out.println("==========================================================================================");
        input = GenerateUtil.generateIntArray("3,12,0,0", ",");
        PrintConsoleUtil.printArray(input);
        new MoveZeroes().moveZeroes(input);
        PrintConsoleUtil.printArray(input);
    }

    /**
     * 冒泡方法：
     * 当前位置为0时候，将0以后的整体长度向前平移，再重新将尾部设置为0
     * 缺点，当0本身都集中在尾部时，还是会进行额外判断
     *
     * @param nums
     */
    public void moveZeroes2(int[] nums) {
        int lastIndex = nums.length - 1;
        int zeroCount = 0;
        int i = 0;
        while (i <= lastIndex) {
            System.out.println("num[" + i + "] = " + nums[i]);
            if (nums[i] == 0) {
                System.arraycopy(nums, i + 1, nums, i, nums.length - i - 1 - zeroCount);
                zeroCount++;
                nums[lastIndex--] = 0;
            } else {
                i++;
            }
            PrintConsoleUtil.printArray(nums);
        }
    }

    /**
     * 优化，原方法是将数组整体向前平移，此处使用逆向思维，将不是0的向前使用swap的方式交换
     *
     * @param nums
     * @list https://leetcode-cn.com/problems/move-zeroes/solution/yi-dong-ling-by-leetcode/
     */
    public void moveZeroes(int[] nums) {
        // 优化，现在是将数组整体向前平移，可以使用单个swap的方式交换
        for (int lastNonZeroFoundAt = 0, cur = 0; cur < nums.length; cur++) {
            if (nums[cur] != 0) {
                swap(nums, lastNonZeroFoundAt++, cur);
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
