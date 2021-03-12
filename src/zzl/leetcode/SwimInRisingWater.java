package zzl.leetcode;

import org.junit.Assert;
import zzl.util.GenerateUtil;

/**
 * 水位上升的泳池中游泳
 * <p>
 * TODO: 二分+dfs
 * TODO: Dijkstra 算法
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/swim-in-rising-water/}
 */
public class SwimInRisingWater {
    public static void main(String[] args) {
        int[][] grid;
        int swimInWater;
        //=====
        grid = GenerateUtil.generateBinaryIntArrayBetter("[[0,2],[1,3]]");
        swimInWater = new SwimInRisingWater().swimInWater(grid);
        Assert.assertEquals(swimInWater, 3);

    }

    private int N;

    public static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    /**
     * 并查集，永远滴神
     * 由于题目要我们找的是最少等待时间，可以模拟下雨的过程，把网格抽象成一个无权图，每经过一个时刻，就考虑此时和雨水高度相等的单元格，
     * 考虑这个单元格的上、下、左、右、四个方向，并且高度更低的单元格，把它们在并查集中进行合并。
     * <p>
     *
     * @param grid
     * @return
     * @link {https://leetcode-cn.com/problems/swim-in-rising-water/solution/shui-wei-shang-sheng-de-yong-chi-zhong-y-862o/}
     */
    public int swimInWater(int[][] grid) {
        this.N = grid.length;

        int len = N * N;
        // 下标：方格的高度，值：对应在方格中的坐标
        int[] index = new int[len];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                index[grid[i][j]] = getIndex(i, j);
            }
        }
//        Arrays.sort(index);
        UnionFindSet unionFind = new UnionFindSet(len);
        for (int i = 0; i < len; i++) {
            int x = index[i] / N;
            int y = index[i] % N;

            for (int[] direction : DIRECTIONS) {
                int newX = x + direction[0];
                int newY = y + direction[1];
                // 把低于水平面以下点都连通起来
                if (inArea(newX, newY) && grid[newX][newY] <= i) {
                    unionFind.union(index[i], getIndex(newX, newY));
                }
                // 当起点和终点连接起来了以后表示路径打通了
                if (unionFind.isConnected(0, len - 1)) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 将二维坐标转为一维坐标
     *
     * @param x
     * @param y
     * @return
     */
    private int getIndex(int x, int y) {
        return x * N + y;
    }

    /**
     * 越界校验
     *
     * @param x
     * @param y
     * @return
     */
    private boolean inArea(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }

    private class UnionFindSet {
        private int[] parent;

        public UnionFindSet(int n) {
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            while (x != parent[x]) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        public boolean isConnected(int x, int y) {
            return find(x) == find(y);
        }

        public void union(int p, int q) {
            if (isConnected(p, q)) {
                return;
            }
            parent[find(p)] = find(q);
        }
    }

}


