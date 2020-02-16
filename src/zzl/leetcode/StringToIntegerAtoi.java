package zzl.leetcode;

import org.junit.Assert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * * @author zzl
 * * @link {https://leetcode-cn.com/problems/string-to-integer-atoi/}
 */
public class StringToIntegerAtoi {
    public static void main(String[] args) {
        Assert.assertEquals(myAtoi("42"), 42);
        Assert.assertEquals(myAtoi("   -42"), -42);
        Assert.assertEquals(myAtoi("4193 with words"), 4193);
        Assert.assertEquals(myAtoi("words and 987"), 0);
        Assert.assertEquals(myAtoi("9aaa"), 9);
        Assert.assertEquals(myAtoi("-"), 0);
        Assert.assertEquals(myAtoi("-1a"), -1);
        Assert.assertEquals(myAtoi("--1"), 0);
        Assert.assertEquals(myAtoi("-+1"), 0);
        Assert.assertEquals(myAtoi("+1"), 1);
        Assert.assertEquals(myAtoi("-91283472332"), Integer.MIN_VALUE);
        Assert.assertEquals(myAtoi("20000000000000000000"), Integer.MAX_VALUE);
        Assert.assertEquals(myAtoi("  0000000000012345678"), 12345678);
        Assert.assertEquals(myAtoi("  -0000000000012345678"), -12345678);
        Assert.assertEquals(myAtoi("+-2"), 0);
        Assert.assertEquals(myAtoi("-+2"), 0);
        Assert.assertEquals(myAtoi("     +004500"), 4500);
        Assert.assertEquals(myAtoi("   +0 123"), 0);
        Assert.assertEquals(myAtoi("-   234"), 0);
        Assert.assertEquals(myAtoi("0-1"), 0);
    }

    /**
     * 时间慢，效率低一些，但是好理解，截取到需要的字符串，使用库函数转换
     *
     * @param str
     * @return
     */
    public static int myAtoi(String str) {
        if (str == null || str.equals("")) {
            return 0;
        }
        // 待优化，只要使用到正则内存空间高一些
        Pattern pattern = Pattern.compile("^[0-9-+]?[0-9]+");
        Matcher matcher = pattern.matcher(str.trim());
        if (matcher.find()) {
            str = matcher.group();
        } else {
            return 0;
        }
        StringBuilder sb = new StringBuilder();
        boolean negativeFlag = false;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c == '-') {
                negativeFlag = true;
                continue;
            }
            if (c == '+' ) {
                continue;
            }
            if ((c >= '0' && c <= '9')) {
                // 判断是否为前置的0
                if (c == '0' && ((sb.length() > 0 && sb.charAt(sb.length() - 1) == '0' && sb.charAt(0) == '0') || sb.length() == 0)) {
                    continue;
                } else {
                    sb.append(c);
                }
            } else {
                break;
            }
        }
        if (sb.toString().equals("")) {
            return 0;
        }
        if (sb.length() > 10) {
            if (negativeFlag) return Integer.MIN_VALUE;
            else return Integer.MAX_VALUE;

        }
        if (negativeFlag) {
            sb.insert(0, "-");
        }
        Long longValue = Long.parseLong(sb.toString());
        if (longValue > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else if (longValue < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        } else {
            return longValue.intValue();
        }
    }

    public static int myAtoi2(String str) {
        if (str == null || str.equals("")) {
            return 0;
        }
        str = str.trim();
        Pattern pattern = Pattern.compile("^[0-9-+]?[0-9]+");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            String group = matcher.group();
            System.out.println(group);
            group = group.replaceAll("^-0+", "-0")
                    .replaceAll("^0+", "0");
            if (group.length() > 11) {
                if (group.startsWith("-")) {
                    return Integer.MIN_VALUE;
                } else {
                    return Integer.MAX_VALUE;
                }
            }
            Long longValue = Long.parseLong(group);
            if (longValue > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            } else if (longValue < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            } else {
                return longValue.intValue();
            }
        } else {
            return 0;
        }
    }
}
