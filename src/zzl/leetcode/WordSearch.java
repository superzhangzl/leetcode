package zzl.leetcode;

import org.junit.Assert;
import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

/**
 * 单词搜索
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/word-search/}
 */
public class WordSearch {
    public static void main(String[] args) {
        // 每次重新生成一个数组，不然上一次设置了特殊字符，会导致下一个条件判断失效
        char[][] board = GenerateUtil.generateBinaryCharArray(
                "A,B,C,E\n" +
                        "S,F,C,S\n" +
                        "A,D,E,E\n", ",");
        Assert.assertEquals(new WordSearch().exist(board, "ABCCED"), true);
        board = GenerateUtil.generateBinaryCharArray(
                "A,B,C,E\n" +
                        "S,F,C,S\n" +
                        "A,D,E,E\n", ",");
        Assert.assertEquals(new WordSearch().exist(board, "SEE"), true);
        board = GenerateUtil.generateBinaryCharArray(
                "A,B,C,E\n" +
                        "S,F,C,S\n" +
                        "A,D,E,E\n", ",");
        Assert.assertEquals(new WordSearch().exist(board, "ABCB"), false);
        board = GenerateUtil.generateBinaryCharArray(
                "a", ",");
        Assert.assertEquals(new WordSearch().exist(board, "a"), true);
        board = GenerateUtil.generateBinaryCharArray(
                "C,A,A\n" +
                        "A,A,A\n" +
                        "B,C,D\n", ",");
        Assert.assertEquals(new WordSearch().exist(board, "AAB"), true);
    }

    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0) {
            return false;
        }
        int height = board.length;
        int width = board[0].length;
        char[] chars = word.toCharArray();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (board[i][j] == chars[0]) {
                    boolean dfs = dfs(board, i, j, chars, 0);
                    System.out.println(dfs);
                    if (dfs) {
                        System.out.println();
                        return true;
                    }
                }
            }
        }
        System.out.println();
        return false;
    }

    private boolean dfs(char[][] board, int i, int j, char[] chars, int index) {
        if (index == chars.length) {
            return true;
        }
        if (i < 0 || i > board.length - 1 || j < 0 || j > board[0].length - 1 || board[i][j] != chars[index]) {
            return false;
        }
        System.out.println(String.format("i=%d, j=%d, board[i][j]=%s, index=%d, chars[index]=%s", i, j, board[i][j], index, chars[index]));
        // 标记当前是否访问过，防止出现重复
        char temp = board[i][j];
        // 临时设置为其他的特殊字符
        board[i][j] = '?';
        boolean res = dfs(board, i - 1, j, chars, index + 1) ||
                dfs(board, i + 1, j, chars, index + 1) ||
                dfs(board, i, j - 1, chars, index + 1) ||
                dfs(board, i, j + 1, chars, index + 1);
        if (res == true) {
            return true;
        } else {
            // 如果以当前节点不通过，那就改回去，不要影响从下一个节点开始
            board[i][j] = temp;
            return false;
        }
    }
}
