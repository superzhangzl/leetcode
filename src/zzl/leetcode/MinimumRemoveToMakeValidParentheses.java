package zzl.leetcode;

import org.junit.Assert;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MinimumRemoveToMakeValidParentheses {
    public static void main(String[] args) {
        MinimumRemoveToMakeValidParentheses task = new MinimumRemoveToMakeValidParentheses();

        String str = "lee(t(c)o)de)";
        String result = task.minRemoveToMakeValid(str);
        Assert.assertTrue("lee(t(co)de)".equals(result) || "lee(t(c)ode)".equals(result) || "lee(t(c)o)de".equals(result));
        //
        str = "a)b(c)d";
        result = task.minRemoveToMakeValid(str);
        Assert.assertEquals(result, "ab(c)d");
        //
        str = "))((";
        result = task.minRemoveToMakeValid(str);
        Assert.assertEquals(result, "");
        //
        str = "(a(b(c)d)";
        result = task.minRemoveToMakeValid(str);
        Assert.assertTrue("a(b(c)d)".equals(result) || "(ab(c)d)".equals(result) || "(a(bc)d)".equals(result));
        //
        str = "())()(((";
        result = task.minRemoveToMakeValid(str);
        Assert.assertEquals(result, "()()");
        //
        str = ")i()()((fm(((()";
        result = task.minRemoveToMakeValid(str);
        Assert.assertEquals(result, "i()()fm()");
    }

    /**
     * @param s
     * @return
     */
    public String minRemoveToMakeValid(String s) {
        Boolean[] closed = new Boolean[s.length()];
        Arrays.fill(closed, false);
        Stack<Integer> leftIndex = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 'a' && c <= 'z') {
                closed[i] = true;
            }
            if (c == '(') {
                leftIndex.push(i);
            }
            if (c == ')') {
                if (!leftIndex.isEmpty()) {
                    Integer pop = leftIndex.pop();
                    closed[pop] = true;
                    closed[i] = true;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (closed[i]) {
                sb.append(s.charAt(i));
            }

        }
        return sb.toString();
    }
}
