package zzl.leetcode;

import zzl.base.Node;
import zzl.util.PrintTreeUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * 填充每个节点的下一个右侧节点指针
 * 提示：
 * - 你只能使用常量级额外空间。
 * - 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 *
 * @author zzl
 */
public class PopulatingNextRightPointersInEachNode {
    public static void main(String[] args) {
        Node root = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        node2.left = new Node(4);
        node2.right = new Node(5);
        node3.left = new Node(6);
        node3.right = new Node(7);
        root.left = node2;
        root.right = node3;
        PrintTreeUtil.printNode(root);
        Node connect = new PopulatingNextRightPointersInEachNode().connect(root);
        System.out.println(connect);
    }

    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        List<List<Node>> stack = new LinkedList<>();
        print(root, 0, stack);
        System.out.println(stack);
        for (List<Node> list : stack) {
            for (int i = 0; i < list.size() - 1; i++) {
                list.get(i).next = list.get(i + 1);
            }
        }
        return root;
    }

    /**
     * 使用stack保存每一个层级的所有节点，添加完后，横向连接过去就行，空间复杂度为O(n)
     * 时间复杂度貌似效率不高，但是胜在好理解一些
     *
     * @param root
     * @param level
     * @param path
     */
    private void print(Node root, int level, List<List<Node>> path) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + " ");
        if (path.size() > level && path.get(level) != null) {
            path.get(level).add(root);
        } else {
            path.add(new LinkedList<Node>() {{
                add(root);
            }});
        }
        print(root.left, level + 1, path);
        print(root.right, level + 1, path);
    }
}