package zzl.leetcode;

import java.util.*;

/**
 * @author zzl
 * @link {https://leetcode-cn.com/problems/subsets/}
 */
public class Subsets {

    public static void main(String[] args) {
        System.out.println(new Subsets().subsets(new int[]{1, 2, 3}));
    }

    public List<List<Integer>> subsets(int[] nums) {

        // 特殊情况直接返回
        if (nums.length == 0) {
            return Collections.emptyList();
        }
        List<List<Integer>> res = new ArrayList<>();
        dfs(nums, 0, new ArrayDeque<>(), res);
        return res;
    }

    // 使用dfs来遍历树的每一条不重复路径，貌似时间复杂度还是有些高来着
    public void dfs(int[] nums, int start, Deque<Integer> path, List<List<Integer>> res) {
        // 首先把path塞进去，然后再执行递归，这样每次递归都会把当前深度的path放进去
        res.add(new ArrayList<>(path));
        for (int i = start; i < nums.length; i++) {
            path.addLast(nums[i]);
            swap(nums, start, i);
            // 使用i + 1 不算当前的就不会重复，需要过滤后续的
            dfs(nums, i + 1, path, res);
            swap(nums, start, i);
            path.removeLast();
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
