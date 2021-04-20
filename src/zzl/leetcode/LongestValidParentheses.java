package zzl.leetcode;

import org.junit.Assert;
import zzl.base.annotation.Level;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

import static zzl.base.enums.Difficulty.*;

/**
 * 最长有效括号
 *
 * @author zzl
 * @date 2021-04-20
 * @link {https://leetcode-cn.com/problems/longest-valid-parentheses/}
 */
@Level(HARD)
public class LongestValidParentheses {
    public static void main(String[] args) {
        int longestValidParentheses;
        //
        longestValidParentheses = new LongestValidParentheses().longestValidParentheses("(()");
        Assert.assertEquals(longestValidParentheses, 2);
        //
        longestValidParentheses = new LongestValidParentheses().longestValidParentheses(")()())");
        Assert.assertEquals(longestValidParentheses, 4);
        //
        longestValidParentheses = new LongestValidParentheses().longestValidParentheses("");
        Assert.assertEquals(longestValidParentheses, 0);
    }

    /**
     * 最长有效括号
     *
     * @param s
     * @return
     * @link {https://leetcode-cn.com/problems/longest-valid-parentheses/solution/zui-chang-you-xiao-gua-hao-by-leetcode-solution/}
     */
    public int longestValidParentheses(String s) {
        int res = 0;
        /*
            栈顶保存最后一个没有被匹配的右括号的下标
         */
        Deque<Integer> stack = new LinkedList<>();
        // 为了保证边界的完整性，插入一个虚拟下标
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            // 如果是左括号，那就把它的下标入进去，可能会有一个对应的右括号来抵消
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                // 弹出一个，有可能是对应的左括号的下标，也有可能是之前传入的右括号的下标
                stack.pop();
                if (stack.isEmpty()) {
                    // 传入符合堆栈定义的右括号的下标，如果里面有之前的，要么被弹出，要么被压在下面了
                    stack.push(i);
                } else {
                    // 如果不为空，说明对应的左括号被弹出，那从当前栈顶向右的都是合法的括号
                    res = Math.max(res, i - stack.peek());
                }
            }
        }
        return res;
    }
}
