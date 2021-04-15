package zzl.leetcode;

import zzl.util.SpecialAssertUtil;

import java.util.*;

/**
 * 删除无效的括号
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/remove-invalid-parentheses/}
 */
public class RemoveInvalidParentheses {
    public static void main(String[] args) {
        String s;
        List<String> parentheses;
        //
        s = "()())()";
        parentheses = new RemoveInvalidParentheses().removeInvalidParentheses(s);
        SpecialAssertUtil.assertStringListContain(parentheses, Arrays.asList("()()()", "(())()"));
        //
        s = "(a)())()";
        parentheses = new RemoveInvalidParentheses().removeInvalidParentheses(s);
        SpecialAssertUtil.assertStringListContain(parentheses, Arrays.asList("(a())()", "(a)()()"));

    }

    int len;
    char[] charArray;
    private Set<String> set = new HashSet<>();

    /**
     * @param s
     * @return
     * @link {https://leetcode-cn.com/problems/remove-invalid-parentheses/solution/shan-chu-wu-xiao-de-gua-hao-by-leetcode/}
     */
    public List<String> removeInvalidParentheses(String s) {
        this.len = s.length();
        this.charArray = s.toCharArray();
        // 需要移除的左括号的数量
        int leftRemove = 0;
        // 需要移除的右括号的数量
        int rightRemove = 0;

        for (char c : charArray) {
            if (c == '(') {
                leftRemove++;
            } else if (c == ')') {
                if (leftRemove == 0) {
                    // 判断左括号的数量，如果左括号的数量为0，那就是需要移除右括号了
                    rightRemove++;
                }
                if (leftRemove > 0) {
                    // 如果左括号的数量大于0，那一个右括号刚好抵消一个左括号
                    leftRemove--;
                }
            }
        }
        StringBuilder path = new StringBuilder();
        dfs(0, 0, 0, leftRemove, rightRemove, path);
        return new ArrayList<>(set);
    }

    /**
     * @param index       当前遍历到的下标
     * @param leftCount   已经遍历到的左括号的个数
     * @param rightCount  已经遍历到的右括号的个数
     * @param leftRemove  最少应该删除的左括号的个数
     * @param rightRemove 最少应该删除的右括号的个数
     * @param path        一个可能的结果
     */
    private void dfs(int index, int leftCount, int rightCount, int leftRemove, int rightRemove, StringBuilder path) {
        // dfs结束条件
        if (index == len) {
            // 如果两个值都等于0，代表左右括号是匹配的
            if (leftRemove == 0 && rightRemove == 0) {
                set.add(path.toString());
            }
            return;
        }
        char c = charArray[index];
        if (c == '(' && leftRemove > 0) {
            // 删除当前的左括号，不让它插入到path中
            dfs(index + 1, leftCount, rightCount, leftRemove - 1, rightRemove, path);
        }
        if (c == ')' && rightRemove > 0) {
            // 删除当前的右括号，不让它插入到path中
            dfs(index + 1, leftCount, rightCount, leftRemove, rightRemove - 1, path);
        }
        path.append(c);
        // 不是括号，直接继续操作
        if (c != '(' && c != ')') {
            dfs(index + 1, leftCount, rightCount, leftRemove, rightRemove, path);
        } else if (c == '(') {
            dfs(index + 1, leftCount + 1, rightCount, leftRemove, rightRemove, path);
        } else if (rightCount < leftCount) {
            // 右括号数量小于左括号数量的时候才添加右括号，即右括号必须匹配一个对应的左括号，不然格式不匹配
            dfs(index + 1, leftCount, rightCount + 1, leftRemove, rightRemove, path);
        }
        path.deleteCharAt(path.length() - 1);
    }
}
