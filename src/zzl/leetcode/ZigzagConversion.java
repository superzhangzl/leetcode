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
//        System.out.println(convert("ABCDEFGHIJKL", 4));
//        System.out.println(convert("ABCDEFGHIJKL", 2));
        System.out.println(convert("LEETCODEISHIRING", 3).equals("LCIRETOESIIGEDHN"));
        System.out.println(convert("LEETCODEISHIRING", 4).equals("LDREOEIIECIHNTSG"));
        System.out.println(convert("AB", 1).equals("AB"));
        System.out.println(convert("A", 2).equals("A"));
        System.out.println(convert("ABC", 2).equals("ACB"));
    }

    /**
     * 思路：将字符串按照层数划分区间，两个游标从两头向中间移动，每执行一层调整一次游标
     * 因为区间内取值是中心对称的，到最中间只输出一次即可
     * 时间复杂度为O(n)，其实略大于O(n)一丢丢，因为要向上取整
     * <p>
     * 注：看到一个解题思路是使用一个flag来取反表示中心对称的操作，思路其实是一致的
     *
     * @param s
     * @param numRows
     * @return
     */
    public static String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        if (s.length() <= numRows) {
            return s;
        }
        int loopCount = (int) Math.ceil(s.length() / (double) numRows);
        print("str length: " + s.length());
        print("loop count: " + loopCount);
        int leftIndex = 0;
        int rightIndex = 2 * numRows - 2;
        char[] result = new char[s.length()];
        int idx = 0;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < loopCount; j++) {
                int left = leftIndex + (2 * numRows - 2) * j;
                int right = rightIndex + (2 * numRows - 2) * j;
                print("left offset: " + left + "; right offset: " + right);
                // left 和right 肯定会相等，因为移动长度为 2 * numRows - 2，一定为偶数
                if (left == right && left < s.length()) {
                    char middleChar = s.charAt(left);
                    print("middleChar:" + middleChar);
                    result[idx++] = middleChar;
                    continue;
                }
                if (left < s.length()) {
                    char leftChar = s.charAt(left);
                    print("leftChar: " + leftChar);
                    result[idx++] = leftChar;
                }
                if (right % (2 * numRows - 2) != 0 && right < s.length()) {
                    char rightChar = s.charAt(right);
                    print("rightChar: " + rightChar);
                    result[idx++] = rightChar;
                }
            }
            leftIndex++;
            rightIndex--;
        }
        return new String(result);
    }

    private static void print(Object msg) {
//        System.out.println(msg);
    }
}
