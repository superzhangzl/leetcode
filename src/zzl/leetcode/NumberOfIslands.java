package zzl.leetcode;

import org.junit.Assert;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 岛屿数量
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/number-of-islands/}
 * @link {https://leetcode-cn.com/problems/number-of-islands/solution/dfs-bfs-bing-cha-ji-python-dai-ma-java-dai-ma-by-l/}
 * @link {https://blog.csdn.net/liujian20150808/article/details/50848646}
 * @tag
 */
public class NumberOfIslands {
    public static void main(String[] args) {
        char[][] grid = new char[][]{
                {'0', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '1'},
                {'0', '0', '0', '1', '1'}
        };
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
        Assert.assertEquals(new NumberOfIslands().numIslands(grid), 1);
    }

    /**
     * 通过csdn的一篇博客就很好理解，并查集就是在一维数组上进行互相认大哥拉帮结派的过程
     * 留到最后的就是各个门派的个数
     * 对应到本题的就是本来是海上一块一块的土地，相连的就结合在一起成一个小岛
     */
    class UnionFind {
        int count; // # of connected components
        // parent保存当前这个人(位置)的大哥(大哥可能也有大大哥)的位置
        int[] parent;
        // 为了均匀分布，提高查找效率
        // 一个大哥所拥有的二当家的个数
        int[] rank;

        public UnionFind(char[][] grid) { // for problem 200
            count = 0;
            int m = grid.length;
            int n = grid[0].length;
            parent = new int[m * n];
            rank = new int[m * n];
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (grid[i][j] == '1') {
                        // 记录坐标为1的位置
                        // 先设置他的上级就是他自己，后面合并的时候，再更新他的上级，比如a[2]=0，就是说2位置的上级是0位置
                        parent[i * n + j] = i * n + j;
                        // 记录1的个数
                        ++count;
                    }
                    rank[i * n + j] = 0;
                }
            }
        }

        public int find(int i) { // 路径压缩
            // 如果一个人的上级就是他自己，那他就是老大
            // find 就是找大当家一个操作，parent里保存的就是大当家的位置
            if (parent[i] != i) {
                parent[i] = find(parent[i]);
            }
            return parent[i];
        }

        /**
         * 以x为核心进行连接，并判断连接小弟的个数
         *
         * @param x
         * @param y
         */
        public void union(int x, int y) { // union with rank
            int rootx = find(x);
            int rooty = find(y);
            System.out.println(String.format("x=%d, y=%d, rootx=%d, rooty=%d", x, y, rootx, rooty));
            System.out.println(String.format("rank[rootx]=%d, rank[rooty]=%d", rank[rootx], rank[rooty]));
            if (rootx != rooty) {
                if (rank[rootx] > rank[rooty]) {
                    // x的小弟比y的多，那y跟x混，相当于y连接在x上了
                    parent[rooty] = rootx;
                } else if (rank[rootx] < rank[rooty]) {
                    // x的小弟比y的少，那x跟y混，相当于x连接在y上了
                    parent[rootx] = rooty;
                } else {
                    // 两个人小弟的数量一样，就默认把x当做大当家的，即认为y连接在x上了
                    parent[rooty] = rootx;
                    // x这个人的二当家数量+1，吞并一个帮派(rank数量相同的以x进行连接)，就多一个二当家的
                    rank[rootx] += 1;
                }
                // 此处的count计算是，本来将多个分散的节点每进行一次合并，就少一个独立的个体，多一个结合的帮派
                // 剩下的就是帮派的个数
                --count;
            }
            System.out.println("parent=" + print(parent));
            System.out.println("rank  =" + print(rank));
            System.out.println("count =" + count);
            System.out.println();
        }

        String print(int[] arr) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < arr.length; i++) {
                sb.append(arr[i]).append(",");
            }
            return sb.toString();
        }

        public int getCount() {
            return count;
        }
    }

    public int numIslands(char[][] grid) {

        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        int num_islands = 0;
        UnionFind uf = new UnionFind(grid);
        //uf.parent 保存了grid中标为1的一位坐标
        //uf.rank   全为0
        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (grid[r][c] == '1') {
                    grid[r][c] = '0';
                    // 上
                    if (r - 1 >= 0 && grid[r - 1][c] == '1') {
                        uf.union(r * nc + c, (r - 1) * nc + c);
                    }
                    // 下
                    if (r + 1 < nr && grid[r + 1][c] == '1') {
                        uf.union(r * nc + c, (r + 1) * nc + c);
                    }
                    // 左
                    if (c - 1 >= 0 && grid[r][c - 1] == '1') {
                        uf.union(r * nc + c, r * nc + c - 1);
                    }
                    // 右
                    if (c + 1 < nc && grid[r][c + 1] == '1') {
                        uf.union(r * nc + c, r * nc + c + 1);
                    }
                }
            }
        }

        return uf.getCount();
    }

    public int numIslandsPlus(char[][] grid) {
        // 特解
        if (grid == null || grid.length == 0) {
            return 0;
        }
        /*
        思路：以其中的一个点为起始点，从这个点开始执行深度遍历（遍历过的点设置为0），当遍历完后认为是一个小岛
            执行了几次深度遍历，就有几个小岛
         */
        int gridCount = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // 如果遇到一个1，则从这个位置开始DFS
                if (grid[i][j] == '1') {
                    gridCount++;
                    dfs(grid, i, j);
                }
            }
        }
        return gridCount;
    }

    /**
     * 使用队列进行广度优先遍历，思路和DFS一样
     *
     * @param grid
     * @param i
     * @param j
     * @link {https://leetcode-cn.com/problems/number-of-islands/solution/dao-yu-shu-liang-by-leetcode/}
     */
    private void bfs(char[][] grid, int i, int j) {
        int nr = grid.length;
        int nc = grid[0].length;
        grid[i][j] = '0'; // mark as visited
        Queue<Integer> queue = new LinkedList<>();
        queue.add(i * nc + j);
        while (!queue.isEmpty()) {
            int id = queue.remove();
            int row = id / nc;
            int col = id % nc;
            if (row - 1 >= 0 && grid[row - 1][col] == '1') {
                queue.add((row - 1) * nc + col);
                grid[row - 1][col] = '0';
            }
            if (row + 1 < nr && grid[row + 1][col] == '1') {
                queue.add((row + 1) * nc + col);
                grid[row + 1][col] = '0';
            }
            if (col - 1 >= 0 && grid[row][col - 1] == '1') {
                queue.add(row * nc + col - 1);
                grid[row][col - 1] = '0';
            }
            if (col + 1 < nc && grid[row][col + 1] == '1') {
                queue.add(row * nc + col + 1);
                grid[row][col + 1] = '0';
            }
        }

    }

    private void dfs(char[][] grid, int i, int j) {
        // 下标越界，以及不为1
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == '0') {
            return;
        }
        // 首先设置当前(i,j)为0，下次就不用执行到此处了
        grid[i][j] = '0';
        // 上
        dfs(grid, i, j - 1);
        // 下
        dfs(grid, i, j + 1);
        // 左
        dfs(grid, i - 1, j);
        // 右
        dfs(grid, i + 1, j);
    }
}
