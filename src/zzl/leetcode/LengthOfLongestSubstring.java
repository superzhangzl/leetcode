package zzl.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 示例 1:
 * <p>
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * <p>
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/longest-substring-without-repeating-characters}
 */
public class LengthOfLongestSubstring {
    public static void main(String[] args) {
        // should using assert
        System.out.println(lengthOfLongestSubstring("abcabcbb")); // 3
        System.out.println(lengthOfLongestSubstring("bbbbb")); // 1
        System.out.println(lengthOfLongestSubstring("pwwkew")); // 3
        System.out.println(lengthOfLongestSubstring("pwkea")); // 5
        System.out.println(lengthOfLongestSubstring("")); // 0
        System.out.println(lengthOfLongestSubstring(null)); // 0
        System.out.println(lengthOfLongestSubstring("abc")); //3
        System.out.println(lengthOfLongestSubstring(" ")); //1
        System.out.println(lengthOfLongestSubstring("abba")); //2
        System.out.println(lengthOfLongestSubstring("tmmzuxt")); //5
        System.out.println(lengthOfLongestSubstring("aabaab!bb")); //3
        System.out.println(lengthOfLongestSubstring("dvdf")); //3
        System.out.println(lengthOfLongestSubstring("au")); //2
    }

    /**
     * 使用数组代替Map，可以减少初始化等运行时间
     * <p>
     * 常规思路：从当前下标开始向结尾遍历，判断不符合条件就中断，时间复杂度为O(n^2)
     * <p>
     * 思路二：使用两个游标分别存储最大不重复子串的起始位置和结束位置，通过遍历字符串来动态更新起始下标和结束下标的值
     *        通过使用空间换时间（使用数据结构存储最新出现的字符所在的下标），时间复杂度为O(n)
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        // 存储字符串及其下标，
        int[] characterWithIndex = new int[128];
        // 初始化为-1，因为数组下标内存储的是字符串的索引，不能使用默认为0的情况，会影响判断
        for (int i = 0; i < characterWithIndex.length; i++) {
            characterWithIndex[i] = -1;
        }
        // 起始游标
        int firstIndex = 0;
        // 结束游标
        int endIndex = 0;
        int maxLength = 0;
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            // 如果存在当前字符，说明从这个位置开始重复了，则将起始游标设置为重复字符第一次出现位置的下一个
            if (characterWithIndex[c] != -1 && characterWithIndex[c] >= firstIndex) {
                // 将firstIndex设置为该字符第一次出现的下一个位置
                firstIndex = characterWithIndex[c] + 1 > firstIndex ? characterWithIndex[c] + 1 : firstIndex;
                int length = endIndex - firstIndex;
                if (maxLength < length) {
                    maxLength = length;
                    print("max(" + i + ") : " + maxLength);
                }
                // 结束游标页也为当前字符的下一个，而不是累加
                endIndex = i + 1;
                // 更新map中字符的当前的位置
                characterWithIndex[c] = i;
            } else {
                characterWithIndex[c] = i;
                endIndex++;
                int length = endIndex - firstIndex;
                if (maxLength < length) {
                    maxLength = length;
                    print("max(" + i + ") : " + maxLength);
                }
            }

            print("current char: " + c + " -> " + i);
            print("index: (" + firstIndex + ", " + endIndex + ")");
            String substring = s.substring(firstIndex, endIndex);
            print(substring);
            print(characterWithIndex.toString());
            print("++++++++++++++++++++++++++");
        }
        // 如果maxLength 的长度和字符串的长度一致，则按照要求减1即可，否则就返回maxLength
        System.out.println("============================================================");
        return maxLength;
    }

    private static void print(String msg) {
//        System.out.println(msg);
    }

    // 原始思路，使用map来存储下标
    public int lengthOfLongestSubstring1(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        // 存储字符串及其下标，
        Map<Character, Integer> characterWithIndex = new HashMap<>();
        // 起始游标
        int firstIndex = 0;
        // 结束游标
        int endIndex = 0;
        int maxLength = 0;
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            // 如果存在当前字符，说明从这个位置开始重复了，则将起始游标设置为重复字符第一次出现位置的下一个
            // 且当前下标应大于等于初始下标，即初始下标之前的字串已经无效了
            if (characterWithIndex.containsKey(c) && characterWithIndex.get(c) >= firstIndex) {
                // 将firstIndex设置为该字符第一次出现的下一个位置
                firstIndex = characterWithIndex.get(c) + 1 > firstIndex ? characterWithIndex.get(c) + 1 : firstIndex;
                int length = endIndex - firstIndex;
                if (maxLength < length) {
                    maxLength = length;
                }
                // 结束游标页也为当前字符的下一个，而不是累加
                endIndex = i + 1;
                // 更新map中字符的当前的位置
                characterWithIndex.put(c, i);
            } else {
                characterWithIndex.put(c, i);
                endIndex++;
                int length = endIndex - firstIndex;
                if (maxLength < length) {
                    maxLength = length;
                }
            }
        }
        // 如果maxLength 的长度和字符串的长度一致，则按照要求减1即可，否则就返回maxLength
        return maxLength;
    }

}
