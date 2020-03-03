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



    public List<List<String>> partition(String s) {
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
