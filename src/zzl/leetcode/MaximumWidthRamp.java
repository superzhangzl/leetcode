package zzl.leetcode;

import org.junit.Assert;
import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

import java.util.*;

/**
 * 最大宽度坡
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/maximum-width-ramp/}
 */
public class MaximumWidthRamp {
    public static void main(String[] args) {
        int[] array;
        int maxWidthRamp;
        //
        array = GenerateUtil.generateIntArray("6,0,8,2,1,5");
        maxWidthRamp = new MaximumWidthRamp().maxWidthRamp(array);
        Assert.assertEquals(maxWidthRamp, 4);
        //
        array = GenerateUtil.generateIntArray("9,8,1,0,1,9,4,0,4,1");
        maxWidthRamp = new MaximumWidthRamp().maxWidthRamp(array);
        Assert.assertEquals(maxWidthRamp, 7);
    }


    /**
     * 单调栈，先以A[0]起始，写入递减序列，即栈中的序列是越来越小
     * 然后再倒叙遍历，只要从结尾开始有比栈顶大的，就满足一次条件，
     * 最后在判断最大的
     *
     * @param A
     * @return
     * @link {https://leetcode-cn.com/problems/maximum-width-ramp/solution/java-dan-diao-zhan-er-fen-jie-fa-chang-shi-jie-shi/}
     */
    public int maxWidthRamp(int[] A) {
        PrintConsoleUtil.printArray(A);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < A.length; i++) {
            if (stack.isEmpty() || A[stack.peek()] > A[i]) {
                stack.push(i);
            }
        }
        int res = 0;
        for (int i = A.length - 1; i >= 0; i--) {
            // (i,j)，且A[i] <= A[j]
            while (!stack.isEmpty() && A[stack.peek()] <= A[i]) {
                // 直接pop即可，因为是单调的，栈后续的值要更大
                int cur = stack.pop();
                res = Math.max(res, i - cur);
            }
        }
        return res;
    }

    /**
     * 普通解法
     *
     * @param A
     * @return
     * @link {https://leetcode-cn.com/problems/maximum-width-ramp/solution/zui-da-kuan-du-po-by-leetcode/}
     */
    public int maxWidthRamp_Normal(int[] A) {
        List<Pair> list = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            list.add(new Pair(i, A[i]));
        }
        // 按照A[x]进行排序，因为有要求对于元祖(i,j)，A[i] <= A[j]
        list.sort(Comparator.comparingInt(o -> o.j));
        int ans = 0;
        // 默认指向最大下标
        int m = A.length;
        for (Pair pair : list) {
            // 此时的i是乱序的，计算当前i和
            ans = Math.max(ans, pair.i - m);
            // 因为最大宽度定义是 j - i, m即当前被减的i，m越小越好，计算出来的宽度是最大的
            // 即m是索引最小的下标
            m = Math.min(m, pair.i);
        }
        return ans;
    }

    class Pair {
        int i;
        int j;

        public Pair(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "i=" + i +
                    ", j=" + j +
                    '}';
        }
    }
}
