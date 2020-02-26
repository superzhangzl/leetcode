package zzl.leetcode;

import java.util.*;

/**
 * @author zzl
 * @link {https://leetcode-cn.com/problems/combinations/}
 */
public class Combinations {
    public static void main(String[] args) {
        System.out.println(new Combinations().combine(4, 2));
    }

    /**
     * C_{n}^{k}的所有组合的个数
     *
     *
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
        }
        int maxDepth = k;
        // 特殊情况直接返回
        if (n == 0 || k == 0 || k > n) {
            return Collections.emptyList();
        }
        List<List<Integer>> res = new ArrayList<>();
        dfs(nums, 0, 0, maxDepth, new ArrayDeque<>(), res);
        return res;
    }

    public void dfs(int[] nums, int start, int depth, int maxDepth, Deque<Integer> path, List<List<Integer>> res) {
        if (depth == maxDepth) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            // 剪枝，因为存在交换情况，当前数字小于上一个的时候说明重复了，比如1,2已有，就不需要2,1了，对于2,1的情况就跳过即可
            if (i > 0 && nums[i] < nums[i - 1] ) {
                continue;
            }
            path.addLast(nums[i]);
            swap(nums, start, i);
            // 使用i + 1 不算当前的就不会重复，需要过滤后续的
            dfs(nums, i + 1, depth + 1, maxDepth, path, res);
            swap(nums, start, i);
            path.removeLast();
        }
        System.out.println();
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
