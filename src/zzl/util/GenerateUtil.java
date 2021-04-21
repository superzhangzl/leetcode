package zzl.util;

import zzl.base.ListNode;
import zzl.base.TreeNode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zzl
 */
public class GenerateUtil {
    private static final String DEFAULT_SPLIT_CHAR = ",";
    private static final String DEFAULT_LIST_NODE_SPLIT_CHAR = "->";

    public static ListNode generateListNode(String input) {
        return generateListNode(input, DEFAULT_LIST_NODE_SPLIT_CHAR);
    }

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
        return generateIntArray(input, DEFAULT_SPLIT_CHAR);
    }

    public static char[] generateCharArray(String input, String splitChar) {
        String[] split = input.split(splitChar);
        int length = split.length;
        char[] result = new char[length];
        for (int i = 0; i < length; i++) {
            result[i] = split[i].trim()
                    .replaceAll("\"", "")
                    .replaceAll("'", "")
                    .charAt(0);
        }
        return result;
    }

    public static char[] generateCharArray(String input) {
        return generateCharArray(beautyInputString(input), DEFAULT_SPLIT_CHAR);
    }

    public static int[][] generateBinaryIntArrayBetter(String s) {
        String replace = s.trim()
                .replaceAll("\\s", "")
                .replaceAll("\\],\\[", "\n")
                .replace("[[", "")
                .replace("]]", "");
        return generateBinaryIntArray(replace);
    }

    public static char[][] generateBinaryCharArrayBetter(String s) {
        String replace = s.trim()
                .replaceAll("\\],\\[", "\n")
                .replace("[[", "")
                .replace("]]", "");
        return generateBinaryCharArray(replace, DEFAULT_SPLIT_CHAR);
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
                String beauty = number[j].replaceAll("\"", "").replaceAll("'", "");
                result[i][j] = beauty.charAt(0);
            }
        }
        return result;
    }

    public static TreeNode generateTreeNode(String input) {
        return generateTreeNode(input, DEFAULT_SPLIT_CHAR);
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

    /**
     * 链表反转
     *
     * @param head
     * @return
     */
    public static ListNode reverseListNode(ListNode head) {
        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static String[] generateStringArray(String s, String splitChar) {
        return Arrays.stream(s.split(splitChar))
                .map(s1 -> s1.substring(1, s1.length() - 1))
                .collect(Collectors.toList())
                .toArray(new String[0]);
    }

    public static String[] generateStringArray(String s) {
        String beauty = beautyInputString(s);
        return generateStringArray(beauty, DEFAULT_SPLIT_CHAR);
    }

    private static String beautyInputString(String s) {
        return s.trim()
                .replaceAll("\\s", "")
                .replaceAll("\\],\\[", "\n")
                .replace("[[", "")
                .replace("]]", "");
    }

    public static void main(String[] args) {
        ListNode listNode = generateListNode("-1->5->3->4->0");
        PrintConsoleUtil.printListNode(listNode);
        PrintConsoleUtil.printListNode(reverseListNode(listNode));
    }
}
