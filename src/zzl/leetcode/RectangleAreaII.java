package zzl.leetcode;

import org.junit.Assert;
import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

/**
 * 矩形面积 II
 * TODO 暂存，线段树
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/rectangle-area-ii/}
 */
public class RectangleAreaII {

    public static void main(String[] args) {
        int[][] arrs;
        int rectangleArea;
        //
        arrs = GenerateUtil.generateBinaryIntArrayBetter("[[0,0,2,2],[1,0,2,3],[1,0,3,1]]");
        rectangleArea = new RectangleAreaII().rectangleArea(arrs);
        Assert.assertEquals(rectangleArea, 6);
        //
        arrs = GenerateUtil.generateBinaryIntArrayBetter("[[0,0,1000000000,1000000000]]");
        rectangleArea = new RectangleAreaII().rectangleArea(arrs);
        Assert.assertEquals(rectangleArea, 49);

    }

    public int rectangleArea(int[][] rectangles) {
        int max = 0;
        for (int[] rectangle : rectangles) {
            for (int index : rectangle) {
                max = Math.max(index, max);
            }
        }
        System.out.println(max);
        // 最差的思路，肯定会超时
        int[][] arrs = new int[max + 1][max + 1];
        for (int[] rectangle : rectangles) {
            for (int i = rectangle[0]; i < rectangle[2]; i++) {
                for (int j = rectangle[1]; j < rectangle[3]; j++) {
                    arrs[i][j] = 1;
                }
            }
        }
        PrintConsoleUtil.printArray(arrs);
        int area = 0;
        for (int[] arr : arrs) {
            for (int i : arr) {
                if (i == 1) {
                    area++;
                }
            }
        }
        return area % 1_000_000_000;
    }
}
