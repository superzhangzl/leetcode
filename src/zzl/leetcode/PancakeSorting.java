package zzl.leetcode;

import zzl.base.annotation.Level;
import zzl.util.GenerateUtil;

import java.util.LinkedList;
import java.util.List;

import static zzl.base.enums.Difficulty.MEDIUM;

/**
 * 煎饼排序
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/pancake-sorting/}
 */
@Level(MEDIUM)
public class PancakeSorting {
    public static void main(String[] args) {
        int[] array;
        List<Integer> pancakeSort;
        //
        array = GenerateUtil.generateIntArray("3,2,4,1");
        pancakeSort = new PancakeSorting().pancakeSort(array);
        System.out.println(pancakeSort);
    }

    /**
     * 递归
     * 注：其中一种答案，但不是最短的答案
     *
     * @param arr
     * @return
     */
    public List<Integer> pancakeSort(int[] arr) {
        sort(arr, arr.length);
        return list;
    }

    LinkedList<Integer> list = new LinkedList<>();

    private void sort(int[] arr, int n) {
        if (n == 1) {
            // 只剩一个煎饼的时候已经有序了
            return;
        }
        // 寻找最大煎饼的位置
        int maxPancake = 0;
        int maxPancakeIndex = 0;
        // n以后的已经有序
        for (int i = 0; i < n; i++) {
            if (arr[i] > maxPancake) {
                maxPancake = arr[i];
                maxPancakeIndex = i;
            }
        }
        // 第一次翻转：将[0,n]内最大的烧饼放到上面
        reverse(arr, 0, maxPancakeIndex);
        // 记录当前位置，因为要求的下标从1，开始
        list.add(maxPancakeIndex + 1);
        // 连通底部进行一次翻转
        reverse(arr, 0, n - 1);
        list.add(n);
        // 大的已经在底部了，范围缩小再来一次
        sort(arr, n - 1);
    }

    private void reverse(int[] arr, int i, int j) {
        while (i < j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
            i++;
            j--;
        }
    }
}
