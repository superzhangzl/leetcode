package zzl.leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 格雷编码
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/gray-code/}
 */
public class GrayCode {
    public static void main(String[] args) {
        // 定长编码
        System.out.println(new GrayCode().grayCode(2)); // [0,1,3,2]
        System.out.println(new GrayCode().grayCode(0));
    }

    /**
     * 两个连续的数值仅有一个位数的差异
     *
     * @param n
     * @return
     */
    public List<Integer> grayCode(int n) {
        int count = (int) Math.pow(2, n);
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            // 格雷码的递推公式G(n) = B(n+1) XOR B(n)
            // G：格雷码 B：二进制码 n：正在计算的位
            /*
            1位格雷码有两个码字
            (n+1)位格雷码中的前2n个码字等于n位格雷码的码字，按顺序书写，加前缀0
            (n+1)位格雷码中的后2n个码字等于n位格雷码的码字，按逆序书写，加前缀1
            n+1位格雷码的集合 = n位格雷码集合(顺序)加前缀0 + n位格雷码集合(逆序)加前缀1
             */
            result.add(i ^ (i >> 1));
            System.out.println(i >> 1);
            System.out.println(i);
            System.out.println(Integer.toBinaryString(i ^ (i + 1)));
            System.out.println("=======");
        }
        return result;
    }
}
