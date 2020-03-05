package zzl.leetcode;

import zzl.util.PrintConsoleUtil;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author zzl
 * @link {https://leetcode-cn.com/problems/course-schedule-ii/}
 */
public class CourseScheduleII {
    public static void main(String[] args) {
        int[] order = new CourseScheduleII().findOrder(2, new int[][]{{1, 0}});
        PrintConsoleUtil.printArray(order);
        order = new CourseScheduleII().findOrder(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}});
        PrintConsoleUtil.printArray(order);
        order = new CourseScheduleII().findOrder(2, new int[][]{{0, 1}, {1, 0}});
        PrintConsoleUtil.printArray(order);
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
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
