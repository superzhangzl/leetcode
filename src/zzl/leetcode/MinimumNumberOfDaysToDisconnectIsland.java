package zzl.leetcode;

import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 使陆地分离的最少天数
 * TODO: Tarjan 高级算法
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/minimum-number-of-days-to-disconnect-island/}
 */
public class MinimumNumberOfDaysToDisconnectIsland {

    public static void main(String[] args) {
        int[][] area = GenerateUtil.generateBinaryIntArray("0,1,1,0\n0,1,1,0\n0,0,0,0");
        PrintConsoleUtil.printArray(area);
        int minDays = new MinimumNumberOfDaysToDisconnectIsland().minDays(area);
        System.out.println(minDays);

        area = GenerateUtil.generateBinaryIntArray("1,1\n1,0");
        PrintConsoleUtil.printArray(area);
        minDays = new MinimumNumberOfDaysToDisconnectIsland().minDays(area);
        System.out.println(minDays);
    }

    // 只有上下左右相连才认为是连续的
    int[][] position = {
            {0, 1},
            {0, -1},
            {1, 0},
            {-1, 0},
    };

    private boolean isSeparate(int[][] grid) {
        int x = 0, y = 0;
        int cnt = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                // 如果是水域就跳过
                if (grid[i][j] == 0) {
                    continue;
                }
                // 统计陆地的个数
                cnt++;
                // 刷新入口，即陆地最右下角的位置
                x = i;
                y = j;
            }
        }
        // 如果都是水域，默认就是分离的
        if (cnt == 0) {
            return true;
        }
        Deque<Pair> q = new ArrayDeque<>();
        boolean[][] mark = new boolean[grid.length][grid[0].length];
        // 第一个点加入队列进行bfs
        q.push(new Pair(x, y));
        mark[x][y] = true;
        cnt--;
        // 找到一片完整的陆地
        while (!q.isEmpty()) {
            Pair f = q.pop();
            for (int[] pos : position) {
                int nx = pos[0] + f.x;
                int ny = pos[1] + f.y;
                // 下一个点是陆地而且未被访问
                if (0 <= nx && nx < grid.length && 0 <= ny && ny < grid[0].length && grid[nx][ny] == 1) {
                    if (mark[nx][ny]) {
                        continue;
                    }
                    q.push(new Pair(nx, ny));
                    mark[nx][ny] = true;
                    cnt--;
                }
            }
        }
        // 看当前一片完整的陆地的面积和实际的陆地面积是否一致
        // 不一致则认为分离了
        return cnt != 0;
    }

    /**
     * 参考链接，根据题意至多只需要将顶角上相连的两个陆地去掉即可完成分离条件
     *
     * @param grid
     * @return
     * @link {https://leetcode-cn.com/problems/minimum-number-of-days-to-disconnect-island/solution/nao-jin-ji-zhuan-wan-zui-duo-fen-liang-ci-on4-by-t/}
     */
    public int minDays(int[][] grid) {
        if (isSeparate(grid)) {
            return 0;
        }
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                // 如果是水域就跳过
                if (grid[i][j] == 0) {
                    continue;
                }
                // 先将当前位置的陆地变成水域
                grid[i][j] = 0;

                // PrintConsoleUtil.printArray(grid);

                // 然后判断消失一个陆地的情况下能不能满足分离条件
                if (isSeparate(grid)) {
                    return 1;
                }
                // 将当前位置恢复成陆地
                grid[i][j] = 1;
            }
        }
        return 2;
    }

    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
