package zzl.util;

import zzl.base.ListNode;
import zzl.base.TreeNode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zzl
 */
public class GenerateUtil {
    private static String DEFAULT_SPLIT_CHAR = ",";

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

    public static int[] generateIntArray(String input) {
        String[] split = input.split(DEFAULT_SPLIT_CHAR);
        int length = split.length;
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            result[i] = Integer.parseInt(split[i].trim());
        }
        return result;
    }

    public static int[][] generateBinaryIntArrayBetter(String s) {
        String replace = s.trim()
                .replaceAll("\\],\\[", "\n")
                .replace("[[", "")
                .replace("]]", "");
        return generateBinaryIntArray(replace);
    }


    public static List<List<Integer>> generateBinaryIntList(String s) {
        String replace = s.trim()
                .substring(1, s.trim().length() - 1);
        List<List<Integer>> list = new ArrayList<>();
        // 按照正则表达式分割
        String[] lineSplit = replace.split("(?<=\\]),(?=\\[)");
        for (String line : lineSplit) {
            // 分割后的每一节都包含"[]"，所以从起始向里位移一位裁剪
            String tmp = line.substring(1, line.length() - 1);
            List<Integer> ll = new ArrayList<>();
            if (tmp.length() != 0) {
                String[] split = tmp.split(DEFAULT_SPLIT_CHAR);
                for (int i = 0; i < split.length; i++) {
                    ll.add(Integer.valueOf(split[i]));
                }
            }
            list.add(ll);
        }
        return list;
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

    public static int[][] generateBinaryIntArray(String input) {
        return generateBinaryIntArray(input, DEFAULT_SPLIT_CHAR);
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


    public static List<List<Integer>> generateBinaryList(String input, String splitChar) {
        String[] split = input.split("\n");
        int height = split.length;
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            String[] number = split[i].split(splitChar);
            List<Integer> line = new ArrayList<>();
            for (int j = 0; j < number.length; j++) {
                line.add(Integer.valueOf(number[j]));
            }
            result.add(line);
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = generateTreeNode("1,2,2,3,4,4,3", ",");
        PrintConsoleUtil.printTreeNode(root);
        root = generateTreeNode("1,2,2,null,3,null,3", ",");
        PrintConsoleUtil.printTreeNode(root);
    }
}
