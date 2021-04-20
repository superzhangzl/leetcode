package zzl.leetcode;

import zzl.base.annotation.Level;
import zzl.util.SpecialAssertUtil;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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
    }

    /**
     * 常规的滑动窗口，每次比较滑动窗口内的值的是否匹配
     * TODO：每次判断会导致时间复杂度多O(len(26))，需要更巧妙的办法降低时间复杂度
     *
     * @param s
     * @param p
     * @return
     */
    public List<Integer> findAnagrams(String s, String p) {
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
