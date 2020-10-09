package zzl.leetcode;

import zzl.util.PrintConsoleUtil;

/**
 * 比特位计数
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/counting-bits/}
 */
public class CountingBits {
    public static void main(String[] args) {
        int number = 5;
        PrintConsoleUtil.printArray(new CountingBits().countBits(number));
    }

    /**
     * @param num
     * @return
     */
    public int[] countBits(int num) {
        int[] result = new int[num + 1];
        for (int i = 0; i <= num; i++) {
            result[i] = calculateCount(i);
        }
        return result;
    }

    private int calculateCount(int num) {
        int count = 0;
        while (num != 0) {
            System.out.println(Integer.toBinaryString(num - 1) + "==" + Integer.toBinaryString(num));
            if (((num - 1) ^ num) == 1) {
                count++;
            }
            num >>= 1;
        }
        return count;
    }
}
