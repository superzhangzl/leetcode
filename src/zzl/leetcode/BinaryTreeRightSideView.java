package zzl.leetcode;

import zzl.base.TreeNode;
import zzl.util.PrintTreeUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树的右视图
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/binary-tree-right-side-view/}
 */
public class BinaryTreeRightSideView {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        node2.left = new TreeNode(4);
        node2.right = new TreeNode(5);
        node3.left = new TreeNode(6);
        node3.right = new TreeNode(7);
        root.left = node2;
        root.right = node3;
        PrintTreeUtil.print(root);
        System.out.println(new BinaryTreeRightSideView().rightSideView(root));
    }

    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> stack = new LinkedList<>();
        print(root, 0, stack);
        return stack;
    }

    private void print(TreeNode root, int level, List<Integer> path) {
        if (root == null) {
            return;
        }
        if (path.size() > level && path.get(level) != null) {
            path.set(level, root.val);
        } else {
            // 第一次就添加
            path.add(level, root.val);
        }
        print(root.left, level + 1, path);
        print(root.right, level + 1, path);
    }
}
