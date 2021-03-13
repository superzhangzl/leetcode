package zzl.leetcode;

import org.junit.Assert;
import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

/**
 * 不同路径III
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/unique-paths-iii/}
 */
public class UniquePathsIII {
    public static void main(String[] args) {
        int[][] grid;
        int uniquePaths;
        grid = GenerateUtil.generateBinaryIntArrayBetter("[[1,0,0,0],[0,0,0,0],[0,0,2,-1]]");
        PrintConsoleUtil.printArray(grid);
        uniquePaths = new UniquePathsIII().uniquePathsIII(grid);
        Assert.assertEquals(uniquePaths, 2);
        //
        grid = GenerateUtil.generateBinaryIntArrayBetter("[[1,0,0,0],[0,0,0,0],[0,0,0,2]]");
        PrintConsoleUtil.printArray(grid);
        uniquePaths = new UniquePathsIII().uniquePathsIII(grid);
        Assert.assertEquals(uniquePaths, 4);
        //
        grid = GenerateUtil.generateBinaryIntArrayBetter("[[0,1],[2,0]]");
        PrintConsoleUtil.printArray(grid);
        uniquePaths = new UniquePathsIII().uniquePathsIII(grid);
        Assert.assertEquals(uniquePaths, 0);
    }

    // 默认起点和终点只有一个
    private int[] startIndex;
    // 可通过点的个数
    private int rightPathCount = 0;
    // 不同路径条数
    private int uniquePathCount = 0;
    private int xl;
    private int yl;
    private static final int START_FLAG = 1;
    private static final int END_FLAG = 2;
    private static final int PATH_FLAG = 0;
    private static final int STOP_FLAG = -1;

    public int uniquePathsIII(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        xl = grid.length;
        yl = grid[0].length;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == START_FLAG) {
                    startIndex = new int[]{i, j};
                }
                if (grid[i][j] != STOP_FLAG) {
                    rightPathCount++;
                }
            }
        }
        uniquePathCount = 0;
        dfs(grid, startIndex[0], startIndex[1], 1);
        return uniquePathCount;
    }

    private int[][] position = {
            {0, 1},
            {0, -1},
            {1, 0},
            {-1, 0}
    };

    private void dfs(int[][] grid, int x, int y, int pathLength) {
        // 结束条件
        if (grid[x][y] == END_FLAG) {
            // 只有每一个节点都走一遍才算结束
            if (pathLength == rightPathCount) {
                uniquePathCount++;
            }
            return;
        }
        // 使用grid来做visited数组
        grid[x][y] = STOP_FLAG;
        for (int[] pos : position) {
            int nx = x + pos[0];
            int ny = y + pos[1];
            if (inArea(nx, ny) && (grid[nx][ny] == PATH_FLAG || grid[nx][ny] == END_FLAG)) {
                dfs(grid, nx, ny, pathLength + 1);
            }
        }
        // 恢复可访问状态
        grid[x][y] = PATH_FLAG;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < xl && y >= 0 && y < yl;
    }
}
