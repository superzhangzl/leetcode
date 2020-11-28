package zzl.leetcode;

import org.junit.Assert;
import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

/**
 * 每日温度
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/daily-temperatures/}
 */
public class DailyTemperatures {
    public static void main(String[] args) {
        int[] nums = GenerateUtil.generateIntArray("73, 74, 75, 71, 69, 72, 76, 73");
        int[] dailyTemperatures = new DailyTemperatures().dailyTemperatures(nums);
        PrintConsoleUtil.printArray(dailyTemperatures);
        Assert.assertArrayEquals(dailyTemperatures, GenerateUtil.generateIntArray("1, 1, 4, 2, 1, 1, 0, 0"));

        nums = GenerateUtil.generateIntArray("55,38,53,81,61,93,97,32,43,78");
        dailyTemperatures = new DailyTemperatures().dailyTemperatures(nums);
        PrintConsoleUtil.printArray(dailyTemperatures);
        Assert.assertArrayEquals(dailyTemperatures, GenerateUtil.generateIntArray("3,1,1,2,1,1,0,1,1,0"));

    }

    public int[] dailyTemperatures(int[] T) {
        int[] res = new int[T.length];
        for (int i = 0; i < T.length - 1; i++) {
            int step = 0;
            for (int j = i; j < T.length; j++) {
                if (T[j] <= T[i]) {
                    step++;
                    continue;
                } else {
                    res[i] = step;
                    break;
                }
            }
        }
        res[res.length - 1] = 0;
        return res;
    }
}
