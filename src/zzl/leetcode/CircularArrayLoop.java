package zzl.leetcode;

import org.junit.Assert;
import zzl.base.annotation.AlgTemplate;
import zzl.base.annotation.Level;
import zzl.util.GenerateUtil;

import java.util.*;

import static zzl.base.enums.Difficulty.MEDIUM;

/**
 * 环形数组是否存在循环
 *
 * @author zzl
 * @date 2021-08-07
 * @link {https://leetcode-cn.com/problems/circular-array-loop/}
 */
@Level(MEDIUM)
public class CircularArrayLoop {
    public static void main(String[] args) {
        int[] nums;
        boolean loop;
        //
        nums = GenerateUtil.generateIntArray("2,-1,1,2,2");
        loop = new CircularArrayLoop().circularArrayLoop(nums);
        Assert.assertTrue(loop);
        //
        nums = GenerateUtil.generateIntArray("-1,2");
        loop = new CircularArrayLoop().circularArrayLoop(nums);
        Assert.assertFalse(loop);
        //
        nums = GenerateUtil.generateIntArray("-2,1,-1,-2,-2");
        loop = new CircularArrayLoop().circularArrayLoop(nums);
        Assert.assertFalse(loop);
    }

    /**
     * 使用拓扑排序判断是否存在环
     *
     * @param nums
     * @return
     */
    public boolean circularArrayLoop(int[] nums) {
        int n = nums.length;
        // 首先判断正向是否存在环
        List<List<Integer>> graph = new ArrayList<>();
        int[] inDegrees = new int[n];
        // 初始化
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            // 找到当前节点指向的节点
            int next = ((i + nums[i]) % n + n) % n;
            // 如果方向是反向的先不管，
            // 如果下一跳等于自己也不管，环的长度要大于1
            if (nums[i] <= 0 || i == next) {
                continue;
            }
            // 将当前节点的下一跳添加到图中
            graph.get(i).add(next);
            // 下一节点的入度+1
            inDegrees[next]++;
        }
        if (hasLoopByTopoSort(graph, inDegrees, n)) {
            return true;
        }

        // 再判断反向是否存在环
        graph = new ArrayList<>();
        Arrays.fill(inDegrees, 0);
        // 初始化
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            // 找到当前节点指向的节点
            int next = ((i + nums[i]) % n + n) % n;
            // 如果方向是正向的先不管，前面已经算过了
            // 如果下一跳等于自己也不管，环的长度要大于1
            if (nums[i] >= 0 || i == next) {
                continue;
            }
            // 将当前节点的下一跳添加到图中
            graph.get(i).add(next);
            // 下一节点的入度+1
            inDegrees[next]++;
        }
        if (hasLoopByTopoSort(graph, inDegrees, n)) {
            return true;
        }
        return false;
    }

    /**
     * @param graph
     * @param inDegrees
     * @param nodeCount
     * @return
     */
    @AlgTemplate("拓扑排序")
    private boolean hasLoopByTopoSort(List<List<Integer>> graph, int[] inDegrees, int nodeCount) {
        Queue<Integer> queue = new ArrayDeque<>();
        // 首先将入度为0的节点写入算法
        for (int i = 0; i < nodeCount; i++) {
            if (inDegrees[i] == 0) {
                queue.add(i);
            }
        }
        while (!queue.isEmpty()) {
            // 当前入度为0的点
            int curNode = queue.poll();
            // 找该节点指向的点
            for (Integer node : graph.get(curNode)) {
                // 将被指向的点的入度减一，即断开连接
                inDegrees[node]--;
                if (inDegrees[node] == 0) {
                    // 将入度等于0的点放进队列
                    queue.add(node);
                }
            }
        }
        for (int inDegree : inDegrees) {
            if (inDegree != 0) {
                // 还有节点的入度不等于0，说明有环
                return true;
            }
        }
        return false;
    }


    /**
     * 暴力解法：
     * 因为不确定哪个点是起始点，所以需要假设以某一个点开始，算它是不是起点
     *
     * @param nums
     * @return
     * @link {https://leetcode-cn.com/problems/circular-array-loop/solution/gong-shui-san-xie-yi-ti-shuang-jie-mo-ni-ag05/}
     */
    public boolean circularArrayLoop_Bad(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (checkIsLoopStartPoint(i, nums)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkIsLoopStartPoint(int start, int[] nums) {
        int n = nums.length;
        int cur = start;
        int loopLength = 1;
        // 起始点是否为正数
        boolean startIsPositive = nums[start] > 0;
        while (true) {
            if (loopLength > n) return false;
            int next = ((cur + nums[cur]) % n + n) % n;
            // 判断起始点和循环点的是否都为正数或者负数
            if (nums[start] * nums[next] < 0) {
                return false;
            }
            // 形成了环
            if (next == start) {
                return loopLength > 1;
            }
            // 循环长度+1
            loopLength++;
            // 步数+1
            cur = next;
        }
    }
}
