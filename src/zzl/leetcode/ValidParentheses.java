package zzl.leetcode;

import org.junit.Assert;

import java.util.Stack;

/**
 * 有效的括号
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/valid-parentheses/}
 */
public class ValidParentheses {
    public static void main(String[] args) {
        System.out.println("() = " + (int) '(' + ", " + (int) ')');
        System.out.println("[] = " + (int) '[' + ", " + (int) ']');
        System.out.println("{} = " + (int) '{' + ", " + (int) '}');
        Assert.assertEquals(new ValidParentheses().isValid("()"), true);
        Assert.assertEquals(new ValidParentheses().isValid("()[]{}"), true);
        Assert.assertEquals(new ValidParentheses().isValid("(]"), false);
        Assert.assertEquals(new ValidParentheses().isValid("([)]"), false);
        Assert.assertEquals(new ValidParentheses().isValid("{[]}"), true);
        Assert.assertEquals(new ValidParentheses().isValid("{"), false);
        Assert.assertEquals(new ValidParentheses().isValid("}"), false);
        Assert.assertEquals(new ValidParentheses().isValid(""), true);
    }

    public boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                Character pop = stack.pop();
                if (c == ')')
                    if (pop != '(') return false;
                if (c == ']')
                    if (pop != '[') return false;
                if (c == '}')
                    if (pop != '{') return false;
            }
        }
        return stack.isEmpty();
    }
}
