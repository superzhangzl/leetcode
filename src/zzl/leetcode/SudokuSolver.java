package zzl.leetcode;

import zzl.base.annotation.Level;
import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

import static zzl.base.enums.Difficulty.*;

/**
 * 解数独
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/sudoku-solver/}
 */
@Level(HARD)
public class SudokuSolver {
    public static void main(String[] args) {
        char[][] board = GenerateUtil.generateBinaryCharArrayBetter("[[\"5\",\"3\",\".\",\".\",\"7\",\".\",\".\",\".\",\".\"]," +
                "[\"6\",\".\",\".\",\"1\",\"9\",\"5\",\".\",\".\",\".\"]," +
                "[\".\",\"9\",\"8\",\".\",\".\",\".\",\".\",\"6\",\".\"]," +
                "[\"8\",\".\",\".\",\".\",\"6\",\".\",\".\",\".\",\"3\"]," +
                "[\"4\",\".\",\".\",\"8\",\".\",\"3\",\".\",\".\",\"1\"]," +
                "[\"7\",\".\",\".\",\".\",\"2\",\".\",\".\",\".\",\"6\"]," +
                "[\".\",\"6\",\".\",\".\",\".\",\".\",\"2\",\"8\",\".\"]," +
                "[\".\",\".\",\".\",\"4\",\"1\",\"9\",\".\",\".\",\"5\"]," +
                "[\".\",\".\",\".\",\".\",\"8\",\".\",\".\",\"7\",\"9\"]]");
        new SudokuSolver().solveSudoku(board);
        PrintConsoleUtil.printArray(board);

    }

    /**
     * 递归回溯每一条可能的路径
     *
     * @param board
     */
    public void solveSudoku(char[][] board) {
        backtrace(board, 0, 0);
    }

    private boolean backtrace(char[][] board, int i, int j) {
        int m = 9, n = 9;
        // 当前行遍历完了到下一行
        if (j == n) {
            return backtrace(board, i + 1, 0);
        }
        // 到最后一行了，计算完了直接返回true
        if (i == m) {
            return true;
        }
        // 自带数字，直接跳过
        if (board[i][j] != '.') {
            return backtrace(board, i, j + 1);
        }
        // 尝试1~9所有的数字
        for (char ch = '1'; ch <= '9'; ch++) {
            // 过滤掉无效的数字
            if (!isValid(board, i, j, ch)) {
                continue;
            }
            board[i][j] = ch;
            // 如果返回true表示遍历完了，直接返回当前递归深度的路径
            if (backtrace(board, i, j + 1)) {
                return true;
            }
            board[i][j] = '.';
        }
        // 即所有的数字都尝试完了也找不到合适的数字，那就返回false表示不存在结果
        return false;
    }

    private boolean isValid(char[][] board, int i, int j, char ch) {
        for (int k = 0; k < 9; k++) {
            // 同一行
            if (board[i][k] == ch) {
                return false;
            }
            // 同一列
            if (board[k][j] == ch) {
                return false;
            }
            // 判断(i,j)所在的9宫格内是否有相同的数字
            if (board[(i / 3) * 3 + k / 3][(j / 3) * 3 + k % 3] == ch) {
                return false;
            }
        }
        return true;
    }
}
