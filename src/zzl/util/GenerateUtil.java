package zzl.util;

import zzl.base.ListNode;
import zzl.base.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author zzl
 */
public class GenerateUtil {
    public static ListNode generateListNode(String input, String splitChar) {
        String[] split = input.split(splitChar);
        int length = split.length;
        ListNode preRoot = new ListNode(-999);
        ListNode head = preRoot;
        for (int i = 0; i < length; i++) {
            preRoot.next = new ListNode(Integer.parseInt(split[i].trim()));
            preRoot = preRoot.next;
        }
        return head.next;
    }

    public static int[] generateIntArray(String input, String splitChar) {
        String[] split = input.split(splitChar);
        int length = split.length;
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            result[i] = Integer.parseInt(split[i].trim());
        }
        return result;
    }

    public static int[][] generateBinaryIntArray(String input, String splitChar) {
        String[] split = input.split("\n");
        int height = split.length;
        int width = split[0].trim().split(splitChar).length;
        int[][] result = new int[height][width];
        for (int i = 0; i < height; i++) {
            String[] number = split[i].split(splitChar);
            for (int j = 0; j < width; j++) {
                result[i][j] = Integer.parseInt(number[j].trim());
            }
        }
        return result;
    }

    public static char[][] generateBinaryCharArray(String input, String splitChar) {
        String[] split = input.split("\n");
        int height = split.length;
        int width = split[0].trim().split(splitChar).length;
        char[][] result = new char[height][width];
        for (int i = 0; i < height; i++) {
            String[] number = split[i].split(splitChar);
            for (int j = 0; j < width; j++) {
                result[i][j] = number[j].charAt(0);
            }
        }
        return result;
    }

    public static TreeNode generateTreeNode(String input, String splitChar) {
        Queue<Integer> queue = new LinkedList<>();
        Queue<TreeNode> bfsNodeOrder = new LinkedList<>();
        Queue<TreeNode> bfsRootOrder = new LinkedList<>();
        String[] split = input.split(splitChar);
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
            if (leftVal != null){
                TreeNode left = new TreeNode(leftVal);
                currentRoot.left = left;
                bfsRootOrder.add(left);
            }
            if (rightVal != null){
                TreeNode right = new TreeNode(rightVal);
                currentRoot.right = right;
                bfsRootOrder.add(right);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = generateTreeNode("1,2,2,3,4,4,3", ",");
        PrintConsoleUtil.printTreeNode(root);
        root = generateTreeNode("1,2,2,null,3,null,3", ",");
        PrintConsoleUtil.printTreeNode(root);
    }
}
