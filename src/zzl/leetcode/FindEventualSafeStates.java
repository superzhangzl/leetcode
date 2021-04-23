package zzl.leetcode;

import zzl.base.annotation.Level;
import zzl.util.GenerateUtil;
import zzl.util.SpecialAssertUtil;

import java.util.*;

import static zzl.base.enums.Difficulty.MEDIUM;

/**
 * 找到最终的安全状态
 *
 * @author zzl
 * @date 2021-04-23
 * @link {https://leetcode-cn.com/problems/find-eventual-safe-states/}
 */
@Level(MEDIUM)
public class FindEventualSafeStates {
    public static void main(String[] args) {
        int[][] graph;
        List<Integer> eventualSafeNodes;
        //
        graph = GenerateUtil.generateBinaryIntNotStableLength("[[1,2],[2,3],[5],[0],[5],[],[]]");
        eventualSafeNodes = new FindEventualSafeStates().eventualSafeNodes(graph);
        SpecialAssertUtil.assertIntListContain(eventualSafeNodes, Arrays.asList(2, 4, 5, 6));
        //
        graph = GenerateUtil.generateBinaryIntNotStableLength("[[1,2,3,4],[1,2],[3,4],[0,4],[]]");
        eventualSafeNodes = new FindEventualSafeStates().eventualSafeNodes(graph);
        SpecialAssertUtil.assertIntListContain(eventualSafeNodes, Arrays.asList(4));
    }

    public List<Integer> eventualSafeNodes(int[][] graph) {
        int len = graph.length;
        int[] outDegrees = new int[len];
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                outDegrees[i]++;
            }
        }
        Queue<Integer> queue = new ArrayDeque<>();
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < outDegrees.length; i++) {
            // 出度为0的节点肯定是安全的
            if (outDegrees[i] == 0) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            Integer poll = queue.poll();
            res.add(poll);
            // 当前位置会超时，需要将入度的关系缓存起来
            for (int i = 0; i < graph.length; i++) {
                for (int j = 0; j < graph[i].length; j++) {
                    if (graph[i][j] == poll) {
                        outDegrees[i]--;
                        if (outDegrees[i] == 0) {
                            queue.add(i);
                        }
                    }
                }
            }
        }
        res.sort(Comparator.comparingInt(o -> o));
        return res;
    }
}
