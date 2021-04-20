package zzl.leetcode;

import org.junit.Assert;
import zzl.base.annotation.Level;

import static zzl.base.enums.Difficulty.*;

/**
 * 回文子串
 *
 * @author zzl
 * @date 2021-04-20
 * @link {https://leetcode-cn.com/problems/palindromic-substrings/}
 */
@Level(MEDIUM)
public class PalindromicSubstrings {
    public static void main(String[] args) {
        int countSubstrings;
        //
        countSubstrings = new PalindromicSubstrings().countSubstrings("abc");
        Assert.assertEquals(countSubstrings, 3);
        //
        countSubstrings = new PalindromicSubstrings().countSubstrings("aaa");
        Assert.assertEquals(countSubstrings, 6);
    }

    public int countSubstrings(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            // 奇数时，出发点为同一个的时候
            int left, right = left = i;
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
                res++;
            }
            // 偶数时，left和right相邻
            right = i + 1;
            left = i;
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
                res++;
            }
        }
        return res;
    }
}
