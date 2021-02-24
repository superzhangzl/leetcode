package zzl.leetcode;

import org.junit.Assert;
import zzl.util.GenerateUtil;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 前 K 个高频元素
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/top-k-frequent-elements/}
 */
public class TopKFrequentElements {
    public static void main(String[] args) {
        int[] nums = GenerateUtil.generateIntArray("1,1,1,2,2,3");
        int[] res = new TopKFrequentElements().topKFrequent(nums, 2);
        Assert.assertArrayEquals(res, GenerateUtil.generateIntArray("1,2"));
        nums = GenerateUtil.generateIntArray("1,1,1,1");
        res = new TopKFrequentElements().topKFrequent(nums, 1);
        Assert.assertArrayEquals(res, GenerateUtil.generateIntArray("1"));
        nums = GenerateUtil.generateIntArray("1,2,3,4,5");
        res = new TopKFrequentElements().topKFrequent(nums, 5);
        Assert.assertArrayEquals(res, GenerateUtil.generateIntArray("1,2,3,4,5"));

    }

    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> kCount = new HashMap<>();
        for (int num : nums) {
            kCount.put(num, kCount.getOrDefault(num, 0) + 1);
        }

        System.out.println(kCount);
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(kCount::get));
        for (Integer key : kCount.keySet()) {
            if (queue.size() < k) {
                queue.add(key);
            } else if (kCount.get(key) > kCount.get(queue.peek())) {
                queue.remove();
                queue.add(key);
            }
        }
        System.out.println(queue);
        int[] res = new int[k];
        for (int i = 0; i < res.length; i++) {
            res[i] = queue.poll();
        }
        return res;
    }

    /**
     * 先hash统计个数，再排序
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] topKFrequent2(int[] nums, int k) {
        HashMap<Integer, Integer> kCount = new HashMap<>();
        for (int num : nums) {
            kCount.put(num, kCount.getOrDefault(num, 0) + 1);
        }

        System.out.println(kCount);
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(kCount::get));
        for (Integer key : kCount.keySet()) {
            if (queue.size() < k) {
                queue.add(key);
            } else if (kCount.get(key) > kCount.get(queue.peek())) {
                queue.remove();
                queue.add(key);
            }
        }
        System.out.println(queue);

        List<Integer> list = kCount.entrySet()
                .stream()
                .map(Map.Entry::getKey)
                .sorted(Comparator.comparingInt(kCount::get))
                .collect(Collectors.toList());
        System.out.println(list);
        int[] res = new int[k];
        for (int i = list.size() - 1, j = 0; i >= 0 && list.size() - 1 - i < k; i--, j++) {
            res[j] = list.get(i);
        }
        return res;
    }
}
