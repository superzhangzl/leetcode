package zzl.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 组合总和 III
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/combination-sum-iii/}
 */
public class CombinationSumIII {
    public static void main(String[] args) {
        System.out.println(new CombinationSumIII().combinationSum3(3, 7));
        System.out.println(new CombinationSumIII().combinationSum3(3, 9));
    }

    List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> combinationSum3(int k, int n) {
        dfs(1, n, 0, k, 0, new LinkedList<>());
        return result;
    }

    private void dfs(int start, int target, int depth, int depthLimit, int sum, List<Integer> path) {
        if (depth == depthLimit) {
            // 增加sum字段，就不用每次再累加path了
            if (sum == target) {
                result.add(new ArrayList(path));
            }
            return;
        }
        for (int i = start; i <= target && i <= 9; i++) {
            // 从i开始都大于target了就直接中断跳过
            if (sum + i > target) {
                break;
            }
            // 再累加一级都小于target了，就不用进行递归了，直接执行下一个数
            if (sum + i < target && depth + 1 == depthLimit) {
                continue;
            }
            path.add(i);
            // start = i + 1 保证不重复
            dfs(i + 1, target, depth + 1, depthLimit, sum + i, path);
            path.remove(path.size() - 1);
        }
    }
}
