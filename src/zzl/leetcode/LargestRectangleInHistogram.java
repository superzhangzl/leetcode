package zzl.leetcode;

import org.junit.Assert;
import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 柱状图中最大的矩形
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/largest-rectangle-in-histogram/}
 */
public class LargestRectangleInHistogram {
    public static void main(String[] args) {
        int[] heights;
        int largestRectangleArea;
        //
        heights = GenerateUtil.generateIntArray("2,1,5,6,2,3");
        largestRectangleArea = new LargestRectangleInHistogram().largestRectangleArea(heights);
        Assert.assertEquals(largestRectangleArea, 10);
    }

    /**
     * 单调栈
     *
     * @param heights
     * @return
     * @link {https://leetcode-cn.com/problems/largest-rectangle-in-histogram/solution/bao-li-jie-fa-zhan-by-liweiwei1419/}
     */
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int len = heights.length;
        // 两头新增高度为0的哨兵单位
        int[] newHeight = new int[len + 2];
        newHeight[0] = 0;
        System.arraycopy(heights, 0, newHeight, 1, len);
        len += 2;
        newHeight[len - 1] = 0;

        int res = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.add(0);
        PrintConsoleUtil.printArray(newHeight);
        for (int i = 1; i < len; i++) {
            while (newHeight[i] < newHeight[stack.peek()]) {
                int curHeight = newHeight[stack.pop()];
                // 减一是因为前面哨兵多了一位
                int curWidth = i - stack.peek() - 1;
                int area = curWidth * curHeight;
                System.out.println(String.format("i=%d, [%d]=%d, height=%d, width=%d, area=%d",
                        i, stack.peek(), newHeight[stack.peek()], curHeight, curWidth, area));
                PrintConsoleUtil.printArrayWithIndex(newHeight, i);
                res = Math.max(area, res);
            }
            stack.push(i);
        }
        return res;
    }
}