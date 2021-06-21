package zzl.leetcode;

import org.junit.Assert;
import zzl.base.annotation.Level;
import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

import static zzl.base.enums.Difficulty.*;

/**
 * 排序矩阵查找
 *
 * @author zzl
 * @date 2021-05-14
 * @link {https://leetcode-cn.com/problems/sorted-matrix-search-lcci/}
 */
@Level(MEDIUM)
public class SortedMatrixSearchLcci {
    public static void main(String[] args) {
        int[][] matrix = GenerateUtil.generateBinaryIntArrayBetter("[[1,   4,  7, 11, 15],\n" +
                "[2,   5,  8, 12, 19],\n" +
                "[3,   6,  9, 16, 22],\n" +
                "[10, 13, 14, 17, 24],\n" +
                "[18, 21, 23, 26, 30]]");
        boolean searchMatrix;
        //
        searchMatrix = new SortedMatrixSearchLcci().searchMatrix(matrix, 5);
        Assert.assertTrue(searchMatrix);
        //
        searchMatrix = new SortedMatrixSearchLcci().searchMatrix(matrix, 20);
        Assert.assertFalse(searchMatrix);
    }

    /**
     * @param matrix
     * @param target
     * @return
     * @link {https://leetcode-cn.com/problems/sorted-matrix-search-lcci/solution/shu-ju-jie-gou-he-suan-fa-3chong-jie-fa-5f7f3/}
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0)
            return false;
        int col = 0;//列
        int row = matrix.length - 1;//行
        //从左下角开始找
        while (row >= 0 && col <= matrix[0].length - 1) {
            if (target == matrix[row][col]) {
                return true;
            } else if (target < matrix[row][col]) {
                row--;
            } else if (target > matrix[row][col]) {
                col++;
            }
        }
        return false;
    }

}
