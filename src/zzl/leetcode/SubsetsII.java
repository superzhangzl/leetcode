package zzl.leetcode;

import java.util.*;

/**
 * @author zzl
 * @link {https://leetcode-cn.com/problems/subsets-ii/}
 */
public class SubsetsII {
    public static void main(String[] args) {
        System.out.println(new SubsetsII().subsetsWithDup(new int[]{1, 2, 2}));
//        System.out.println(new SubsetsII().subsetsWithDup(new int[]{1, 2, 2, 3}));
//        System.out.println(new SubsetsII().subsetsWithDup(new int[]{1, 1, 2, 2}));
//[
//  [2],
//  [1],
//  [1,2,2],
//  [2,2],
//  [1,2],
//  []
//]
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        // 特殊情况直接返回
        if (nums.length == 0) {
            return Collections.emptyList();
        }
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        dfs(nums, 0, new ArrayDeque<>(), res);
        return res;
    }    // 使用dfs来遍历树的每一条不重复路径，貌似时间复杂度还是有些高来着

    public void dfs(int[] nums, int start, Deque<Integer> path, List<List<Integer>> res) {
        // 首先把path塞进去，然后再执行递归，这样每次递归都会把当前深度的path放进去
        System.out.println(String.format(" data=%s", path));
        res.add(new ArrayList<>(path));
        for (int i = start; i < nums.length; i++) {
            // 注：此处的判断条件是i>start，即当前数字上一次出现过
            // 错误写法：i>0，会导致一开始判断的数值(i=start)重复时就跳过，实际上该数字是可用的
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            // 此处的是有序的，每个用一次，可要可不要且有重复，所以不需要通过swap交换值
            path.addLast(nums[i]);
            // 使用i + 1 不算当前的就不会重复，需要过滤后续的
            dfs(nums, i + 1, path, res);
            path.removeLast();
        }
    }
}
