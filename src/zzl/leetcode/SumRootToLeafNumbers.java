package zzl.leetcode;

import org.junit.Assert;
import zzl.base.TreeNode;
import zzl.util.PrintTreeUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author zzl
 * @link {https://leetcode-cn.com/problems/sum-root-to-leaf-numbers/}
 */
public class SumRootToLeafNumbers {
    public static void main(String[] args) {
        // [1,2,3]
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        PrintTreeUtil.print(root);
        Assert.assertEquals(new SumRootToLeafNumbers().sumNumbers(root), 25);
        // [4,9,0,5,1]
        root = new TreeNode(4);
        TreeNode node9 = new TreeNode(9);
        node9.left = new TreeNode(5);
        node9.right = new TreeNode(1);
        root.right = new TreeNode(0);
        root.left = node9;
        PrintTreeUtil.print(root);
        Assert.assertEquals(new SumRootToLeafNumbers().sumNumbers(root), 1026);
    }

    private List<Integer> result = new ArrayList<>();

    /**
     * 这种传统解法的时间和内存消耗都比较高
     * <p>
     * T_T
     *
     * @param root
     * @return
     */
    public int sumNumbers(TreeNode root) {
        dfs(root, new StringBuilder());
        return result.stream().mapToInt(Integer::intValue).sum();
    }

    private void dfs(TreeNode root, StringBuilder stack) {
        if (root == null) {
            return;
        }
        // 先序遍历
        stack.append(root.val);
        if (root.left != null || root.right != null) {
            dfs(root.left, stack);
            dfs(root.right, stack);
        } else {
            // 打印由前序遍历的来的，所有的root节点到叶子节点的路径
            result.add(Integer.parseInt(stack.toString()));
        }
        stack.deleteCharAt(stack.length() - 1);
    }
}
