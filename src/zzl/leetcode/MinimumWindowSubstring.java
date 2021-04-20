package zzl.leetcode;

import org.junit.Assert;
import zzl.base.annotation.Level;

import java.util.HashMap;
import java.util.Map;

import static zzl.base.enums.Difficulty.*;

/**
 * 最小覆盖子串
 *
 * @author zzl
 * @date 2021-04-20
 * @link {https://leetcode-cn.com/problems/minimum-window-substring/}
 */
@Level(HARD)
public class MinimumWindowSubstring {
    public static void main(String[] args) {
        String minWindow;
        //
        minWindow = new MinimumWindowSubstring().minWindow("ADOBECODEBANC", "ABC");
        Assert.assertEquals(minWindow, "BANC");
        //
        minWindow = new MinimumWindowSubstring().minWindow("a", "a");
        Assert.assertEquals(minWindow, "a");
    }

    public String minWindow(String s, String t) {
        if (s == null || s.length() == 0 || t == null || t.length() == 0) {
            return "";
        }
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        int left = 0;
        int right = 0;
        int valid = 0; // 表示窗口中满足need条件的字符个数
        int start = 0;
        int len = Integer.MAX_VALUE;
        /*
        一般来说，如果一个字符进入窗口，应该增加window计数器；如果一个字符将移出窗口，应该减少window计数器；
        当valid满足need时应该收缩窗口；收缩窗口的时候应该更新最终结果。
         */
        while (right < s.length()) {
            // 将放入窗口中的字符
            char c = s.charAt(right);
            right++;
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                // integer类型使用equal比较
                if (window.get(c).equals(need.get(c))) {
                    valid++;
                }
            }

            while (valid == need.size()) {
                // 当前窗口的长度
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                // 将要移出窗口的字符
                char d = s.charAt(left);
                left++;
                if (need.containsKey(d)) {
                    if (window.get(d).equals(need.get(d))) {
                        valid--;
                    }
                    window.put(d, window.getOrDefault(d, 0) - 1);
                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);
    }
}
