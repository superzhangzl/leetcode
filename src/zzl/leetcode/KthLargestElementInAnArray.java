package zzl.leetcode;

import org.junit.Assert;
import zzl.util.PrintConsoleUtil;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 数组中的第K个最大元素
 * 注：你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/kth-largest-element-in-an-array/}
 */
public class KthLargestElementInAnArray {
    public static void main(String[] args) {

        Assert.assertEquals(new KthLargestElementInAnArray().findKthLargestBetter(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4), 4);
        Assert.assertEquals(new KthLargestElementInAnArray().findKthLargestBetter(new int[]{3, 2, 1, 5, 6, 4}, 2), 5);
    }

    /**
     * 使用最大堆保存前k大个数，最后抛出最后一个
     * 时间复杂度 : {O}(N \log k)O(Nlogk)。
     * 空间复杂度 : {O}(k)O(k)，用于存储堆元素
     *
     * @param nums
     * @param k
     * @return
     * @link {https://leetcode-cn.com/problems/kth-largest-element-in-an-array/solution/shu-zu-zhong-de-di-kge-zui-da-yuan-su-by-leetcode/}
     */
    public int findKthLargestBetter(int[] nums, int k) {
        // init heap 'the smallest element first'
        PriorityQueue<Integer> heap =
                new PriorityQueue<>((n1, n2) -> n1 - n2);

        // keep k largest elements in the heap
        for (int n : nums) {
            heap.add(n);
            if (heap.size() > k) {
                heap.poll();
            }
        }
        // output
        return heap.poll();
    }

    public int findKthLargest(int[] nums, int k) {
        Arrays.parallelSort(nums);
        PrintConsoleUtil.printArray(nums);
        return nums[nums.length - k];
    }
}
