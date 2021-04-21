package zzl.leetcode;

import org.junit.Assert;
import zzl.base.annotation.Level;

import java.util.Collections;
import java.util.LinkedList;

import static zzl.base.enums.Difficulty.MEDIUM;

/**
 * 字符串解码
 *
 * @author zzl
 * @date 2021-04-21
 * @link {https://leetcode-cn.com/problems/decode-string/}
 */
@Level(MEDIUM)
public class DecodeString {
    public static void main(String[] args) {
        String decodeString;
        //
        decodeString = new DecodeString().decodeString("3[a]2[bc]");
        Assert.assertEquals(decodeString, "aaabcbc");
        //
        decodeString = new DecodeString().decodeString("3[a2[c]]");
        Assert.assertEquals(decodeString, "accaccacc");
        //
        decodeString = new DecodeString().decodeString("2[abc]3[cd]ef");
        Assert.assertEquals(decodeString, "abcabccdcdcdef");
        //
        decodeString = new DecodeString().decodeString("abc3[cd]xyz");
        Assert.assertEquals(decodeString, "abccdcdcdxyz");
        //
        decodeString = new DecodeString().decodeString("100[leetcode]");
        Assert.assertEquals(decodeString, "leetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcodeleetcode");
    }

    int sIndex;

    /**
     * 思路：
     * 1. 如果当前的字符为数位，解析出一个数字（连续的多个数位）并进栈
     * 2. 如果当前的字符为字母或者左括号，直接进栈
     * 3. 如果当前的字符为右括号，开始出栈，一直到左括号出栈，出栈序列反转后拼接成一个字符串，此时取出栈顶的数字（此时栈顶一定是数字，想想为什么？），
     * 就是这个字符串应该出现的次数，我们根据这个次数和字符串构造出新的字符串并进栈
     *
     * @param s
     * @return
     * @link {https://leetcode-cn.com/problems/decode-string/solution/zi-fu-chuan-jie-ma-by-leetcode-solution/}
     */
    public String decodeString(String s) {
        LinkedList<String> stack = new LinkedList<>();
        sIndex = 0;
        while (sIndex < s.length()) {
            char cur = s.charAt(sIndex);
            if (Character.isDigit(cur)) {
                // 注：不能直接使用String.valueOf(cur)，因为前缀的数字可能大于一位数，用这种方法就会导致字符重复次数错误
                String digit = getDigits(s);
                System.out.println("digit: " + digit);
                stack.push(digit);
                // 且此处不用再 sIndex++;，因为在获取前缀数字时候已经自增过了
            } else if (cur == '[' || Character.isLetter(cur)) {
                stack.push(String.valueOf(cur));
                sIndex++;
            } else {
                // ++后移动到]的下一位起始位置
                // 且']'不入栈
                sIndex++;
                LinkedList<String> list = new LinkedList<>();
                while (!"[".equals(stack.peek())) {
                    // 堆栈是倒叙的，所以入到first
                    list.addFirst(stack.pop());
                }
                stack.pop();// '['
                int repeatCnt = Integer.parseInt(stack.pop());
                String baseStr = String.join("", list);
                System.out.println("baseStr: " + baseStr);
                String build = build(repeatCnt, baseStr);
                stack.push(build);
            }
        }
        Collections.reverse(stack);
        return String.join("", stack);
    }

    private String build(int repeatCnt, String baseStr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < repeatCnt; i++) {
            sb.append(baseStr);
        }
        return sb.toString();
    }

    public String getDigits(String s) {
        StringBuffer ret = new StringBuffer();
        while (Character.isDigit(s.charAt(sIndex))) {
            ret.append(s.charAt(sIndex++));
        }
        return ret.toString();
    }

}
