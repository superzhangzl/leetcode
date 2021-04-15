package zzl.leetcode;

import org.junit.Assert;
import zzl.base.TreeNode;
import zzl.util.GenerateUtil;

/**
 * 二叉树中的最大路径和
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/}
 */
public class BinaryTreeMaximumPathSum {
    public static void main(String[] args) {
        TreeNode root;
        int maxPathSum;
        //
        root = GenerateUtil.generateTreeNode("1,2,3");
        maxPathSum = new BinaryTreeMaximumPathSum().maxPathSum(root);
        Assert.assertEquals(maxPathSum, 6);
        //
        root = GenerateUtil.generateTreeNode("-10,9,20,null,null,15,7");
        maxPathSum = new BinaryTreeMaximumPathSum().maxPathSum(root);
        Assert.assertEquals(maxPathSum, 42);
    }

    /**
     * dfs
     * 注：只返回最大值，不打印路径
     *
     * @param root
     * @return
     * @link {https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/solution/er-cha-shu-zhong-de-zui-da-lu-jing-he-by-ikaruga/}
     */
    public int maxPathSum(TreeNode root) {
        dfs(root);
        return max;
    }

    int max = Integer.MIN_VALUE;

    public int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        //计算左边分支最大值，左边分支如果为负数还不如不选择
        int leftMax = Math.max(0, dfs(root.left));
        //计算右边分支最大值，右边分支如果为负数还不如不选择
        int rightMax = Math.max(0, dfs(root.right));
        //left->root->right 作为路径与已经计算过历史最大值做比较
        max = Math.max(max, root.val + leftMax + rightMax);
        // 返回经过root的单边最大分支给当前root的父节点计算使用
        // 如果到叶子节点的时候，叶子节点没有左右子节点，那肯定返回0；如果此时叶子节点是负数，就会通过递归传递到上一层父节点
        // 上一层父节点会和0进行比较，如果比0还小，表示该节点不选择到路径里
        return root.val + Math.max(leftMax, rightMax);
    }
}
