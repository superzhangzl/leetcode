package zzl.leetcode;

import org.junit.Assert;
import zzl.util.GenerateUtil;

import java.util.Stack;

/**
 * 接雨水
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/trapping-rain-water/}
 */
public class TrappingRainWater {
    public static void main(String[] args) {
        int[] input = GenerateUtil.generateIntArray("0,1,0,2,1,0,1,3,2,1,2,1", ",");
        Assert.assertEquals(trap(input), 6);
        input = GenerateUtil.generateIntArray("0,2,0", ",");
        Assert.assertEquals(trap(input), 0);

    }

    /**
     * 单调栈，构造一个递减的单调栈，当出现一个较大的数字时，即构成了一个可以封闭起来的区间，把这个区间之内的面积计算出来
     *
     * @param height
     * @return
     */
    public static int trap(int[] height) {
        Stack<Integer> singleStack = new Stack<>();
        int answer = 0;
        for (int i = 0; i < height.length; i++) {
            System.out.println(singleStack);
            while (!singleStack.isEmpty() && height[singleStack.peek()] < height[i]) {
                int rightTopIdx = singleStack.pop();
                // 连续相等
                while (!singleStack.isEmpty() && height[rightTopIdx] == height[singleStack.peek()]) {
                    singleStack.pop();
                }
                if (!singleStack.isEmpty()) {
                    int leftTopIdx = singleStack.peek();
                    answer += (Math.min(height[leftTopIdx], height[i]) - height[rightTopIdx]) * (i - leftTopIdx - 1);
                    System.out.println("left=" + leftTopIdx + ", right=" + rightTopIdx);
                }
            }
            System.out.println(singleStack + ">" + answer);
            singleStack.push(i);
        }
        return answer;
    }
}
