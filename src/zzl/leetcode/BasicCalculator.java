package zzl.leetcode;

import org.junit.Assert;

import java.util.Stack;

/**
 * 基本计算器
 * 字符串表达式可以包含左括号 ( ，右括号 )，加号 + ，减号 -，非负整数和空格  。
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/basic-calculator/}
 */
public class BasicCalculator {
    public static void main(String[] args) {
        Assert.assertEquals(new BasicCalculator().calculate("0"), 0);
        Assert.assertEquals(new BasicCalculator().calculate("1 + 1"), 2);
        Assert.assertEquals(new BasicCalculator().calculate(" 2-1 + 2 "), 3);
        Assert.assertEquals(new BasicCalculator().calculate("(1+(4+5+2)-3)+(6+8)"), 23);
        Assert.assertEquals(new BasicCalculator().calculate("2-(5-6)"), 3);
        Assert.assertEquals(new BasicCalculator().calculate("(6)-(8)-(7)+(1+(6))"), -2);
    }

    /**
     * 官方的解题示例，按照调用分析了一遍逻辑
     * 步骤可以看下面的链接，核心就是有两个：
     * 1. 累计操作数，因为位数可能大于1
     * 2. 使用标志位来表示下一位需要参与计算的数值的正负
     *
     * @param s
     * @return
     * @link {https://leetcode-cn.com/problems/basic-calculator/solution/ji-ben-ji-suan-qi-by-leetcode/}
     */
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int operand = 0; // 用于计算连续数字，有可能是大于一位数，初始化为0
        int result = 0;  // 累加的数
        int sign = 1;    // +1表示加，-1表示减，这样可以将表达式进行简化，所有的都变成+，通过这个标志位控制正负，eg:A-B => A + B * (-1)

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                // 重要：构建操作数，因为它可以超过一位
                operand = 10 * operand + (ch - '0');
            } else if (ch == '+') {
                // 根据操作数和正负号标志位 计算到当前为止的结果，不包含当前操作符后的数字
                result += sign * operand;
                // 设置标识符为+1
                sign = 1;
                // 初始化操作数
                operand = 0;
            } else if (ch == '-') {
                // 同 '+'
                result += sign * operand;
                // 更新标识符为 '-1'
                sign = -1;
                operand = 0;
            } else if (ch == '(') {
                // 当遇到'('时，将当前的结果和标识符放入堆栈，先计算括号内的结果
                // 即A+(B-C)+D => A+E+D
                stack.push(result);
                stack.push(sign);

                // 初始化表达式
                sign = 1;
                result = 0;
            } else if (ch == ')') {
                // 当遇到匹配的左括号时，先计算一下括号内的数据
                result += sign * operand;
                // ')'标记一组括号内表达式的结尾，其结果与堆栈顶部的符号相乘，因为stack.pop()是括号前的符号
                // 即：更新括号内表达式的正负
                result *= stack.pop();
                // 然后累加到顶部的下一个操作数。 因为stack.pop()是此括号之前的结果
                // (堆栈上的操作数)+(堆栈上的符号*(括号中的结果))
                // 即：更新括号内的数据和栈顶(括号外)的结果
                result += stack.pop();
                // 重置操作数
                operand = 0;
            }
        }
        // 最后重新计算一下，到最后一个时可能是个数字，他后面没有操作符导致该数字没有参与计算，就在返回的时候把他计算进去
        return result + (sign * operand);
    }

    /**
     * 下面的解法是leetcode上耗时最多的解法之一
     */
    char[] cs;
    int index = 0;

    public int calculate2(String s) {
        cs = s.toCharArray();
        return help();
    }

    int help() {
        int sign = 1;
        int num = 0;
        int res = 0;
        while (index < cs.length) {
            char ch = cs[index++];
            if (ch == ' ') continue;
            if (ch >= '0' && ch <= '9') num = num * 10 + ch - '0';
            else if (ch == '(') {
                // 核心和官方示例中的一致，就是将当前索引保存为全局变量，不再使用stack额外保存操作符和括号前的数据
                num = help();
            } else if (ch == ')') {
                break;
            } else {
                res += num * sign;
                num = 0;
                sign = ch == '+' ? 1 : -1;
            }
        }
        res += num * sign;
        return res;
    }
}
