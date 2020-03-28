package zzl.leetcode;

import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

/**
 * 部分排序
 * 给定一个整数数组，编写一个函数，找出索引m和n，只要将索引区间[m,n]的元素排好序，整个数组就是有序的。注意：n-m尽量最小，
 * 也就是说，找出符合条件的最短序列。函数返回值为[m,n]，若不存在这样的m和n（例如整个数组是有序的），请返回[-1,-1]。
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/sub-sort-lcci/}
 * @tag 16.16
 */
public class SubSortLcci {
    public static void main(String[] args) {
        int[] input = GenerateUtil.generateIntArray("1,2,4,7,10,11,7,12,6,7,16,18,19", ",");
        PrintConsoleUtil.printArray(new SubSortLcci().subSort(input));// 3,9
        input = GenerateUtil.generateIntArray("1,2,4,7,10,11,7,12,20,7,16,18,19", ",");
        PrintConsoleUtil.printArray(new SubSortLcci().subSort(input));// 4 12
        input = GenerateUtil.generateIntArray("1,2,4,7,16,18,19", ",");
        PrintConsoleUtil.printArray(new SubSortLcci().subSort(input));// -1, -1
    }

    /**
     * @param array
     * @return
     * @link {https://leetcode-cn.com/problems/sub-sort-lcci/solution/yi-bian-bian-li-shuang-zhi-zhen-by-chen-wei-zhe/}
     */
    public int[] subSort(int[] array) {
        if (array == null || array.length == 0) {
            return new int[]{-1, -1};
        }
        int right = -1, left = -1;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        int len = array.length;
        PrintConsoleUtil.printArray(array);
        for (int i = 0; i < array.length; i++) {
            // 从左往后，找到不按顺序递增的那个值的下标，就是突然冒个泡变小了
            // 就是本来说好的一个一个变大，这样max也会挨个挨个变大，突然有一个值不变大反而变小了，就把它的下标记到小本本上
            // 这个下标相当于是最右边那个不按规矩来的小值
            // 左下标不按规矩变小的同理
            if (array[i] < max) {
                right = i;
            } else {
                max = Math.max(max, array[i]);
            }
            // 从右往左，找到不按顺序递减的那个值的下标，就是突然冒个泡变大了
            if (array[len - 1 - i] > min) {
                left = len - 1 - i;
            } else {
                min = Math.min(min, array[len - 1 - i]);
            }
            System.out.println(String.format("arr[%2d]=%2d, right=%2d, max=%2d || arr[-i]=%2d, left=%2d, min=%2d", i, array[i], right, max, array[len - 1 - i], left, min));
        }
        return new int[]{left, right};
    }
}
