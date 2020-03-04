package zzl.util;

import zzl.base.Node;
import zzl.base.TreeNode;

/**
 * @author zzl
 */
public class PrintTreeUtil {
    static TreePrinter<TreeNode> printer = new TreePrinter<TreeNode>(n -> ("" + n.getValue()), n -> n.getLeft(), n -> n.getRight());

    public static void print(TreeNode root) {
        printer.setHspace(4);
        printer.setTspace(4);
        printer.setSquareBranches(false);
        printer.printTree(root);
    }

    static TreePrinter<Node> nodePrinter = new TreePrinter<>(n -> ("" + n.getVal()), n -> n.getLeft(), n -> n.getRight());

    public static void printNode(Node root) {
        nodePrinter.setHspace(4);
        nodePrinter.setTspace(4);
        nodePrinter.setSquareBranches(false);
        nodePrinter.printTree(root);
    }
}
