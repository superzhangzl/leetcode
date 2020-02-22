package zzl.leetcode;

import java.math.BigDecimal;

/**
 * @author zzl
 * @link {https://leetcode-cn.com/problems/multiply-strings/}
 */
public class MultiplyStrings {
    public static void main(String[] args) {
        System.out.println(multiply("1", "2"));
    }

    public static String multiply(String num1, String num2) {
        BigDecimal n1 = new BigDecimal(num1);
        BigDecimal n2 = new BigDecimal(num2);
        return n1.multiply(n2).toString();
    }
}
