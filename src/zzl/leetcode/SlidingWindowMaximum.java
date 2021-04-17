package zzl.leetcode;

import org.junit.Assert;
import zzl.base.annotation.Level;
import zzl.util.GenerateUtil;

import java.util.ArrayList;
import java.util.LinkedList;

import static zzl.base.enums.Difficulty.HARD;

/**
 * 滑动窗口最大值
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/sliding-window-maximum/}
 */
@Level(HARD)
public class SlidingWindowMaximum {
    public static void main(String[] args) {
        int[] nums;
        int[] maxSlidingWindow;
        //
        nums = GenerateUtil.generateIntArray("1,3,-1,-3,5,3,6,7");
        maxSlidingWindow = new SlidingWindowMaximum().maxSlidingWindow(nums, 3);
        Assert.assertArrayEquals(maxSlidingWindow, GenerateUtil.generateIntArray("3,3,5,5,6,7"));

    }

    /**
     * 单调队列
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        MonotonicQueue window = new MonotonicQueue();
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i < k - 1) {
                window.push(nums[i]);
            } else {
                // 1. 加入当前元素，当前元素有可能成为当前窗口的最大元素，所以要先执行
                window.push(nums[i]);
                // 2. 获取当前窗口中的最大值
                list.add(window.max());
                // 3. 将之前最早的元素移除掉，有可能该元素已经在第一步被删除掉了
                window.pop(nums[i - k + 1]);
            }
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * 自定义优先级队列
     */
    private static class MonotonicQueue {
        private LinkedList<Integer> queue = new LinkedList<>();

        /**
         * 删除队列中比他大的数
         *
         * @param num
         */
        public void push(int num) {
            while (!queue.isEmpty() && queue.getLast() < num) {
                queue.pollLast();
            }
            queue.addLast(num);
        }

        /**
         * 单调队列，直接返回头部即可
         *
         * @return
         */
        public int max() {
            return queue.getFirst();
        }

        /**
         * 如果队首元素和待删除元素相同就删掉，因为某元素有可能在别得比他大的元素入队时被删掉了
         *
         * @param num
         */
        public void pop(int num) {
            if (queue.getFirst() == num) {
                queue.pollFirst();
            }
        }
    }
}
