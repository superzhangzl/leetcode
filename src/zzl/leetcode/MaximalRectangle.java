package zzl.leetcode;

import org.junit.Assert;
import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 最大矩形
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/maximal-rectangle/}
 */
public class MaximalRectangle {
    public static void main(String[] args) {
        char[][] matrix;
        int maximalRectangle;
        //
        matrix = GenerateUtil.generateBinaryCharArrayBetter("[[\"1\",\"0\",\"1\",\"0\",\"0\"]," +
                "[\"1\",\"0\",\"1\",\"1\",\"1\"]," +
                "[\"1\",\"1\",\"1\",\"1\",\"1\"]," +
                "[\"1\",\"0\",\"0\",\"1\",\"0\"]]");
        maximalRectangle = new MaximalRectangle().maximalRectangle(matrix);
        Assert.assertEquals(maximalRectangle, 6);
    }

    /**
     * 详见柱状图中最大的矩形 {@link LargestRectangleInHistogram#largestRectangleArea(int[])}
     * 对数组每一层进行叠加，形成柱状图，然后用84题的方法进行计算并更新
     *
     * @param matrix
     * @return
     * @link {https://leetcode-cn.com/problems/maximal-rectangle/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-1-8/}
     */
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        int[] heights = new int[matrix[0].length];
        int maxArea = 0;
        for (int row = 0; row < matrix.length; row++) {
            //遍历每一列，更新高度
            for (int col = 0; col < matrix[0].length; col++) {
                if (matrix[row][col] == '1') {
                    heights[col] += 1;
                } else {
                    // 注意事项：底为0时直接把高度设置为1，即使上面还有1,也设置为0。
                    // 上面的1在上一轮的时候就计算过了
                    heights[col] = 0;
                }
            }
            //调用上一题的解法，更新函数
            PrintConsoleUtil.printArray(heights);
            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }
        return maxArea;
    }

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
        for (int i = 1; i < len; i++) {
            while (newHeight[i] < newHeight[stack.peek()]) {
                int curHeight = newHeight[stack.pop()];
                // 减一是因为前面哨兵多了一位
                int curWidth = i - stack.peek() - 1;
                int area = curWidth * curHeight;
                res = Math.max(area, res);
            }
            stack.push(i);
        }
        return res;
    }
}
