package zzl.leetcode;

import org.junit.Assert;
import zzl.base.annotation.Level;
import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

import java.nio.channels.Pipe;
import java.util.Arrays;
import java.util.Comparator;

import static zzl.base.enums.Difficulty.*;

/**
 * 用最少数量的箭引爆气球
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/minimum-number-of-arrows-to-burst-balloons/}
 */
@Level(MEDIUM)
public class MinimumNumberOfArrowsToBurstBalloons {
    public static void main(String[] args) {
        int[][] points;
        int minArrowShots;
        //
        points = GenerateUtil.generateBinaryIntArrayBetter("[[10,16],[2,8],[1,6],[7,12]]");
        minArrowShots = new MinimumNumberOfArrowsToBurstBalloons().findMinArrowShots(points);
        Assert.assertEquals(minArrowShots, 2);
        //
        points = GenerateUtil.generateBinaryIntArrayBetter("[[1,2],[3,4],[5,6],[7,8]]");
        minArrowShots = new MinimumNumberOfArrowsToBurstBalloons().findMinArrowShots(points);
        Assert.assertEquals(minArrowShots, 4);
        //
        points = GenerateUtil.generateBinaryIntArrayBetter("[[1,2],[2,3],[3,4],[4,5]]");
        minArrowShots = new MinimumNumberOfArrowsToBurstBalloons().findMinArrowShots(points);
        Assert.assertEquals(minArrowShots, 2);
        //
        points = GenerateUtil.generateBinaryIntArrayBetter("[[2,3],[2,3]]");
        minArrowShots = new MinimumNumberOfArrowsToBurstBalloons().findMinArrowShots(points);
        Assert.assertEquals(minArrowShots, 1);
    }

    /**
     * 思路同“无重叠区间”，
     *
     * @param points
     * @return
     * @see NonOverlappingIntervals
     */
    public int findMinArrowShots(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }
        Arrays.sort(points, Comparator.comparingInt(o -> o[1]));
        int count = 1;
        int leastEnd = points[0][1];
        for (int i = 1; i < points.length; i++) {
            // 和无重叠区间的区别是，相邻的也可以一箭穿破，所以不需要等号
            if (leastEnd < points[i][0]) {
                count++;
                leastEnd = points[i][1];
            }
        }
        return count;
    }
}
