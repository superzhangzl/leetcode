package zzl.leetcode;

import org.junit.Assert;

/**
 * @author zzl
 * @link {https://leetcode-cn.com/problems/powx-n/}
 */
public class Powx_n {
    public static void main(String[] args) {
        System.out.println(myPow(2.00000, 10));
        System.out.println(myPow(2.10000, 3));
        System.out.println(myPow(2.00000, -2));
        System.out.println(myPow(1.00000, -2147483648));
        System.out.println(myPow(2, -2147483648)); //0
    }

    public static double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n > 0 && n <= Integer.MAX_VALUE) {
            return qpow(x, n);
        }
        if (n < 0 && n >= Integer.MIN_VALUE) {
            return 1 / qpow(x, -n);
        }
        return 1.0;
    }

    public static double myPow_official(double x, int n) {
        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        double ans = 1;
        double current_product = x;
        for (long i = N; i > 0; i >>= 1) {
            if ((i % 2) == 1) {
                ans = ans * current_product;
            }
            current_product = current_product * current_product;
        }
        return ans;
    }

    public static double qpow(double a, long b) {
        double res = 1;
        while (b != 0 && b > Integer.MIN_VALUE) {
            if ((b & 1) != 0) {
                res = res * a;
            }
            b >>= 1;
            a *= a;
        }
        return res;
    }

}
