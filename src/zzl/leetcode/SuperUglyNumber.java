package zzl.leetcode;

import org.junit.Assert;
import zzl.base.annotation.Level;
import zzl.util.GenerateUtil;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;

import static zzl.base.enums.Difficulty.*;

/**
 * 超级丑数
 *
 * @author zzl
 * @date 2021-08-09
 * @link {https://leetcode-cn.com/problems/super-ugly-number/}
 */
@Level(MEDIUM)
public class SuperUglyNumber {
    public static void main(String[] args) {
        int[] primes;
        int superUglyNumber;
        //
        primes = GenerateUtil.generateIntArray("2,7,13,19");
        superUglyNumber = new SuperUglyNumber().nthSuperUglyNumber(12, primes);
        Assert.assertEquals(superUglyNumber, 32);
        //
        primes = GenerateUtil.generateIntArray("2,35");
        superUglyNumber = new SuperUglyNumber().nthSuperUglyNumber(1, primes);
        Assert.assertEquals(superUglyNumber, 1);
    }

    public int nthSuperUglyNumber(int n, int[] primes) {
        Set<Long> set = new HashSet<>();
        PriorityBlockingQueue<Long> heap = new PriorityBlockingQueue<>();
        set.add(1L);
        heap.add(1L);
        int nth = 0;
        for (int i = 0; i < n; i++) {
            Long cur = heap.poll();
            nth = Math.toIntExact(cur);
            /*
            1. 是最小的丑数。
            2. 对于任意一个丑数 xx，其与任意给定的质因数 primes[i]primes[i] 相乘，结果仍为丑数。
            前提条件：
            将x写入小根堆中，堆顶元素为第n个最小值；
            堆顶x是超级丑数，那x*primes[i]也是丑数，同时使用set进行去重
             */
            for (int prime : primes) {
                if (!set.contains(prime * cur)) {
                    set.add(prime * cur);
                    heap.add(prime * cur);
                }
            }
        }
        return nth;
    }
}
