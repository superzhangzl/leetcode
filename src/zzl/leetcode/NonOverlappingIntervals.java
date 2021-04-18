package zzl.leetcode;

import org.junit.Assert;
import zzl.base.annotation.Level;
import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

import java.util.Arrays;
import java.util.Comparator;

import static zzl.base.enums.Difficulty.MEDIUM;

/**
 * 无重叠区间
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/non-overlapping-intervals/}
 */
@Level(MEDIUM)
public class NonOverlappingIntervals {
    public static void main(String[] args) {
        int eraseOverlapIntervals;
        int[][] intervals;
        //
        intervals = GenerateUtil.generateBinaryIntArrayBetter("[ [1,2], [2,3], [3,4], [1,3] ]");
        eraseOverlapIntervals = new NonOverlappingIntervals().eraseOverlapIntervals(intervals);
        Assert.assertEquals(eraseOverlapIntervals, 1);
    }

    /**
     * 总的区间数 - 最大不重叠区间数 = 待移除使得不重叠的最小区间数
     *
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        return intervals.length - maxNonOverLapping(intervals);
    }

    /**
     * 求最大不重叠区间
     *
     * @param intervals
     * @return
     */
    private int maxNonOverLapping(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));
        int count = 1;
        int leastEnd = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            // 如果下一区间的起始时间大于等于(相邻不算重叠)上一个区间的结束时间
            // 那么认为这两个区间是不重叠的，数量加一
            if (leastEnd <= intervals[i][0]) {
                count++;
                leastEnd = intervals[i][1];
            }
        }
        return count;
    }
}
