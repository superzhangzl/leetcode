package zzl.leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author zzl
 * @link {https://leetcode-cn.com/problems/binary-tree-level-order-traversal/}
 */
public class BinaryTreeLevelOrderTraversal {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode right = new TreeNode(20);
        right.left = new TreeNode(15);
        right.right = new TreeNode(7);
        root.left = new TreeNode(9);
        root.right = right;
        /*
            3
           / \
          9  20
            /  \
           15   7
         */
        System.out.println(new BinaryTreeLevelOrderTraversal().levelOrder(root));
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int level = 0;
        while (!queue.isEmpty()) {
            // 取出当前需level要查看的节点
            int currentLevelNodeCount = queue.size();
            // 先给当前层级的level new 一个list
            result.add(new ArrayList<>());
            for (int i = 0; i < currentLevelNodeCount; i++) {
                TreeNode currentLevelNode = queue.remove();
                System.out.println(currentLevelNode);
                System.out.println(currentLevelNode.val);
                result.get(level).add(currentLevelNode.val);
                if (currentLevelNode.left != null) {
                    queue.add(currentLevelNode.left);
                }
                if (currentLevelNode.right != null) {
                    queue.add(currentLevelNode.right);
                }
            }
            level++;
        }
        return result;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return "{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    public void simpleBFS(TreeNode root) {
        if (root == null) {
            return;
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            // 取出当前需level要查看的节点
            int currentLevelNodeCount = queue.size();
            for (int i = 0; i < currentLevelNodeCount; i++) {
                TreeNode currentLevelNode = queue.remove();
                System.out.println(currentLevelNode.val);
                if (currentLevelNode.left != null) {
                    queue.add(currentLevelNode.left);
                }
                if (currentLevelNode.right != null) {
                    queue.add(currentLevelNode.right);
                }
            }
        }
        return;
    }
}
