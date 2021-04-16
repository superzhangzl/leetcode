package zzl.leetcode;

import org.junit.Assert;
import zzl.base.annotation.Level;
import zzl.util.GenerateUtil;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import static zzl.base.enums.Difficulty.*;

/**
 * 下一个更大元素 II
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/next-greater-element-ii/}
 */
@Level(MEDIUM)
public class NextGreaterElementII {
    public static void main(String[] args) {
        int[] nums;
        int[] nextGreaterElements;
        //
        nums = GenerateUtil.generateIntArray("1,2,1");
        nextGreaterElements = new NextGreaterElementII().nextGreaterElements(nums);
        Assert.assertArrayEquals(nextGreaterElements, GenerateUtil.generateIntArray("2,-1,2"));
    }

    /**
     * 单调栈
     *
     * @param nums
     * @return
     */
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        List<Integer> list = new ArrayList<>();
        int[] res = new int[n];
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        /*
        单调栈：倒叙遍历，倒着入栈，相当于正着出栈
        循环顺组，长度翻二倍然后模n相当于模拟循环操作
         */
        for (int i = 2 * n - 1; i >= 0; i--) {
            // 单调增的栈
            while (!stack.isEmpty() && stack.peek() <= nums[i % n]) {
                stack.pop();// 矮个子的出列，反正也被高的挡住了
            }
            res[i % n] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i % n]);
        }
        return res;
    }
}
