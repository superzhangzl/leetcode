package zzl.leetcode;

import java.util.*;

/**
 * 为运算表达式设计优先级
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/different-ways-to-add-parentheses/}
 */
public class DifferentWaysToAddParentheses {
    public static void main(String[] args) {
        /*
        2-1-1
        [0, 2]
        ((2-1)-1) = 0
        (2-(1-1)) = 2
         */
        System.out.println(new DifferentWaysToAddParentheses().diffWaysToCompute("2-1-1"));
        /*
        2*3-4*5
        输出: [-34, -14, -10, -10, 10]
        解释:
        (2*(3-(4*5))) = -34
        ((2*3)-(4*5)) = -14
        ((2*(3-4))*5) = -10
        (2*((3-4)*5)) = -10
        (((2*3)-4)*5) = 10
         */
        System.out.println(new DifferentWaysToAddParentheses().diffWaysToCompute("2*3-4*5"));
    }

    Map<String, List<Integer>> map = new HashMap<>();

    /**
     * 解题思路
     * 1.按照运算符做分割，然后用分治算法解。
     * 2.边界条件为：如果剩下的的字符串中没有运算符，即剩下的字符串中有且仅有数字。
     *
     * @param input
     * @return
     * @link {https://blog.csdn.net/w8253497062015/article/details/80732687}
     */
    public List<Integer> diffWaysToCompute(String input) {
        if (map.containsKey(input)) {
            return map.get(input);
        }
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                List<Integer> res1 = diffWaysToCompute(input.substring(0, i));
                List<Integer> res2 = diffWaysToCompute(input.substring(i + 1));
                System.out.println(res1.toString() + c + res2);
                for (int r1 : res1)
                    for (int r2 : res2) {
                        if (c == '+')
                            result.add(r1 + r2);
                        if (c == '-')
                            result.add(r1 - r2);
                        if (c == '*')
                            result.add(r1 * r2);
                    }
            }
        }
        if (result.isEmpty())
            result.add(Integer.parseInt(input));
        if (!map.containsKey(input)) {
            map.put(input, result);
        }
        return result;
    }

    /*
        List<String> numbers = new ArrayList<>();
        List<String> operations = new ArrayList<>();
        String[] operation = input.split("[0-9]+");
        for (int i = 1; i < operation.length; i++) {
            operations.add(operation[i]);
        }
        String[] number = input.split("[\\+\\-\\*\\/]+");
        for (int i = 0; i < number.length; i++) {
            numbers.add(number[i]);
        }
     */
}
