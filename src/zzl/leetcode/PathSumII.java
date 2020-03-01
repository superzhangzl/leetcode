package zzl.leetcode;

import zzl.base.TreeNode;

import java.util.*;

/**
 * @author zzl
 * @link {https://leetcode-cn.com/problems/path-sum-ii/}
 */
public class PathSumII {
    public static void main(String[] args) {
        /*
               5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
         */
        TreeNode root = new TreeNode(5);
        TreeNode node4 = new TreeNode(4);
        TreeNode node8 = new TreeNode(8);
        TreeNode node11 = new TreeNode(11);
        node11.left = new TreeNode(7);
        node11.right = new TreeNode(2);
        TreeNode node4_2 = new TreeNode(4);
        node4_2.left = new TreeNode(5);
        node4_2.right = new TreeNode(1);
        node4.left = node11;
        node8.left = new TreeNode(13);
        node8.right = node4_2;
        root.left = node4;
        root.right = node8;
        System.out.println(new PathSumII().pathSum(root, 22));
    }

    // 常规做法，时间不是最好的
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        dfs(root, new ArrayDeque<>(), sum);
        return result;
    }

    private List<List<Integer>> result = new ArrayList<>();

    private void dfs(TreeNode root, Deque<Integer> paths, int target) {
        if (root == null) {
            return;
        }
        // 先序遍历
        paths.add(root.val);
        if (root.left != null || root.right != null) {
            dfs(root.left, paths, target);
            dfs(root.right, paths, target);
        } else {
            // 打印由前序遍历的来的，所有的root节点到叶子节点的路径
            System.out.println(paths);
            ArrayList<Integer> list = new ArrayList<>(paths);
            int sum = list.stream().mapToInt(Integer::intValue).sum();
            if (sum == target) {
                result.add(list);
            }
        }

        paths.removeLast();
    }

}
