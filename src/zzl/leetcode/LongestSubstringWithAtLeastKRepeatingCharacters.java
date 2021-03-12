package zzl.leetcode;

import org.junit.Assert;

import java.util.HashMap;

/**
 * 至少有 K 个重复字符的最长子串
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/longest-substring-with-at-least-k-repeating-characters/}
 */
public class LongestSubstringWithAtLeastKRepeatingCharacters {
    public static void main(String[] args) {
        int longestSubstring;

        //
        longestSubstring = new LongestSubstringWithAtLeastKRepeatingCharacters().longestSubstring("aaabb", 3);
        Assert.assertEquals(longestSubstring, 3);

        //
        longestSubstring = new LongestSubstringWithAtLeastKRepeatingCharacters().longestSubstring("ababbc", 2);
        Assert.assertEquals(longestSubstring, 5);

        // 考虑如下情况，s=aaabbbb, k=2的情况，要理解题意，aaabbbb属于子串且其中的每一个字符的连续长度都大于2
        // 而不是子串里只有单一的字符，所以长度是7的
        longestSubstring = new LongestSubstringWithAtLeastKRepeatingCharacters().longestSubstring("aaabbbb", 2);
        Assert.assertEquals(longestSubstring, 7);

    }

    /**
     * 分治：
     * 最重要的是拆分子问题，所以需要一个拆分点，本题中的是“连续重复字符的个数小于k的字符”
     * 所以拆分后的子串还是同样满足上述条件，直到拆分到最小条件
     *
     * @param s
     * @param k
     * @return
     * @link {https://leetcode-cn.com/problems/longest-substring-with-at-least-k-repeating-characters/solution/jie-ben-ti-bang-zhu-da-jia-li-jie-di-gui-obla/}
     */
    public int longestSubstring(String s, int k) {
        // 对子串进行剪枝，s的长度小于k直接退出
        if (s.length() < k) return 0;
        HashMap<Character, Integer> counter = new HashMap();
        // 统计当前子串字符的长度
        for (int i = 0; i < s.length(); i++) {
            counter.put(s.charAt(i), counter.getOrDefault(s.charAt(i), 0) + 1);
        }
        // 对当前已有字符进行遍历
        for (char c : counter.keySet()) {
            // 从出现次数小于k的字符开始分割，因为该字符肯定不会出现在答案里
            if (counter.get(c) < k) {
                int res = 0;
                // 被不满足条件的字符分割的结果
                for (String t : s.split(String.valueOf(c))) {
                    // 当前子串不一定是最小子串，再递归走一轮判断
                    res = Math.max(res, longestSubstring(t, k));
                }
                return res;
            }
        }
        // 这种情况下，for循环肯定就走完了，那直接返回当前子串的长度
        return s.length();
    }
}
