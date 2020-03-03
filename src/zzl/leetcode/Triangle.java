package zzl.leetcode;

import org.junit.Assert;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

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
        Assert.assertEquals(new Triangle().minimumTotalWithDfs(input), 11);

    }

    /**
     * 使用DFS时间会超限制
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
            // v2.0 直接选最小的来传
            path.add(list.get(i));
            dfs(triangle, depth + 1, level, i, path);
            path.remove(path.size() - 1);

        }

    }
}
