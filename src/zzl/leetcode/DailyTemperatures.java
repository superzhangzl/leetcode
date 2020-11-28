package zzl.leetcode;

import org.junit.Assert;
import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

import java.util.Stack;

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

    /**
     * 单调栈
     * 保证栈的顺序是递减的，如果出现某一个数大于栈顶对应的数，就将栈弹出
     * 结束后将当前节点的下标放入单调栈中
     *
     * @param T 温度历史记录
     * @return 结果
     */
    public int[] dailyTemperatures(int[] T) {
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[T.length];
        for (int i = 0; i < T.length; i++) {
            while (!stack.isEmpty() && T[stack.peek()] < T[i]) {
                int topIndex = stack.pop();
                res[topIndex] = i - topIndex;
            }
            stack.push(i);
        }
        return res;
    }

    public int[] dailyTemperaturesBad(int[] T) {
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
