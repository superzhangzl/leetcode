package zzl.leetcode;

import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

/**
 * 翻转图像
 *
 * @link {https://leetcode-cn.com/problems/flipping-an-image/}
 */
public class FlippingAnImage {
    public static void main(String[] args) {
        int[][] array = GenerateUtil.generateBinaryIntArray("1,1,0\n" +
                "1,0,1\n" +
                "0,0,0", ",");
        int[][] invertImage = new FlippingAnImage().flipAndInvertImage(array);
        // [[1,0,0],[0,1,0],[1,1,1]]
        PrintConsoleUtil.printArray(invertImage);
    }

    /**
     * https://leetcode-cn.com/problems/flipping-an-image/solution/fan-zhuan-tu-xiang-by-leetcode-solution-yljd/
     *
     * @param A
     * @return
     */
    public int[][] flipAndInvertImage(int[][] A) {
        int n = A.length;
        for (int i = 0; i < n; i++) {
            int left = 0, right = A[i].length - 1;
            while (left < right) {
                if (A[i][left] == A[i][right]) {
                    A[i][left] ^= 1;
                    A[i][right] ^= 1;
                }
                left++;
                right--;
            }
            if (left == right) {
                A[i][left] ^= 1;
            }
        }
        return A;

    }
}
