package zzl.leetcode;

import org.junit.Assert;
import zzl.base.TreeNode;
import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

import java.util.ArrayList;

/**
 * 二叉树的最近公共祖先
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/}
 */
public class LowestCommonAncestorOfABinaryTree {
    public static void main(String[] args) {
        TreeNode treeNode = GenerateUtil.generateTreeNode("3,5,1,6,2,0,8,null,null,7,4", ",");
        PrintConsoleUtil.printTreeNode(treeNode);
        TreeNode commonAncestor = new LowestCommonAncestorOfABinaryTree().lowestCommonAncestor(treeNode, new TreeNode(6), new TreeNode(1));
        Assert.assertEquals(commonAncestor.val, 5);

        treeNode = GenerateUtil.generateTreeNode("-1,0,3,-2,4,null,null,8", ",");
        PrintConsoleUtil.printTreeNode(treeNode);
        commonAncestor = new LowestCommonAncestorOfABinaryTree().lowestCommonAncestor(treeNode, new TreeNode(3), new TreeNode(8));
        Assert.assertEquals(commonAncestor.val, -1);
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        found = false;
        ArrayList<Integer> path1 = pathToTarget(root, p, new ArrayList<>());
        found = false;
        ArrayList<Integer> path2 = pathToTarget(root, q, new ArrayList<>());
        int index = 0;
        while (index < path1.size() && index < path2.size() && path1.get(index).equals(path2.get(index))) {
            index++;
        }
        System.out.println(path1);
        System.out.println(path2);
        return new TreeNode(path1.get(index - 1));
    }

    boolean found = false;

    private ArrayList<Integer> pathToTarget(TreeNode root, TreeNode target, ArrayList<Integer> sb) {
        if (root.val == target.val) {
            found = true;
            sb.add(target.val);
            return sb;
        }
        if (!found) {
            sb.add(root.val);
        }
        if (root.left != null) {
            pathToTarget(root.left, target, sb);
        }
        if (root.right != null) {
            pathToTarget(root.right, target, sb);
        }
        if (!found) {
            sb.remove(sb.size() - 1);
        }
        return sb;
    }


}
