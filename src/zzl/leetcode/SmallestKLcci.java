package zzl.leetcode;

import org.junit.Assert;
import zzl.base.annotation.Level;
import zzl.util.GenerateUtil;
import zzl.util.SpecialAssertUtil;

import java.util.Comparator;
import java.util.PriorityQueue;

import static zzl.base.enums.Difficulty.*;

/**
 * 最小K个数
 *
 * @author zzl
 * @date 2021-05-14
 * @link {https://leetcode-cn.com/problems/smallest-k-lcci/}
 */
@Level(MEDIUM)
public class SmallestKLcci {
    public static void main(String[] args) {
        int[] arr;
        int[] smallestK;
        //
        arr = GenerateUtil.generateIntArray("1,3,5,7,2,4,6,8");
        smallestK = new SmallestKLcci().smallestK(arr, 4);
        Assert.assertArrayEquals(new int[]{1, 2, 3, 4}, smallestK);

    }

    /**
     * 大根堆
     *
     * @param arr
     * @param k
     * @return
     * @link {https://leetcode-cn.com/problems/smallest-k-lcci/solution/zui-xiao-kge-shu-by-leetcode-solution-o5eg/}
     */
    public int[] smallestK(int[] arr, int k) {
        int[] vec = new int[k];
        if (k == 0) { // 排除 0 的情况
            return vec;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>((num1, num2) -> num2 - num1);
        for (int i = 0; i < k; ++i) {
            queue.offer(arr[i]);
        }
        /*
        首先将前 k 个数插入大根堆中，随后从第 k+1 个数开始遍历，
        如果当前遍历到的数比大根堆的堆顶的数要小，就把堆顶的数弹出，再插入当前遍历到的数。
        最后将大根堆里的数存入数组返回即可。
         */
        for (int i = k; i < arr.length; ++i) {
            if (queue.peek() > arr[i]) {
                queue.poll();
                queue.offer(arr[i]);
            }
        }
        for (int i = 0; i < k; ++i) {
            vec[i] = queue.poll();
        }
        return vec;
    }
}
