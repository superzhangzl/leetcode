package zzl.leetcode;

import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

import java.util.Arrays;

/**
 * 移动石子直到连续 II
 * TODO: 脑筋急转弯
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/moving-stones-until-consecutive-ii/}
 */
public class MovingStonesUntilConsecutiveII {
    public static void main(String[] args) {
        int[] stones;
        int[] movedStones;
        //==
        stones = GenerateUtil.generateIntArray("7,4,9");
        movedStones = new MovingStonesUntilConsecutiveII().numMovesStonesII(stones);
        PrintConsoleUtil.printArray(movedStones);
    }

    /**
     * @param stones
     * @return
     * @link {https://leetcode-cn.com/problems/moving-stones-until-consecutive-ii/solution/jie-ti-si-lu-by-owenzzz/}
     */
    public int[] numMovesStonesII(int[] stones) {
//        int max = Arrays.stream(stones).max().getAsInt();
//        int[] rule = new int[max + 1];
//        Arrays.fill(rule, -1);
//        for (int i = 0; i < stones.length; i++) {
//            rule[stones[i]] = i;
//        }
        // 排序是为了取这个区间的最大值和最小值？
        Arrays.sort(stones);
        int n = stones.length;
        // 石子可以放置的空间：最大值减最小值+1，并且减上已经放了石子的位置
        int mx = stones[n - 1] - stones[0] + 1 - n;
        /*
        Math.min(stones[n - 1] - stones[n - 2] - 1, stones[1] - stones[0] - 1)
        表示移动一次末尾的石头或移动一次初始的石头后，(第一个和第二个之间的空缺) 还有 (倒数第一个和倒数第二个) 不能再放石头

        理论上最大移动次数就是最大值和最小值之间空缺的个数，但因为题目有要求，被移动的石子不能成为端点石子。
        需要选从左边开始收敛还是从右边开始收敛，那就找两头空间差值小的来动先一步到位，这样用理论最大值减去不能再选择的剩下的就是可选的最大值
         */
        mx -= Math.min(stones[n - 1] - stones[n - 2] - 1, stones[1] - stones[0] - 1);
        int mi = mx;
        int i = 0;
        int j = 0;
        for (i = 0; i < n; ++i) {
            while (j + 1 < n && stones[j + 1] - stones[i] + 1 <= n)
                ++j;
            int cost = n - (j - i + 1);
            if (j - i + 1 == n - 1 && stones[j] - stones[i] + 1 == n - 1)
                cost = 2;
            mi = Math.min(mi, cost);
        }
        return new int[]{mi, mx};
    }
}
