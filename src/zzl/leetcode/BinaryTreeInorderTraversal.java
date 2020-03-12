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
     * 本方法中，我们使用一种新的数据结构：线索二叉树。方法如下：
     * Step 1: 将当前节点current初始化为根节点
     * Step 2: While current不为空，
     * ----若current没有左子节点
     * --------a. 将current添加到输出
     * --------b. 进入右子树，亦即, current = current.right
     * ----否则
     * --------a. 在current的左子树中，令current成为最右侧节点的右子节点
     * --------b. 进入左子树，亦即，current = current.left
     *
     * @param root
     * @return
     * @link {https://leetcode-cn.com/problems/binary-tree-inorder-traversal/solution/er-cha-shu-de-zhong-xu-bian-li-by-leetcode/}
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        TreeNode current = root;
        TreeNode pre;
        while (current != null) {
            if (current.left == null) {
                result.add(current.val);
                current = current.right;
            } else {
                pre = current.left;
                while (pre.right != null)
                    pre = pre.right;
                pre.right = current;
                TreeNode temp = current;
                current = current.left;
                temp.left = null;
            }
            PrintConsoleUtil.printTreeNode(root);
        }
        return result;
    }

    /**
     * 使用堆栈模拟递归
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversalStack(TreeNode root) {
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
