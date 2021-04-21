package zzl.leetcode;

import org.junit.Assert;
import zzl.base.annotation.Level;

import java.util.HashMap;
import java.util.Map;

import static zzl.base.enums.Difficulty.MEDIUM;

/**
 * 字符串的排列
 *
 * @author zzl
 * @date 2021-04-21
 * @link {https://leetcode-cn.com/problems/permutation-in-string/}
 */
@Level(MEDIUM)
public class PermutationInString {
    public static void main(String[] args) {
        boolean checkInclusion;
        //
        checkInclusion = new PermutationInString().checkInclusion("ab", "eidbaooo");
        Assert.assertEquals(checkInclusion, true);
        //
        checkInclusion = new PermutationInString().checkInclusion("ab", "eidboaoo");
        Assert.assertEquals(checkInclusion, false);
    }

    /**
     * 滑动窗口，先判断窗口内是否包含了所有需要的字符，然后再在主键缩小的过程中判断是否满足条件
     *
     * @param s1
     * @param s2
     * @return
     * @see MinimumWindowSubstring#minWindow(String, String)
     */
    public boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        for (char c : s1.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        int left = 0;
        int right = 0;
        int valid = 0;
        while (right < s2.length()) {
            char c = s2.charAt(right);
            right++;
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(need.get(c))) {
                    valid++;
                }
            }
            while (right - left >= s1.length()) {
                if (valid == need.size()) {
                    return true;
                }
                char d = s2.charAt(left);
                left++;
                if (need.containsKey(d)) {
                    if (window.get(d).equals(need.get(d))) {
                        valid--;
                    }
                    // 先判断合法值数量是否需要减小，然后再更新窗口内字符的数量
                    window.put(d, window.getOrDefault(d, 0) - 1);
                }
            }
        }
        return false;
    }
}
