package zzl.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 无重复字符串的排列组合
 * 计算某字符串的所有排列组合，字符串每个字符均不相同。
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/permutation-i-lcci/}
 */
public class PermutationILcci {
    public static void main(String[] args) {
        String input = "qwe";
        System.out.println(Arrays.asList(new PermutationILcci().permutation(input)));
    }

    /**
     * 全排列，dfs + 剪枝，这类题型的思路比较一致
     *
     * @param S
     * @return
     */
    public String[] permutation(String S) {
        int len = S.length();
        if (len == 0) {
            return new String[0];
        }
        char[] chars = S.toCharArray();
        StringBuilder path = new StringBuilder();
        List<String> res = new ArrayList<>();
        dfsWithSwap(chars, 0, len, 0, path, res);
        // 参数为0即可，只是标识一下返回消息类型
        return res.toArray(new String[0]);
    }

    /**
     * 思路参考
     * {@link Permutations}
     *
     * @param chars
     * @param start
     * @param len
     * @param depth
     * @param path
     * @param res
     */
    private void dfsWithSwap(char[] chars, int start, int len, long depth, StringBuilder path, List<String> res) {
        if (depth == len) {
            res.add(path.toString());
        }
        for (int i = start; i < len; i++) {
            path.append(chars[i]);
            swap(chars, start, i);
            dfsWithSwap(chars, start + 1, len, depth + 1, path, res);
            swap(chars, start, i);
            path.deleteCharAt(path.length() - 1);
        }
    }

    private void swap(char[] nums, int i, int j) {
        char temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
