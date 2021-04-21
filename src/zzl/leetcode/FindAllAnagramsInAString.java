package zzl.leetcode;

import zzl.base.annotation.Level;
import zzl.util.SpecialAssertUtil;

import java.util.*;

import static zzl.base.enums.Difficulty.MEDIUM;

/**
 * 找到字符串中所有字母异位词
 *
 * @author zzl
 * @date 2021-04-20
 * @link {https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/}
 */
@Level(MEDIUM)
public class FindAllAnagramsInAString {
    public static void main(String[] args) {
        List<Integer> anagrams;
        //
        anagrams = new FindAllAnagramsInAString().findAnagrams("cbaebabacd", "abc");
        SpecialAssertUtil.assertIntListContain(anagrams, Arrays.asList(0, 6));
        //
        anagrams = new FindAllAnagramsInAString().findAnagrams("abab", "ab");
        SpecialAssertUtil.assertIntListContain(anagrams, Arrays.asList(0, 1, 2));
        //
        anagrams = new FindAllAnagramsInAString().findAnagrams("baa", "aa");
        SpecialAssertUtil.assertIntListContain(anagrams, Arrays.asList(1));
    }

    /**
     * 滑动窗口
     *
     * @param s
     * @param p
     * @return
     * @see PermutationInString#checkInclusion(String, String)
     */
    public List<Integer> findAnagrams(String s, String p) {
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        for (char c : p.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0) + 1);
        }
        int left = 0;
        int right = 0;
        int valid = 0;
        List<Integer> res = new LinkedList<>();
        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (need.get(c).equals(window.get(c))) {
                    valid++;
                }
            }
            while (right - left >= p.length()) {
                // 判断合法
                if (valid == need.size()) {
                    res.add(left);
                }
                char d = s.charAt(left);
                left++;
                if (need.containsKey(d)) {
                    if (need.get(d).equals(window.get(d))) {
                        valid--;
                    }
                    window.put(d, window.getOrDefault(d, 0) - 1);
                }
            }
        }
        return res;
    }

    /**
     * 常规的滑动窗口，每次比较滑动窗口内的值的是否匹配
     * 时间复杂度较高
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams_bad(String s, String p) {
        int leftIndex = 0;
        int rightIndex = p.length();
        List<Integer> res = new LinkedList<>();
        while (rightIndex <= s.length()) {
            String substring = s.substring(leftIndex, rightIndex);
            if (checkAnagrams(substring, p)) {
                res.add(leftIndex);
            }
            leftIndex++;
            rightIndex++;
        }
        return res;
    }

    private boolean checkAnagrams(String sub, String p) {
        int[] tmp = new int[26];
        for (char c : p.toCharArray()) {
            tmp[c - 'a']++;
        }
        for (char c : sub.toCharArray()) {
            tmp[c - 'a']--;
        }
        for (int i : tmp) {
            if (i > 0) {
                return false;
            }
        }
        return true;
    }
}
