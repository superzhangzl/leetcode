package zzl.leetcode;

import java.util.*;

/**
 * @author zzl
 * @link {https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/}
 */
public class BinaryTreeZigzagLevelOrderTraversal {
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
        System.out.println(new BinaryTreeZigzagLevelOrderTraversal().zigzagLevelOrder(root));
        TreeNode root1 = new TreeNode(1);
        TreeNode left1 = new TreeNode(2);
        left1.left = new TreeNode(4);
        TreeNode right1 = new TreeNode(3);
        right1.right = new TreeNode(5);
        root1.left = left1;
        root1.right = right1;
        /*
                1
               / \
              2   3
             /      \
            4        5
         */
        System.out.println(new BinaryTreeZigzagLevelOrderTraversal().zigzagLevelOrder(root1));
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return new LinkedList<>();
        }
        // 使用linkedList，提高头插效率
        List<List<Integer>> result = new LinkedList<>();
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        int level = 0;
        while (!queue.isEmpty()) {
            // 取出当前需level要查看的节点
            // 先给当前层级的level new 一个list
            int currentLevelNodeCount = queue.size();
            result.add(new LinkedList<>());
            for (int i = currentLevelNodeCount - 1; i >= 0; i--) {
                TreeNode currentLevelNode = queue.remove();
                System.out.println(currentLevelNode);
                System.out.println(currentLevelNode.val);
                // 根据不同的层级，把数字从头部添加或者从尾部添加
                if (level % 2 == 0) {
                    result.get(level).add(currentLevelNode.val);
                } else {
                    result.get(level).add(0, currentLevelNode.val);
                }
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
}
