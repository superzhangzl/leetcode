package zzl.leetcode;

import zzl.base.TreeNode;
import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

/**
 * 二叉树的序列化与反序列化
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/}
 */
public class SerializeAndDeserializeBinaryTree {
    public static void main(String[] args) {
        TreeNode deserialize = new Codec().deserialize("");
        PrintConsoleUtil.printTreeNode(deserialize);
        String serialize = new Codec().serialize(deserialize);
        System.out.println(serialize);
        PrintConsoleUtil.printTreeNode(GenerateUtil.generateTreeNode(serialize));
    }

    private static class Codec {
        List<Integer> list = new ArrayList<>();

        /**
         * 无需考虑结尾时候多带的null，因为在反序列化的时候会直接过滤掉不会添加到子节点中
         *
         * @param root
         * @return
         */
        public String serialize(TreeNode root) {
            if (root == null) {
                return "";
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                TreeNode poll = queue.poll();
                if (poll != null) {
                    list.add(poll.val);
                    queue.add(poll.left);
                    queue.add(poll.right);
                } else {
                    list.add(null);
                }
            }
            return list.stream().map(String::valueOf).collect(Collectors.joining(","));
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            if (data == null || data == "") {
                return null;
            }
            Queue<Integer> queue = new LinkedList<>();
            Queue<TreeNode> bfsRootOrder = new LinkedList<>();
            String[] split = data.split(",");
            for (int i = 0; i < split.length; i++) {
                try {
                    queue.add(Integer.parseInt(split[i].trim()));
                } catch (NumberFormatException e) {
                    queue.add(null);
                }
            }
            Integer rootVal = queue.poll();
            TreeNode root = new TreeNode(rootVal);
            bfsRootOrder.add(root);
            while (!queue.isEmpty()) {
                Integer leftVal = queue.poll();
                Integer rightVal = queue.poll();
                TreeNode currentRoot = bfsRootOrder.poll();
                if (leftVal != null) {
                    TreeNode left = new TreeNode(leftVal);
                    currentRoot.left = left;
                    bfsRootOrder.add(left);
                }
                if (rightVal != null) {
                    TreeNode right = new TreeNode(rightVal);
                    currentRoot.right = right;
                    bfsRootOrder.add(right);
                }
            }
            return root;
        }
    }
}
