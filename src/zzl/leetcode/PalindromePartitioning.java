package zzl.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 分割回文串
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 * <p>
 * 返回 s 所有可能的分割方案。
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/palindrome-partitioning/}
 */
public class PalindromePartitioning {
    public static void main(String[] args) {
        System.out.println(new PalindromePartitioning().partition("aab"));
        System.out.println(new PalindromePartitioning().partition("efe"));
    }

    /**
     * 通过动态规划，对回文串进行预操作
     *
     * @param s
     * @return
     * @link {https://leetcode-cn.com/problems/palindrome-partitioning/solution/hui-su-you-hua-jia-liao-dong-tai-gui-hua-by-liweiw/}
     */
    public List<List<String>> partition(String s) {
        int len = s.length();
        List<List<String>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }

        // 预处理
        // 状态：dp[i][j] 表示 s[i][j] 是否是回文
        boolean[][] dp = new boolean[len][len];
        // 状态转移方程：在 s[i] == s[j] 的时候，dp[i][j] 参考 dp[i + 1][j - 1]
        for (int right = 0; right < len; right++) {
            // 注意：left <= right 取等号表示 1 个字符的时候也需要判断
            for (int left = 0; left <= right; left++) {
                System.out.print("(" + left + "," + right + ")" + ">" + s.substring(left, right + 1) + "\t");
                // 对于字符串 s
                // 用 dp[i][j] 表示 s[i，j] 是否是回文串。
                // 然后有 dp[i][j] = (s[i] == s[j]) && dp[i+1][j-1] 。
                // 此处right-left<=2时，为了判断三位一下的子串，其中0代表本身，1代表相邻，2代表三位的，再加上前面的==判断
                if (s.charAt(left) == s.charAt(right) && (right - left <= 2 || dp[left + 1][right - 1])) {
                    dp[left][right] = true;
                }
            }
            System.out.println();
        }
        for (int i = 0; i < len; i++) {
            // 注意：left <= i 取等号表示 1 个字符的时候也需要判断
            for (int j = 0; j < len; j++) {
                System.out.print(dp[j][i] + " ");
            }
            System.out.println();
        }

        Deque<String> stack = new ArrayDeque<>();
        backtracking(s, 0, len, dp, stack, res);
        return res;
    }

    private void backtracking(String s,
                              int start,
                              int len,
                              boolean[][] dp,
                              Deque<String> path,
                              List<List<String>> res) {
        if (start == len) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i < len; i++) {
            // 剪枝
            if (!dp[start][i]) {
                continue;
            }
            path.addLast(s.substring(start, i + 1));
            backtracking(s, i + 1, len, dp, path, res);
            path.removeLast();
        }
    }

    public List<List<String>> partition1(String s) {
        print(s, 0, s.length(), new ArrayDeque<>());
        return result;
    }

    List<List<String>> result = new ArrayList<>();

    private void print(String str, int start, int length, Deque<String> path) {
        if (start == length) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i < length; i++) {
            // sub消耗性能
            String substring = str.substring(start, i + 1);
            if (!checkPalindrome(substring)) {
                continue;
            }
            path.add(substring);
            print(str, i + 1, length, path);
            path.removeLast();
        }
    }

    private boolean checkPalindrome(String substring) {
        for (int left = 0, right = substring.length() - 1; left <= right; left++, right--) {
            if (substring.charAt(left) != substring.charAt(right)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 遍历所有子串，但这样不是分割
     * StringBuilder 是线程安全的，会有些性能开销
     */
    private void allSubString(String str, int start, int length, StringBuffer sb) {
        if (sb.length() == length) {
            System.out.println(sb.toString());
            return;
        }
        System.out.println(sb.toString());
        for (int i = start; i < str.length(); i++) {
            sb.append(str.charAt(i));
            allSubString(str, i + 1, length, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
