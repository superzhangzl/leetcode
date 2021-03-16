package zzl.util;

import zzl.base.BinaryNode;
import zzl.base.ListNode;
import zzl.base.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zzl
 */
public class PrintConsoleUtil {
    static TreePrinter<TreeNode> printer = new TreePrinter<TreeNode>(n -> ("" + n.getValue()), n -> n.getLeft(), n -> n.getRight());

    public static void printTreeNode(TreeNode root) {
        printer.setHspace(4);
        printer.setTspace(4);
        printer.setSquareBranches(false);
        printer.printTree(root);
    }

    static TreePrinter<BinaryNode> nodePrinter = new TreePrinter<>(n -> ("" + n.getVal()), n -> n.getLeft(), n -> n.getRight());

    public static void printNode(BinaryNode root) {
        nodePrinter.setHspace(4);
        nodePrinter.setTspace(4);
        nodePrinter.setSquareBranches(false);
        nodePrinter.printTree(root);
    }

    public static void printListNode(ListNode listNode) {
        List<Integer> list = new ArrayList<>();
        while (listNode != null) {
            list.add(listNode.val);
            listNode = listNode.next;
        }
        System.out.println(list.stream().map(String::valueOf).collect(Collectors.joining(" -> ")));
    }

    public static void printArray(int[][] arrs) {
        for (int i = 0; i < arrs.length; i++) {
            for (int j = 0; j < arrs[0].length; j++) {
                System.out.print(String.format("%2d ", arrs[i][j]));
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void printArray(char[][] arrs) {
        for (int i = 0; i < arrs.length; i++) {
            for (int j = 0; j < arrs[0].length; j++) {
                System.out.print(String.format("%2s ", arrs[i][j]));
            }
            System.out.println();
        }
    }

    public static void printArray(int[] arrs) {
        for (int i = 0; i < arrs.length; i++) {
            System.out.print(String.format("%2d ", arrs[i]));
        }
        System.out.println();
    }

    public static void printArrayWithIndex(int[] arrs, int index) {
        for (int i = 0; i < arrs.length; i++) {
            System.out.print(String.format("%2d ", arrs[i]));
        }
        System.out.println();
        for (int i = 0; i < arrs.length; i++) {
            if (i == index) {
                System.out.print(String.format("[%d]", i));
            } else {
                System.out.print("   ");
            }
        }
        System.out.println();
    }

    public static void printParams(Object... params) {
        if (params.length % 2 != 0) {
            System.out.println("printParams param error....");
        }
        HashMap<Object, Object> map = new LinkedHashMap<>();
        for (int i = 0; i < params.length; i += 2) {
            map.put(params[i], params[i + 1]);
        }
        System.out.println(map);
    }
}
