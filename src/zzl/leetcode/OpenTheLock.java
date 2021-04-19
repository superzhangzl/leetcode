package zzl.leetcode;

import org.junit.Assert;
import zzl.base.annotation.Level;
import zzl.util.GenerateUtil;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static zzl.base.enums.Difficulty.MEDIUM;

/**
 * 打开转盘锁
 *
 * @author zzl
 * @date 2021-04-19
 * @link {https://leetcode-cn.com/problems/open-the-lock/}
 */
@Level(MEDIUM)
public class OpenTheLock {
    public static void main(String[] args) {
        String[] deadends = GenerateUtil.generateStringArray("\"0201\",\"0101\",\"0102\",\"1212\",\"2002\"");
        int minOptCnt = new OpenTheLock().openLock(deadends, "0202");
        Assert.assertEquals(minOptCnt, 6);
    }


    /**
     * BFS
     *
     * @param deadends
     * @param target
     * @return
     */
    public int openLock(String[] deadends, String target) {
        Set<String> dead = Arrays.stream(deadends).collect(Collectors.toSet());
        Set<String> visited = new HashSet<>();
        ArrayDeque<String> queue = new ArrayDeque<>();
        queue.add("0000");
        visited.add("0000");
        int step = 0;
        while (!queue.isEmpty()) {
            // 注：这里的for循环不能直接使用queue.size()作为判断条件，因为queue会一直增长导致条件判断错误
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String poll = queue.poll();
                if (dead.contains(poll)) {
                    continue;
                }
                if (target.equals(poll)) {
                    return step;
                }
                for (int j = 0; j < 4; j++) {
                    String up = moveOneStep(poll, j, 1);
                    if (!visited.contains(up)) {
                        queue.add(up);
                        visited.add(up);
                    }
                    String down = moveOneStep(poll, j, -1);
                    if (!visited.contains(down)) {
                        queue.add(down);
                        visited.add(down);
                    }
                }
            }
            step++;
        }
        return -1;
    }


    /**
     * 支持字符串的某一位向上或者向下旋转
     *
     * @param str
     * @param i
     * @param dis
     * @return
     */
    private String moveOneStep(String str, int i, int dis) {
        char[] chars = str.toCharArray();
        // +10转给正数，然后再取模再转为字符类型
        chars[i] = (char) ((((chars[i] - '0') + dis + 10) % 10) + '0');
        return new String(chars);
    }
}
