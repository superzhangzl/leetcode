package zzl.leetcode;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * @author zzl
 * @link {https://leetcode-cn.com/problems/combination-sum/}
 */
public class CombinationSum {
    public static void main(String[] args) {
        Assert.assertEquals(new CombinationSum().combinationSum(new int[]{2, 3, 6, 7}, 7), Arrays.asList(Arrays.asList(2, 2, 3), Arrays.asList(7)));
    }

    private List<List<Integer>> res = new ArrayList<>();
    private int[] candidates;
    private int len;

    /**
     * @param residue
     * @param start
     * @param pre
     * @link {https://leetcode-cn.com/problems/combination-sum/solution/hui-su-suan-fa-jian-zhi-python-dai-ma-java-dai-m-2/}
     */
    private void findCombinationSum(int residue, int start, Stack<Integer> pre) {
        if (residue == 0) {
            // Java 中可变对象是引用传递，因此需要将当前 path 里的值拷贝出来
            res.add(new ArrayList<>(pre));
            return;
        }
        // 优化添加的代码2：在循环的时候做判断，尽量避免系统栈的深度
        // residue - candidates[i] 表示下一轮的剩余，如果下一轮的剩余都小于 0 ，就没有必要进行后面的循环了
        // 这一点基于原始数组是排序数组的前提，因为如果计算后面的剩余，只会越来越小
        for (int i = start; i < len && residue - candidates[i] >= 0; i++) {
            pre.add(candidates[i]);
            // 深度优先使用的stack，广度优先可以使用queue
            System.out.println("t:" + residue + ", i: " + i + ", data: " + pre);
            // 【关键】因为元素可以重复使用，这里递归传递下去的是 i 而不是 i + 1
            findCombinationSum(residue - candidates[i], i, pre);
            pre.pop();
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        int len = candidates.length;
        if (len == 0) {
            return res;
        }
        // 优化添加的代码1：先对数组排序，可以提前终止判断
        Arrays.sort(candidates);
        this.len = len;
        this.candidates = candidates;
        findCombinationSum(target, 0, new Stack<>());
        return res;
    }
}
