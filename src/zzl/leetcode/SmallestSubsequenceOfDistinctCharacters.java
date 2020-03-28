package zzl.leetcode;

import org.junit.Assert;

import java.util.Stack;

/**
 * 不同字符的最小子序列
 * 返回字符串 text 中按字典序排列最小的子序列，该子序列包含 text 中所有不同字符一次。
 * 1 <= text.length <= 1000
 * text 由小写英文字母组成
 *
 * 相似题型：https://leetcode-cn.com/problems/remove-duplicate-letters/
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/smallest-subsequence-of-distinct-characters/}
 * @tag 1081 316
 */
public class SmallestSubsequenceOfDistinctCharacters {
    public static void main(String[] args) {
        String input = "cdadabcc";
        Assert.assertEquals(new SmallestSubsequenceOfDistinctCharacters().smallestSubsequence(input), "adbc");
        input = "abcd";
        Assert.assertEquals(new SmallestSubsequenceOfDistinctCharacters().smallestSubsequence(input), "abcd");

        input = "ecbacba";
        Assert.assertEquals(new SmallestSubsequenceOfDistinctCharacters().smallestSubsequence(input), "eacb");

        input = "leetcode";
        Assert.assertEquals(new SmallestSubsequenceOfDistinctCharacters().smallestSubsequence(input), "letcod");
    }

    /**
     * 那如果那些字典序靠前的字符出现得比较晚该怎么办呢？此时就要看，已经出现过的字符将来还有没有可能出现，如果将来有可能出现，就把前面的字符依次删去，
     * 经过这样的流程，得到的子序列就符合题意，这是 贪心算法 的思想，局部最优则全局最优。
     *
     * @param text
     * @return
     * @link {https://leetcode-cn.com/problems/smallest-subsequence-of-distinct-characters/solution/tan-xin-suan-fa-zhan-wei-yan-ma-python-dai-ma-java/}
     */
    public String smallestSubsequence(String text) {
        System.out.println(text);
        int length = text.length();
        int index = 0;
        Stack<Character> stack = new Stack<>();
        while (index < length) {
            char c = text.charAt(index++);
            if (stack.contains(c)) {
                continue;
            }
            // stack不是空，且栈顶字符字典序大于当前字符，且栈顶字符在当前位置往后还是存在的，就把弹出去
            // 反正等会儿还有新的会放进来
            while (!stack.isEmpty() && c < stack.peek() && text.indexOf(stack.peek(), index) != -1) {
                stack.pop();
            }
            stack.push(c);
        }
        System.out.println(stack);
        StringBuilder sb = new StringBuilder();
        for (Character character : stack) {
            sb.append(character);
        }
        return sb.toString();
    }
}
