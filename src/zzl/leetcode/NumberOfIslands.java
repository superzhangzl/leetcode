package zzl.leetcode;

import org.junit.Assert;

/**
 * 岛屿数量
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/number-of-islands/}
 */
public class NumberOfIslands {
    public static void main(String[] args) {
        char[][] grid = new char[][]{
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        };
        Assert.assertEquals(new NumberOfIslands().numIslands(grid), 1);
    }

    public int numIslands(char[][] grid) {
        // 特解
        if (grid == null || grid.length == 0) {
            return 0;
        }
        /*
        思路：以其中的一个点为起始点，从这个点开始执行深度遍历（遍历过的点设置为0），当遍历完后认为是一个小岛
            执行了几次深度遍历，就有几个小岛
         */
        int gridCount = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // 如果遇到一个1，则从这个位置开始DFS
                if (grid[i][j] == '1') {
                    gridCount++;
                    dfs(grid, i, j);
                }
            }
        }
        return gridCount;
    }

    private void dfs(char[][] grid, int i, int j) {
        // 下标越界，以及不为1
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == '0') {
            return;
        }
        // 首先设置当前(i,j)为0，下次就不用执行到此处了
        grid[i][j] = '0';
        // 上
        dfs(grid, i, j - 1);
        // 下
        dfs(grid, i, j + 1);
        // 左
        dfs(grid, i - 1, j);
        // 右
        dfs(grid, i + 1, j);
    }
}
