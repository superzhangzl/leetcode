package zzl.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author zzl
 * @link {https://leetcode-cn.com/problems/permutations/}
 */
public class Permutations {

    public static void main(String[] args) {
        System.out.println(1 ^ 1);
        System.out.println(1 ^ 0);
        System.out.println(0 ^ 1);
        System.out.println(0 ^ 0);
        System.out.println(new Permutations().permute(new int[]{1, 2, 3}));
    }

    /**
     * 重点：数组元素不重复
     *
     * @param nums
     * @return
     * @link {https://leetcode-cn.com/problems/permutations/solution/hui-su-suan-fa-python-dai-ma-java-dai-ma-by-liweiw/}
     */
    public List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>(factorial(len));
        if (len == 0) {
            return res;
        }

        long used = 0;
        Deque<Integer> path = new ArrayDeque<>(len);
//        dfs(nums, len, 0, path, used, res);
        dfsWithSwap(nums, 0, len, 0, path, res);
        return res;
    }

    private int factorial(int n) {
        int res = 1;
        for (int i = 2; i <= n; i++) {
            res *= i;
        }
        return res;
    }

    private void dfs(int[] nums, int len, int depth,
                     Deque<Integer> path, long used,
                     List<List<Integer>> res) {
        if (depth == len) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < len; i++) {
            // 使用位运算来判断当前元素是否使用过
            // used右移i位后的位数和1做&操作会得到0/1，0表示未使用过
            // 此处的时间复杂度还是会对nums遍历，还有一个解法是交换元素的位置，来将uesd的元素放在前面来减少遍历范围
            if (((used >> i) & 1) == 0) {
                path.addLast(nums[i]);
                // 进行亦或操作，如果是0就设置为1
                used ^= (1 << i);
                dfs(nums, len, depth + 1, path, used, res);
                // 如果为1就设置为0
                used ^= (1 << i);
                path.removeLast();
            }
        }
    }

    private void dfsWithSwap(int[] nums, int start, int len, long depth, Deque<Integer> path, List<List<Integer>> res) {
        if (depth == len) {
            res.add(new ArrayList<>(path));
        }
        for (int i = start; i < len; i++) {
            path.addLast(nums[i]);
            // 全排列需要保证每个都使用一遍，而且起始是从start开始，而不是从0开始的
            // 需要将没使用过的swap交换到后面
            swap(nums, start, i);
            dfsWithSwap(nums, start + 1, len, depth + 1, path, res);
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
