package zzl.leetcode;

import org.junit.Assert;

/**
 * @author zzl
 * @link {https://leetcode-cn.com/problems/implement-strstr/}
 */
public class ImplementStrstr {
    public static void main(String[] args) {
        String haystack = "hello";
        String needle = "ll";
        Assert.assertEquals(new ImplementStrstr().strStr(haystack, needle), 2);
        haystack = "aaaaa";
        needle = "bba";
        Assert.assertEquals(new ImplementStrstr().strStr(haystack, needle), -1);
    }

    public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }
}
