package zzl.leetcode;

import zzl.util.PrintConsoleUtil;

/**
 * 被围绕的区域
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/surrounded-regions/}
 */
public class SurroundedRegions {
    public static void main(String[] args) {
        char[][] regions = new char[][]{
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'}
        };
        new SurroundedRegions().solve(regions);
        PrintConsoleUtil.printArray(regions);
    }

    /**
     * 反向思路，先找与边界联通的'O'，特殊标记一下，那剩下的'O'就是被包围的，更改完再反向设置回去
     * 基础思路参考"岛屿数量"一题
     * 基本矩阵的题就是和图的遍历有关
     * {@link NumberOfIslands}
     */
    public void solve(char[][] board) {
        if (board == null || board.length == 0) {
            return;
        }
        // 图的遍历
        int height = board.length;
        int weight = board[0].length;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < weight; j++) {
                if ((i == 0 || j == 0 || i == height - 1 || j == weight - 1) && board[i][j] == 'O') {
                    dfs(board, i, j);
                }
            }
        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < weight; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void dfs(char[][] board, int i, int j) {
        // 下标越界，以及不为1
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] == 'X' || board[i][j] == '#') {
            return;
        }
        // 首先设置当前(i,j)为0，下次就不用执行到此处了
        board[i][j] = '#';
        // 上
        dfs(board, i, j - 1);
        // 下
        dfs(board, i, j + 1);
        // 左
        dfs(board, i - 1, j);
        // 右
        dfs(board, i + 1, j);
    }
}
