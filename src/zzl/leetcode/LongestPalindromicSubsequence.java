package zzl.leetcode;

import org.junit.Assert;

public class LongestPalindromicSubsequence {

    public static void main(String[] args) {
        String s;
        int longestPalindromeSubseq;
        //
        s = "bbbab";
        longestPalindromeSubseq = new LongestPalindromicSubsequence().longestPalindromeSubseq(s);
        Assert.assertEquals(longestPalindromeSubseq, 4);
        //
        s = "cbbd";
        longestPalindromeSubseq = new LongestPalindromicSubsequence().longestPalindromeSubseq(s);
        Assert.assertEquals(longestPalindromeSubseq, 2);
    }

    public int longestPalindromeSubseq(String s) {
        int len = s.length();
        // 在子串s[i...j]中，最长回文子序列的长度为dp[i][j]
        int[][] dp = new int[len][len];
        // 单个字符肯定是回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = 1;
        }
        // dp[i][j]的状态依赖左边、下边和左下方的值，所以外层for循环需要倒序遍历
        for (int i = len - 2; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                // 如果s[i] == s[j]，肯定是回文串，长度直接+2
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[0][len - 1];
    }
}
