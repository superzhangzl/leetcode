package zzl.leetcode;

import org.junit.Assert;
import zzl.base.TreeNode;
import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

/**
 * 对称二叉树
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/symmetric-tree/}
 */
public class SymmetricTree {
    public static void main(String[] args) {
        TreeNode root = GenerateUtil.generateTreeNode("1,2,2,3,4,4,3", ",");
        PrintConsoleUtil.printTreeNode(root);
        Assert.assertEquals(new SymmetricTree().isSymmetric(root), true);
        root = GenerateUtil.generateTreeNode("1,2,2,null,3,null,3", ",");
        PrintConsoleUtil.printTreeNode(root);
        Assert.assertEquals(new SymmetricTree().isSymmetric(root), false);

    }

    public boolean isSymmetric(TreeNode root) {
        return isMirror(root, root);
    }

    /**
     * 镜像位置
     * 1. 它们的两个根结点具有相同的值。
     * 2. 每个树的右子树都与另一个树的左子树镜像对称。
     *
     * @param t1
     * @param t2
     * @return
     */
    public boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return (t1.val == t2.val)
                && isMirror(t1.right, t2.left)
                && isMirror(t1.left, t2.right);
    }
}
