package zzl.leetcode;

import org.junit.Assert;

public class MinimumInsertionStepsToMakeAStringPalindrome {
    public static void main(String[] args) {
        String s;
        int minInsertions;
        //
        s = "zzazz";
        minInsertions = new MinimumInsertionStepsToMakeAStringPalindrome().minInsertions(s);
        Assert.assertEquals(minInsertions, 0);
        //
        s = "mbadm";
        minInsertions = new MinimumInsertionStepsToMakeAStringPalindrome().minInsertions(s);
        Assert.assertEquals(minInsertions, 2);
        //
        s = "leetcode";
        minInsertions = new MinimumInsertionStepsToMakeAStringPalindrome().minInsertions(s);
        Assert.assertEquals(minInsertions, 5);
    }

    public int minInsertions(String s) {
        int len = s.length();
        // 对字符串s[i...j]，最少需要进行dp[i][j]次插入才能变成回文串
        int[][] dp = new int[len][len];

        for (int i = len - 2; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1];
                } else {
                    // 下面表达式的操作肯定会变成子串，但是不是最少的
                    // dp[i][j] = dp[i + 1][j - 1] + 2;
                    // s[i+1][j]和s[i][j-1]中变成回文串次数最小的，+1表示选其中一个进行一次操作
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
                }
            }

        }
        return dp[0][len - 1];
    }
}
