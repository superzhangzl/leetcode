package zzl.leetcode;

import zzl.util.GenerateUtil;

/**
 * 单调数列
 *
 * @link {https://leetcode-cn.com/problems/monotonic-array/}
 */
public class MonotonicArray {
    public static void main(String[] args) {
        int[] array = GenerateUtil.generateIntArray("1,2,2,3");
//        System.out.println(new MonotonicArray().isMonotonic(array));
        array = GenerateUtil.generateIntArray("1,3,2");
        System.out.println(new MonotonicArray().isMonotonic(array));
    }

    public boolean isMonotonic(int[] A) {
        boolean up = true;
        boolean down = true;
        for (int i = 1; i < A.length; i++) {
            if (A[i] >= A[i - 1]) {
                // do nothing
            } else {
                up = false;
            }
            if (A[i] <= A[i - 1]) {
                // do nothing
            } else {
                down = false;
            }
        }
        return up | down;
    }
}
