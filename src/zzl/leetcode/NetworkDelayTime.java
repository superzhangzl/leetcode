package zzl.leetcode;

import org.junit.Assert;
import zzl.base.annotation.Level;
import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

import java.util.Arrays;

import static zzl.base.enums.Difficulty.*;

/**
 * 网络延迟时间
 *
 * @author zzl
 * @date 2021-08-03
 * @link {https://leetcode-cn.com/problems/network-delay-time/}
 */
@Level(MEDIUM)
public class NetworkDelayTime {
    public static void main(String[] args) {
        int[][] times;
        int delayTime;
        //
        times = GenerateUtil.generateBinaryIntArrayBetter("[[2,1,1],[2,3,1],[3,4,1]]");
        delayTime = new NetworkDelayTime().networkDelayTime(times, 4, 2);
        Assert.assertEquals(delayTime, 2);
        //
        times = GenerateUtil.generateBinaryIntArrayBetter("[[1,2,1]]");
        delayTime = new NetworkDelayTime().networkDelayTime(times, 2, 2);
        Assert.assertEquals(delayTime, -1);

    }

    /**
     * 题目实际是求节点 KK 到其他所有点中最远的距离，那么首先需要求出节点 KK 到其他所有点的最短路，然后取最大值即可。
     * 迪杰斯特拉(Dijkstra)算法是典型最短路径算法，用于计算一个节点到其他节点的最短路径
     * <p>
     * 可以作为Dijkstra算法的模板
     *
     * @param times
     * @param n
     * @param k
     * @return
     * @link {https://leetcode-cn.com/problems/network-delay-time/solution/gtalgorithm-dan-yuan-zui-duan-lu-chi-tou-w3zc/}
     */
    public int networkDelayTime(int[][] times, int n, int k) {
        int INF = Integer.MAX_VALUE / 2;
        int[][] grid = new int[n][n];
        // 先将所有的边设置为极大值
        for (int i = 0; i < n; ++i) {
            Arrays.fill(grid[i], INF);
        }

        for (int i = 0; i < times.length; i++) {
            // 让边的序号从0开始，不影响权值计算
            int src = times[i][0] - 1;
            int dst = times[i][1] - 1;
            int weight = times[i][2];
            grid[src][dst] = weight;
        }
        // 计算从原点k开始到其他点的最小距离
        int[] distWeight = new int[n];
        // 其他点初始默认最大
        Arrays.fill(distWeight, INF);
        // 自己到自己的距离为0
        distWeight[k - 1] = 0;

        // 记录其他点是否被更新过？？？？
        boolean[] updated = new boolean[n];

        // 首先计算从原点k开始到其他每个点的最小距离
        for (int i = 0; i < n; i++) {
            // 在还未确定最短路的点中，寻找距离最小的点
            // 第一轮开始肯定是源点，然后是除源点以外距离最小的点
            int x = -1; // 节点编号
            for (int y = 0; y < n; y++) {
                if (!updated[y] && (x == -1 || distWeight[y] < distWeight[x])) {
                    x = y;
                }
                PrintConsoleUtil.printParams("x", x, "y", y);
                PrintConsoleUtil.printArray(distWeight);
//                System.out.println();
            }
            System.out.print("===> ");
            PrintConsoleUtil.printParams("x", x);
            // 用该点作为跳板，更新所有其他点的距离
            updated[x] = true;
            for (int y = 0; y < n; ++y) {
                // 如果跳板点无法访问到其他点时，相加以后的距离会更大，即grid[x][y]很大
                distWeight[y] = Math.min(distWeight[y], distWeight[x] + grid[x][y]);
            }
        }

        int maxWeight = Arrays.stream(distWeight).max().getAsInt();
        return maxWeight == INF ? -1 : maxWeight;
    }
}
