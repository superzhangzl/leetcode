package zzl.leetcode;

import org.junit.Assert;
import zzl.util.GenerateUtil;

/**
 * 矩阵中的最长递增路径
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/longest-increasing-path-in-a-matrix/}
 */
public class LongestIncreasingPathInAMatrix {
    public static void main(String[] args) {
        LongestIncreasingPathInAMatrix util = new LongestIncreasingPathInAMatrix();
        int[][] input = GenerateUtil.generateBinaryIntArray(
                "9,9,4,\n" +
                        "6,6,8,\n" +
                        "2,1,1\n", ",");
        Assert.assertEquals(util.longestIncreasingPath(input), 4);
        input = GenerateUtil.generateBinaryIntArray(
                "3,4,5,\n" +
                        "3,2,6,\n" +
                        "2,2,1", ",");
        Assert.assertEquals(util.longestIncreasingPath(input), 4);
    }

    private final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private int m, n;

    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0) return 0;
        m = matrix.length;
        n = matrix[0].length;
        int[][] cache = new int[m][n];
        int ans = 0;
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                ans = Math.max(ans, dfs(matrix, i, j, cache));
        return ans;
    }

    private int dfs(int[][] matrix, int i, int j, int[][] cache) {
        if (cache[i][j] != 0) {
            return cache[i][j];
        }
        for (int[] d : dirs) {
            int x = i + d[0], y = j + d[1];
            if (0 <= x && x < m && 0 <= y && y < n && matrix[x][y] > matrix[i][j]) {
                cache[i][j] = Math.max(cache[i][j], dfs(matrix, x, y, cache));
            }
        }
        return ++cache[i][j];
    }
}
