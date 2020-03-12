package zzl.leetcode;

import zzl.base.TreeNode;
import zzl.util.PrintConsoleUtil;

import java.util.*;

/**
 * 二叉树的中序遍历
 * 进阶: 递归算法
 *
 * @author zzl
 * @tag
 * @link {https://leetcode-cn.com/problems/binary-tree-inorder-traversal/}
 */
public class BinaryTreeInorderTraversal {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        node2.left = new TreeNode(3);
        root.right = node2;
        PrintConsoleUtil.printTreeNode(root);
        System.out.println(new BinaryTreeInorderTraversal().inorderTraversal(root));
    }

    /**
     * 使用堆栈模拟递归
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> result = new Stack<>();
        TreeNode curNode = root;
        while (curNode != null || !stack.isEmpty()) {
            // 一直向左深入，直到叶子节点
            while (curNode != null) {
                stack.push(curNode);
                curNode = curNode.left;
            }
            // 再读取到左叶子节点
            curNode = stack.pop();
            // 添加左值
            result.add(curNode.val);
            // 将索引设置为右
            curNode = curNode.right;
        }
        return result;
    }
}
