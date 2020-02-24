package zzl.leetcode;

import java.util.*;

/**
 * @author zzl
 * @link {https://leetcode-cn.com/problems/permutations-ii/}
 */
public class PermutationsII {
    public static void main(String[] args) {
        System.out.println(new PermutationsII().permuteUnique(new int[]{1, 1, 2}));
        System.out.println(new PermutationsII().permuteUnique(new int[]{3, 3, 0, 3}));
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) {
            return res;
        }
        Arrays.sort(nums);
        long used = 0;
        Deque<Integer> path = new ArrayDeque<>(len);
        dfs(nums, len, 0, used, path, res);
        return res;
    }

    private void dfs(int[] nums, int len, int depth, long used, Deque<Integer> path, List<List<Integer>> res) {
        if (depth == len) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < len; i++) {
            // 判断当前的nums[i]和nums[i-1]相等，且nums[i-1]没使用
            // 此处要求已经排序
            if (i > 0 && nums[i] == nums[i - 1] && (used >> (i - 1) & 1) == 0) {
                continue;
            }
            if ((used >> i & 1) == 0) {
                path.addLast(nums[i]);
                used ^= (1 << i);
                dfs(nums, len, depth + 1, used, path, res);
                used ^= (1 << i);
                path.removeLast();
            }

        }

    }

}
