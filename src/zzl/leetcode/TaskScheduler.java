package zzl.leetcode;

import org.junit.Assert;
import zzl.base.annotation.Level;
import zzl.util.GenerateUtil;

import java.util.HashMap;
import java.util.Map;

import static zzl.base.enums.Difficulty.MEDIUM;

/**
 * 任务调度器
 *
 * @author zzl
 * @date 2021-04-21
 * @link {https://leetcode-cn.com/problems/task-scheduler/}
 */
@Level(MEDIUM)
public class TaskScheduler {
    public static void main(String[] args) {
        char[] tasks;
        int leastInterval;
        //
        tasks = GenerateUtil.generateCharArray("\"A\",\"A\",\"A\",\"B\",\"B\",\"B\"");
        leastInterval = new TaskScheduler().leastInterval(tasks, 2);
        Assert.assertEquals(leastInterval, 8);
        //
        tasks = GenerateUtil.generateCharArray("\"A\",\"A\",\"A\",\"B\",\"B\",\"B\"");
        leastInterval = new TaskScheduler().leastInterval(tasks, 0);
        Assert.assertEquals(leastInterval, 6);
        //
        tasks = GenerateUtil.generateCharArray("\"A\",\"A\",\"A\",\"A\",\"A\",\"A\",\"B\",\"C\",\"D\",\"E\",\"F\",\"G\"");
        leastInterval = new TaskScheduler().leastInterval(tasks, 2);
        Assert.assertEquals(leastInterval, 16);
    }

    /**
     * 数学模型推导
     * 详见解析内图文
     *
     * @param tasks
     * @param n
     * @return
     * @link {https://leetcode-cn.com/problems/task-scheduler/solution/ren-wu-diao-du-qi-by-leetcode-solution-ur9w/}
     */
    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> freq = new HashMap<>();
        // 最多的执行次数
        int maxExec = 0;
        for (char ch : tasks) {
            int exec = freq.getOrDefault(ch, 0) + 1;
            freq.put(ch, exec);
            maxExec = Math.max(maxExec, exec);
        }

        // 具有最多执行次数的任务数量
        int maxCount = 0;
        for (Integer value : freq.values()) {
            if (value == maxExec) {
                ++maxCount;
            }
        }

        return Math.max((maxExec - 1) * (n + 1) + maxCount, tasks.length);
    }
}
