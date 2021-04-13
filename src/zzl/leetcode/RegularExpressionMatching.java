package zzl.leetcode;

import org.junit.Assert;

/**
 * 正则表达式匹配
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/regular-expression-matching/}
 */
public class RegularExpressionMatching {
    public static void main(String[] args) {
        String s;
        String p;
        boolean match;
        //
        s = "aa";
        p = "a";
        match = new RegularExpressionMatching().isMatch(s, p);
        Assert.assertEquals(match, false);
        //
        s = "aa";
        p = "a*";
        match = new RegularExpressionMatching().isMatch(s, p);
        Assert.assertEquals(match, true);
    }

    public boolean isMatch(String s, String p) {
        return dp(s.toCharArray(), 0, p.toCharArray(), 0);
    }

    /**
     * 动态规划
     * TODO：待优化，使用缓存提高速度
     *
     * @param s
     * @param i
     * @param p
     * @param j
     * @return
     */
    private boolean dp(char[] s, int i, char[] p, int j) {
        if (j == p.length) {
            return i == s.length;
        }
        if (i == s.length) {
            // 即前边的都匹配到了，但是后续除非是因为*通配导致命中0次，不然肯定是不匹配的
            // 且字符和*是成对出现
            if ((p.length - j) % 2 == 1) {
                return false;
            }
            // 判断后续是否有成对出现的字符和*号
            for (; j + 1 < p.length; j += 2) {
                if (p[j + 1] != '*') {
                    return false;
                }
            }
            return true;
        }
        // 匹配到了：“.”通配或者相同字符
        if (s[i] == p[j] || p[j] == '.') {
            if (j < p.length - 1 && p[j + 1] == '*') {
                // “*”没有匹配到，j直接向后移动两位接着匹配（*匹配0次）
                boolean none = dp(s, i, p, j + 2);
                // “*”匹配到了，i向后移动一位接着判断（*匹配多次）
                boolean match = dp(s, i + 1, p, j);
                // 两种情况只要有一种匹配到了就算匹配到了
                return none || match;
            } else {
                // 字符的形式匹配到，那就同时向后移动一位
                return dp(s, i + 1, p, j + 1);
            }
        } else {
            if (j < p.length - 1 && p[j + 1] == '*') {
                // 虽然字符不匹配，但是由于有一个*，并且*表示匹配0次，符合条件
                boolean none = dp(s, i, p, j + 2);
                return none;
            } else {
                // 字符直接不匹配，且没有特殊字符
                return false;
            }
        }
    }
}
