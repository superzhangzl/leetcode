package zzl.leetcode;

import org.junit.Assert;
import zzl.base.annotation.Level;
import zzl.util.PrintConsoleUtil;

import java.util.ArrayDeque;
import java.util.Queue;

import static zzl.base.enums.Difficulty.MEDIUM;

/**
 * 课程表II
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/course-schedule-ii/}
 */
@Level(MEDIUM)
public class CourseScheduleII {
    public static void main(String[] args) {
        int[] order;
        order = new CourseScheduleII().findOrder(2, new int[][]{{1, 0}});
        Assert.assertArrayEquals(order, new int[]{0, 1});
        order = new CourseScheduleII().findOrder(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}});
        Assert.assertArrayEquals(order, new int[]{0, 1, 2, 3});
        order = new CourseScheduleII().findOrder(2, new int[][]{{0, 1}, {1, 0}});
        Assert.assertArrayEquals(order, new int[]{});
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] inDegrees = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            inDegrees[prerequisite[0]]++;
        }
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < inDegrees.length; i++) {
            if (inDegrees[i] == 0) {
                queue.add(i);
            }
        }
        int[] res = new int[numCourses];
        int index = 0;
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            numCourses--;
            for (int[] prerequisite : prerequisites) {
                if (prerequisite[1] == poll) {
                    inDegrees[prerequisite[0]]--;
                    if (inDegrees[prerequisite[0]] == 0) {
                        queue.add(prerequisite[0]);
                    }
                }
            }
            // 把入度已经为0的元素依次添加到res中
            res[index++] = poll;
        }
        if (numCourses != 0) {
            return new int[0];
        }
        return res;
    }

    /**
     * 当前节点在入度和出度的表示上表示反向，按照个人理解进行重写
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public int[] findOrder_backup(int numCourses, int[][] prerequisites) {
        int[] order = new int[numCourses];
        // 入度表，记录从这个点出发的边的个数
        int[] indegrees = new int[numCourses];
        for (int[] cp : prerequisites) {
            // cp[0]为当前节点，cp[1]是目标节点
            // 这样一组节点表示一个有向的连接关系
            indegrees[cp[1]]++;
        }
        PrintConsoleUtil.printArray(indegrees);
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < indegrees.length; i++) {
            if (indegrees[i] == 0) {
                queue.add(i);
            }
        }
        int count = numCourses;
        while (!queue.isEmpty()) {
            // 拿出来了一门课去判断
            numCourses--;
            Integer ele = queue.poll();
            // 添加路径

            for (int[] cp : prerequisites) {
                if (cp[0] == ele) {
                    // 入度-1，即断开一个指向ele的连接
                    indegrees[cp[1]]--;
                    if (indegrees[cp[1]] == 0) {
                        queue.add(cp[1]);
                    }
                }
            }
            order[--count] = ele;
        }
        System.out.println(count);
        System.out.println(numCourses);
        if (count > 0) {
            return new int[0];
        }
        return order;
    }

}
