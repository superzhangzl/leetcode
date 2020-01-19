package zzl.leetcode;

/**
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 * <p>
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 * <p>
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 * <p>
 * 请你实现这个将字符串进行指定行数变换的函数：
 * <p>
 * string convert(string s, int numRows);
 * 示例 1:
 * <p>
 * 输入: s = "LEETCODEISHIRING", numRows = 3
 * 输出: "LCIRETOESIIGEDHN"
 * 示例 2:
 * <p>
 * 输入: s = "LEETCODEISHIRING", numRows = 4
 * 输出: "LDREOEIIECIHNTSG"
 * 解释:
 * <p>
 * L     D     R
 * E   O E   I I
 * E C   I H   N
 * T     S     G
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/zigzag-conversion}
 */
public class ZigzagConversion {
    public static void main(String[] args) {
        System.out.println(convert("LEETCODEISHIRING", 3));
        System.out.println(convert("LEETCODEISHIRING", 4));
    }

    public static String convert(String s, int numRows) {
        if (s.length() == 1) {
            return s;
        }
        if (s.length() == 2) {
        }
        double length = s.length();
        int width = (int) (Math.ceil((length / (2.0 * numRows - 2.0))) * (numRows - 1));
        int height = numRows;
        print("length: " + length);
        print("size: (" + width + "," + height + ")");
        int[][] arr = new int[height][width];
        // todo 最简单的方法，构造一个矩阵，再重新读取。若字符串长度非常长，浪费很大
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
        System.out.println("=================");
        return null;
    }

    private static void print(Object msg) {
        System.out.println(msg);
    }
}
