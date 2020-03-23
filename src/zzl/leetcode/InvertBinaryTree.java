package zzl.leetcode;

import zzl.base.TreeNode;
import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

/**
 * 翻转二叉树
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/invert-binary-tree/}
 */
public class InvertBinaryTree {
    public static void main(String[] args) {
        TreeNode treeNode = GenerateUtil.generateTreeNode("4,2,7,1,3,6,9", ",");
        PrintConsoleUtil.printTreeNode(treeNode);
        PrintConsoleUtil.printTreeNode(new InvertBinaryTree().invertTree(treeNode));
    }

    /**
     * 递归翻转二叉树
     * 使用临时变量重建二叉树，不要在原基础上修改。时间复杂度怎么改都是O(n)
     * 官方示例给出了另一种解法：
     * if ( root = = null) {
     * return null;
     * }
     * TreeNode right = invertTree(root.right);
     * TreeNode left = invertTree(root.left);
     * root.left = right;
     * root.right = left;
     * return root;
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        // 使用临时变量来存储root的值，不要再root的基础上进行修改，由于引用会导致赋值错误
        TreeNode temp = new TreeNode(root.val);
        temp.left = invertTree(root.right);
        temp.right = invertTree(root.left);
        return temp;
    }
}
