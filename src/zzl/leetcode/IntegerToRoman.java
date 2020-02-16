package zzl.leetcode;

import org.junit.Assert;

/**
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/integer-to-roman/}
 */
public class IntegerToRoman {
    public static void main(String[] args) {
        Assert.assertEquals(intToRoman(3), "III");
        Assert.assertEquals(intToRoman(4), "IV");
        Assert.assertEquals(intToRoman(58), "LVIII");
        Assert.assertEquals(intToRoman(1994), "MCMXCIV");
    }

    /**
     * 由于题目限制了数据范围，可以直接通过十进制暴力穷举
     * 当数据量上升后，可以通过预先构造数据表再进行循环取模操作
     *
     * @param num
     * @return
     */
    public static String intToRoman(int num) {
        String[] Q = new String[]{"", "M", "MM", "MMM"};
        String[] B = new String[]{"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] S = new String[]{"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] G = new String[]{"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return Q[(int) Math.floor(num / 1000)] + B[(int) Math.floor((num % 1000) / 100)] + S[(int) Math.floor((num % 100) / 10)] + G[num % 10];

    }
}
