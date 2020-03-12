package zzl.leetcode;

import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

import java.util.*;

/**
 * @author zzl
 * @link {https://leetcode-cn.com/problems/merge-intervals/}
 */
public class MergeIntervals {
    public static void main(String[] args) {
        int[][] ints = GenerateUtil.generateBinaryIntArray(" 1,3\n" +
                "2,6\n" +
                "8,10\n" +
                "15,18\n", ",");
        int[][] res = new MergeIntervals().merge(ints);
//        PrintConsoleUtil.printArray(res);
//        ints = GenerateUtil.generateBinaryIntArray(" 1,4\n" +
//                "0,4\n", ",");
//        res = new MergeIntervals().merge(ints);
//        PrintConsoleUtil.printArray(res);
//        ints = GenerateUtil.generateBinaryIntArray(" 1,4\n" +
//                "4,5\n", ",");
//        res = new MergeIntervals().merge(ints);
//        PrintConsoleUtil.printArray(res);
//        ints = GenerateUtil.generateBinaryIntArray(" 1,4\n" +
//                "1,6\n", ",");
//        res = new MergeIntervals().merge(ints);
//        PrintConsoleUtil.printArray(res);
//        ints = GenerateUtil.generateBinaryIntArray(" 1,4\n" +
//                "2,3\n", ",");
//        res = new MergeIntervals().merge(ints);
//        PrintConsoleUtil.printArray(res);
        ints = GenerateUtil.generateBinaryIntArray("1,9\n" +
                "2,5\n" +
                "19,20\n" +
                "10,11\n" +
                "12,20\n" +
                "0,3\n" +
                "0,1\n" +
                "0,2", ",");
        res = new MergeIntervals().merge(ints);
        PrintConsoleUtil.printArray(res);
    }

    /**
     * 需要排序才行
     *
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        LinkedList<int[]> merged = new LinkedList<>();
        for (int i = 0; i < intervals.length; i++) {
            if (merged.isEmpty() || merged.getLast()[1] < intervals[i][0]) {
                merged.add(new int[]{intervals[i][0], intervals[i][1]});
            } else {
                merged.getLast()[1] = Math.max(merged.getLast()[1], intervals[i][1]);
            }
        }
        int[][] result = new int[merged.size()][2];
        for (int i = 0; i < merged.size(); i++) {
            result[i][0] = merged.get(i)[0];
            result[i][1] = merged.get(i)[1];
        }
        return result;
    }
}
