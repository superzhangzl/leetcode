package zzl.leetcode;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzl
 * @link {https://leetcode-cn.com/problems/permutation-sequence/}
 */
public class PermutationSequence {
    public static void main(String[] args) {
        PermutationSequence permutationSequence = new PermutationSequence();
//        Assert.assertEquals(permutationSequence.getPermutation(3, 3), "213");
        Assert.assertEquals(permutationSequence.getPermutation(4, 8), "2143");
        Assert.assertEquals(permutationSequence.getPermutation(4, 9), "2314");
        // 时间超限
        Assert.assertEquals(permutationSequence.getPermutation(9, 296662), "839127564");
    }

    public String getPermutation(int n, int k) {
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nums.add(i + 1);
        }
        // 3, 6 -> 3 213
        // [[1, 2, 3], [1, 3, 2],
        //  [2, 1, 3], [2, 3, 1],
        //  [3, 1, 2], [3, 2, 1]]
        // 4, 24 -> 9 2314
        // [[1, 2, 3, 4], [1, 2, 4, 3], [1, 3, 2, 4], [1, 3, 4, 2], [1, 4, 2, 3], [1, 4, 3, 2],
        //  [2, 1, 3, 4], [2, 1, 4, 3], [2, 3, 1, 4], [2, 3, 4, 1], [2, 4, 1, 3], [2, 4, 3, 1],
        //  [3, 1, 2, 4], [3, 1, 4, 2], [3, 2, 1, 4], [3, 2, 4, 1], [3, 4, 1, 2], [3, 4, 2, 1],
        //  [4, 1, 2, 3], [4, 1, 3, 2], [4, 2, 1, 3], [4, 2, 3, 1], [4, 3, 1, 2], [4, 3, 2, 1]]
        StringBuilder sb = new StringBuilder();
        // 首先减一，因为使用的是索引的方式，下标从0开始
        k = k - 1;
        // 循环不变式
        // 全排列是有规律的，每次取当前下标所在的起始数字
        // eg：4,9 => 先9-1=8取得下标，
        // 第一次 8/fib(4-1)，那么肯定是2开头的，剩下的从[1,3,4]的全排列中选取，8%fib(4-1)=2，取得在下一个组合中的偏移量
        // 第二次 2/fin(3-1)，那么肯定是3开头的，以此推类
        while (!nums.isEmpty()) {
            int fib = factorial(n - 1);
            int index = k / fib;
//            System.out.println("k     > " + k);
//            System.out.println("fib   > " + fib);
//            System.out.println("index > " + index);
//            System.out.println("pool  > " + nums);
            Integer cur = nums.get(index);
//            System.out.println("cur   > " + cur);
            sb.append(cur);
//            System.out.println();
            nums.remove(cur);
            k = k % fib;
            n--;
        }
//        System.out.println(sb);
        return sb.toString();
    }

    private int factorial(int n) {
        int res = 1;
        for (int i = 2; i <= n; i++) {
            res *= i;
        }
        return res;
    }

}
