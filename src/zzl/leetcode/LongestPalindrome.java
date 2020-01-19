package zzl.leetcode;

/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * <p>
 * 示例 1：
 * <p>
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 * <p>
 * 输入: "cbbd"
 * 输出: "bb"
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/longest-palindromic-substring}
 */
public class LongestPalindrome {

    public static void main(String[] args) {
        System.out.println(longestPalindrome("babad"));
        System.out.println(longestPalindrome("cbbd"));
        System.out.println(longestPalindrome("c"));
        System.out.println(longestPalindrome(""));
        System.out.println(longestPalindrome(null));
        System.out.println(longestPalindrome("null"));
        System.out.println(longestPalindrome("abcba"));
        System.out.println(longestPalindrome("abc"));
        System.out.println(longestPalindrome("ab"));
        System.out.println(longestPalindrome("aa"));
        System.out.println(longestPalindrome("aaa"));
        System.out.println(longestPalindrome("aba"));
        System.out.println(longestPalindrome("aab"));
        System.out.println(longestPalindrome("baa"));
        System.out.println(longestPalindrome("aabc"));
    }

    // todo 待优化，看到一个和我一样的算法，但是把字符串转成了数组来提高计算速度
    public static String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        // 长度为1直接返回
        if (s.length() == 1) {
            return s;
        }
        int right = 0;
        int left = 0;
        int maxLength = 0;
        // 思路：从当前位置开始，向两边同时遍历判断
        for (int i = 0; i < s.length(); i++) {
            char middleChar = s.charAt(i);
            print(i);
            print(middleChar);
            // 判断回文序列为奇数的情况
            int leftIndex = i;
            int rightIndex = i;
            while (true) {
                if ((leftIndex >= 0) && (rightIndex < s.length()) && s.charAt(leftIndex) == s.charAt(rightIndex)) {
                    print("current: " + s.substring(leftIndex, rightIndex + 1));
                    rightIndex++;
                    leftIndex--;
                } else {
                    break;
                }
            }
            int length = (rightIndex - 1) - (leftIndex + 1) + 1;
            if (length > maxLength) {
                if (length == s.length()) {
                    return s;
                }
                maxLength = length;
                left = leftIndex + 1;
                right = rightIndex - 1;
                print("max length: " + length);
                print("left, right: " + left + ", " + right);
                print("max str: " + s.substring(left, right + 1));
            }
            // 判断回文序列为偶数的情况，区别就是右游标多移动一位而已
            leftIndex = i;
            rightIndex = i + 1;
            while (true) {
                if ((leftIndex >= 0) && (rightIndex < s.length()) && s.charAt(leftIndex) == s.charAt(rightIndex)) {
                    print("current: " + s.substring(leftIndex, rightIndex + 1));
                    rightIndex++;
                    leftIndex--;
                } else {
                    break;
                }
            }
            length = (rightIndex - 1) - (leftIndex + 1) + 1;
            if (length > maxLength) {
                if (length == s.length()) {
                    return s;
                }
                maxLength = length;
                left = leftIndex + 1;
                right = rightIndex - 1;
                print("max length: " + length);
                print("left, right: " + left + ", " + right);
                print("max str: " + s.substring(left, right + 1));
            }
            print("============================");
        }
        return s.substring(left, right + 1);
    }

    private static void print(Object msg) {
//        System.out.println(msg);
    }
}
