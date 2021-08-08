package zzl.leetcode;

import org.junit.Assert;
import zzl.base.annotation.Level;

import static zzl.base.enums.Difficulty.*;

/**
 * 第 N 个泰波那契数
 *
 * @author zzl
 * @date 2021-08-08
 * @link {https://leetcode-cn.com/problems/n-th-tribonacci-number/}
 */
@Level(SIMPLE)
public class NThTribonacciNumber {
    private static int[] answers = new int[39];

    static {
        answers[0] = 0;
        answers[1] = 1;
        answers[2] = 1;
        for (int i = 3; i < 38; i++) {
            answers[i] = answers[i - 1] + answers[i - 2] + answers[i - 3];
        }
    }

    public static void main(String[] args) {
        int tribonacci;
        //
        tribonacci = new NThTribonacciNumber().tribonacci(4);
        Assert.assertEquals(tribonacci, 4);
        //
        tribonacci = new NThTribonacciNumber().tribonacci(25);
        Assert.assertEquals(tribonacci, 1389537);
    }

    public int tribonacci(int n) {
        return answers[n];
    }
}
