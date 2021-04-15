package zzl.leetcode;

import org.junit.Assert;
import zzl.util.GenerateUtil;

/**
 * 最大矩形
 * TODO：暂存，先做84题
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

    public int maximalRectangle(char[][] matrix) {

        return 0;
    }
}
