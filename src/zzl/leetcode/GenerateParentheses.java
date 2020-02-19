package zzl.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzl
 * @link {https://leetcode-cn.com/problems/generate-parentheses/}
 */
public class GenerateParentheses {
    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
        System.out.println(generateParenthesis(2));
    }

    private static List<String> generateParent;

    public static List<String> generateParenthesis(int n) {
        generateParent = new ArrayList<>();
        recursion(n, n, "");
        return generateParent;
    }

    /**
     * 思路很简单，只要‘ ( ’的剩余数量 left <= right ' ) '的剩余数量
     * 换言之，就是先放‘ ( ’,再放‘ ) ’
     * 如果 left < right ， 可以放‘ ( ’,也可以放‘ ) ’ 注意left,right 保证大于0
     * 如果 left == right， 只能放左括号
     * 不会有left > right的情况
     * <p>
     *
     * @param leftNeedAddCount
     * @param rightNeedAddCount
     * @param current
     * @link {https://leetcode-cn.com/problems/generate-parentheses/solution/python3-di-gui-zhi-xing-yong-shi-32-ms-zai-suo-you/}
     */
    private static void recursion(int leftNeedAddCount, int rightNeedAddCount, String current) {
        System.out.println(String.format("[%s], (%d, %d)", current, leftNeedAddCount, rightNeedAddCount));
        if (leftNeedAddCount == rightNeedAddCount && leftNeedAddCount == 0) {
            generateParent.add(current);
        } else {
            if (leftNeedAddCount < rightNeedAddCount) {
                if (leftNeedAddCount > 0) {
                    recursion(leftNeedAddCount - 1, rightNeedAddCount, current + '(');
                }
                if (rightNeedAddCount > 0) {
                    recursion(leftNeedAddCount, rightNeedAddCount - 1, current + ')');
                }
            } else {
                // 起始位置
                recursion(leftNeedAddCount - 1, rightNeedAddCount, current + '(');
            }
        }
    }

    /**
     * 官方1
     *
     * @param n
     * @return
     */
    public static List<String> generateParenthesis1(int n) {
        // 中间数的个数
        List<String> result = new ArrayList<>();
        if (n == 0) {
            result.add("");
        } else {
            for (int c = 0; c < n; ++c)
                for (String left : generateParenthesis1(c))
                    for (String right : generateParenthesis1(n - 1 - c))
                        result.add("(" + left + ")" + right);
        }
        return result;
    }
}
