package zzl.leetcode;

import org.junit.Assert;
import zzl.util.PrintConsoleUtil;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 课程表
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/course-schedule/}
 * @link {https://zhuanlan.zhihu.com/p/56024487}
 */
public class CourseSchedule {
    public static void main(String[] args) {
        // 起始点为0
        Assert.assertEquals(new CourseSchedule().canFinish(6, new int[][]{{0, 1}, {1, 2}, {1, 4}, {2, 3}, {2, 4}, {3, 5}, {4, 5}}), true);
        Assert.assertEquals(new CourseSchedule().canFinish(2, new int[][]{{1, 0}}), true);
        Assert.assertEquals(new CourseSchedule().canFinish(2, new int[][]{{1, 0}, {0, 1}}), false);
    }

    /**
     * BFS
     * 1. 统计课程安排图中每个节点的入度，生成 入度表 indegrees。
     * 2. 借助一个队列 queue，将所有入度为 0 的节点入队。
     * 3. 当 queue 非空时，依次将队首节点出队，在课程安排图中删除此节点 pre：
     * - 并不是真正从邻接表中删除此节点 pre，而是将此节点对应所有邻接节点 cur 的入度 −1，即 indegrees[cur] -= 1。
     * - 当入度 −1后邻接节点 cur 的入度为 0，说明 cur 所有的前驱节点已经被 “删除”，此时将 cur 入队。
     * 4. 在每次 pre 出队时，执行 numCourses--；
     * - 若整个课程安排图是有向无环图（即可以安排），则所有节点一定都入队并出队过，即完成拓扑排序。换个角度说，若课程安排图中存在环，一定有节点的入度始终不为 0。
     * - 因此，拓扑排序出队次数等于课程个数，返回 numCourses == 0 判断课程是否可以成功安排。
     *
     * @param numCourses
     * @param prerequisites
     * @return
     * @link {https://leetcode-cn.com/problems/course-schedule/solution/course-schedule-tuo-bu-pai-xu-bfsdfsliang-chong-fa/}
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
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
        while (!queue.isEmpty()) {
            // 拿出来了一门课去判断
            numCourses--;
            Integer ele = queue.poll();
            System.out.println(ele);
            for (int[] cp : prerequisites) {
                if (cp[0] == ele) {
                    // 入度-1，即断开一个指向ele的连接
                    indegrees[cp[1]]--;
                    if (indegrees[cp[1]] == 0) {
                        queue.add(cp[1]);
                    }
                }
            }
        }
        // 看下是不是都拿完了，如果拿完了说明没有环
        return numCourses == 0;
    }


    /**
     * 注：这个方法应该是拿出度作为判断依据吧
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinishBackup(int numCourses, int[][] prerequisites) {
        // 入度表，记录从这个点出发的边的个数
        int[] indegrees = new int[numCourses];
        for (int[] cp : prerequisites) {
            // cp[0]为当前节点，cp[1]是目标节点
            // 这样一组节点表示一个有向的连接关系
            indegrees[cp[0]]++;
        }
        PrintConsoleUtil.printArray(indegrees);
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < indegrees.length; i++) {
            if (indegrees[i] == 0) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            // 拿出来了一门课去判断
            numCourses--;
            Integer ele = queue.poll();
            System.out.println(ele);
            for (int[] cp : prerequisites) {
                if (cp[1] == ele) {
                    // 入度-1，即断开一个指向ele的连接
                    indegrees[cp[0]]--;
                    if (indegrees[cp[0]] == 0) {
                        queue.add(cp[0]);
                    }
                }
            }
        }
        // 看下是不是都拿完了，如果拿完了说明没有环
        return numCourses == 0;
    }
}
