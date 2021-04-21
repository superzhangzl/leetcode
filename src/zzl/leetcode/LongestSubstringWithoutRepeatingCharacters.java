package zzl.leetcode;

import org.junit.Assert;
import zzl.base.annotation.Level;

import java.util.HashMap;
import java.util.Map;

import static zzl.base.enums.Difficulty.*;

/**
 * 无重复字符的最长子串
 *
 * @author zzl
 * @date 2021-04-21
 * @link {https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/}
 */
@Level(MEDIUM)
public class LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        int lengthOfLongestSubstring;
        //
        lengthOfLongestSubstring = new LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring("abcabcbb");
        Assert.assertEquals(lengthOfLongestSubstring, 3);
        //
        lengthOfLongestSubstring = new LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring("bbbbb");
        Assert.assertEquals(lengthOfLongestSubstring, 1);
        //
        lengthOfLongestSubstring = new LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring("pwwkew");
        Assert.assertEquals(lengthOfLongestSubstring, 3);
        //
        lengthOfLongestSubstring = new LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring("");
        Assert.assertEquals(lengthOfLongestSubstring, 0);
    }

    /**
     * 滑动窗口
     *
     * @param s
     * @return
     * @see MinimumWindowSubstring#minWindow(String, String)
     */
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> window = new HashMap<>();
        int left = 0;
        int right = 0;
        int res = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            window.put(c, window.getOrDefault(c, 0) + 1);
            // 如果当前字符在窗口中出现多次，说明存在重复字符，需要删除
            // 但是删除的话，有可能把窗口内别的字符删掉，可保证当前窗口内无重复子串
            // 注意是子串不是子序列
            while (window.get(c) > 1) {
                char d = s.charAt(left);
                left++;
                window.put(d, window.getOrDefault(d, 0) - 1);
            }
            // 在窗口更新完以后再刷新最大的窗口大小
            res = Math.max(res, right - left);
        }
        return res;
    }
}
