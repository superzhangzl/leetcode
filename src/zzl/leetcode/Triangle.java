package zzl.leetcode;

import org.junit.Assert;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 三角形最小路径和
 * 说明：
 * 如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/triangle/}
 */
public class Triangle {
    public static void main(String[] args) {
        /*
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
         */
        List<List<Integer>> input = Arrays.asList(
                Arrays.asList(2),
                Arrays.asList(3, 4),
                Arrays.asList(6, 5, 7),
                Arrays.asList(4, 1, 8, 3)
        );
        Assert.assertEquals(new Triangle().minimumTotalPlus(input), 11);
        input = Arrays.asList(
                Arrays.asList(-1),
                Arrays.asList(2, 3),
                Arrays.asList(1, -1, -3)
        );
        Assert.assertEquals(new Triangle().minimumTotalPlus(input), -1);

    }

    public int minimumTotalPlus(List<List<Integer>> triangle) {
        // 特判
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        // dp最大长度==triangle底边长度
        // 题意：只使用 O(n) 的额外空间（n 为三角形的总行数）
        int[] dp = new int[triangle.size()];
        dp[0] = triangle.get(0).get(0);

        // prev暂存dp[i-1][j-1],cur暂存dp[i-1][j]
        // 即当前行以上的都已经没用了，就不需要再额外保存
        int prev = 0, cur;
        for (int i = 1; i < triangle.size(); i++) {
            //对每一行的元素进行推导
            List<Integer> rows = triangle.get(i);
            for (int j = 0; j <= i; j++) {
                cur = dp[j];
                if (j == 0) {
                    // 最左端特殊处理
                    dp[j] = cur + rows.get(j);
                } else if (j == i) {
                    // 最右端特殊处理
                    dp[j] = prev + rows.get(j);
                } else {
                    dp[j] = Math.min(cur, prev) + rows.get(j);
                }
                prev = cur;
            }
        }
        // i~[0, 3]
        // [2, 0, 0, 0]
        // [5, 6, 0, 0]
        // [11, 10, 13, 0]
        // [15, 11, 18, 16]

        int res = Integer.MAX_VALUE;
        // dp最后一行记录了最小路径
        for (int i = 0; i < triangle.size(); i++) {
            System.out.print(dp[i] + " ");
            res = Math.min(res, dp[i]);
        }
        System.out.println();
        return res;
    }

    /**
     * @param triangle
     * @return
     * @link {https://leetcode-cn.com/problems/triangle/solution/javadong-tai-gui-hua-si-lu-yi-ji-dai-ma-shi-xian-b/}
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        // 特判
        if (triangle == null || triangle.size() == 0) {
            return 0;
        }
        int row = triangle.size();
        int column = triangle.get(row - 1).size();

        int[][] dp = new int[row][column];
        dp[0][0] = triangle.get(0).get(0);
        int res = Integer.MAX_VALUE;

        for (int i = 1; i < row; i++) {
            //对每一行的元素进行推导
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    // 最左端特殊处理
                    dp[i][j] = dp[i - 1][j] + triangle.get(i).get(j);
                } else if (j == i) {
                    // 最右端特殊处理
                    dp[i][j] = dp[i - 1][j - 1] + triangle.get(i).get(j);
                } else {
                    // 状态转移方程
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + triangle.get(i).get(j);
                }
            }
        }

        // dp最后一行记录了最小路径
        for (int i = 0; i < column; i++) {
            res = Math.min(res, dp[row - 1][i]);
        }
        return res;
    }


    /**
     * DFS递归遍历每个合法路径再求最小值的时候，数据量极大的时候会超时
     *
     * @param triangle
     * @return
     */
    @Deprecated
    public int minimumTotalWithDfs(List<List<Integer>> triangle) {
        int level = triangle.size();
        List<Integer> path = new LinkedList<>();
        path.add(triangle.get(0).get(0));
        dfs(triangle, 1, level, 0, path);
        return minPathSum;
    }

    private int minPathSum = Integer.MAX_VALUE;

    private void dfs(List<List<Integer>> triangle, int depth, int level, int suffix, List<Integer> path) {
        if (depth == level) {
            // 到底了
//            System.out.println(path);
            int sum = path.stream().mapToInt(Integer::intValue).sum();
            if (sum < minPathSum) {
                minPathSum = sum;
            }
            return;
        }
        List<Integer> list = triangle.get(depth);
        for (int i = 0; i < list.size(); i++) {
            // 剪枝，即当前节点最下面的
            if (i < suffix || i - suffix > 1) {
                continue;
            }
            path.add(list.get(i));
            dfs(triangle, depth + 1, level, i, path);
            path.remove(path.size() - 1);

        }

    }
}
