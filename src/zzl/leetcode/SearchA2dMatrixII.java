package zzl.leetcode;

import org.junit.Assert;
import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

/**
 * 搜索二维矩阵 II
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/search-a-2d-matrix-ii/}
 */
public class SearchA2dMatrixII {
    public static void main(String[] args) {
        String input = "  1,   4,  7, 11, 15\n" +
                "  2,   5,  8, 12, 19\n" +
                "  3,   6,  9, 16, 22\n" +
                "  10, 13, 14, 17, 24\n" +
                "  18, 21, 23, 26, 30\n";
        int[][] matrix = GenerateUtil.generateBinaryIntArray(input, ",");
        PrintConsoleUtil.printArray(matrix);
        Assert.assertEquals(new SearchA2dMatrixII().searchMatrix(matrix, 5), true);
        matrix = GenerateUtil.generateBinaryIntArray(input, ",");
        Assert.assertEquals(new SearchA2dMatrixII().searchMatrix(matrix, 20), false);
    }

    /**
     * 官方解题4：
     * 注：需要构造的是一个递增序列
     * 选左上角，往右走和往下走都增大，不能选
     * 选右下角，往上走和往左走都减小，不能选
     * 选左下角，往右走增大，往上走减小，可选
     * 选右上角，往下走增大，往左走减小，可选
     *
     * @param matrix
     * @param target
     * @return
     * @link {https://leetcode-cn.com/problems/search-a-2d-matrix-ii/solution/sou-suo-er-wei-ju-zhen-ii-by-leetcode-2/}
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int height = matrix.length - 1;
        int width = 0;
        while (height >= 0 && width < matrix[0].length) {
            if (matrix[height][width] > target) {
                height--;
            } else if (matrix[height][width] < target) {
                width++;
            } else { // found it
                return true;
            }
        }
        return false;
    }
}
