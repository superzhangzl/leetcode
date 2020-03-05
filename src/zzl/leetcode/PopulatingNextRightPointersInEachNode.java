package zzl.leetcode;

import zzl.base.BinaryNode;
import zzl.util.PrintConsoleUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * 填充每个节点的下一个右侧节点指针
 * 完美二叉树
 * 提示：
 * - 你只能使用常量级额外空间。
 * - 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/}
 */
public class PopulatingNextRightPointersInEachNode {
    public static void main(String[] args) {
        BinaryNode root = new BinaryNode(1);
        BinaryNode binaryNode2 = new BinaryNode(2);
        BinaryNode binaryNode3 = new BinaryNode(3);
        binaryNode2.left = new BinaryNode(4);
        binaryNode2.right = new BinaryNode(5);
        binaryNode3.left = new BinaryNode(6);
        binaryNode3.right = new BinaryNode(7);
        root.left = binaryNode2;
        root.right = binaryNode3;
        PrintConsoleUtil.printNode(root);
        BinaryNode connect = new PopulatingNextRightPointersInEachNode().connect(root);
        System.out.println(connect);
    }

    public BinaryNode connect(BinaryNode root) {
        if (root == null) {
            return null;
        }
        List<BinaryNode> stack = new LinkedList<>();
        print(root, 0, stack);
        System.out.println(stack);
        return root;
    }

    /**
     * 优化，每一层只保存最右且没有next过的节点，然后连接在当前的root节点后，把当前的root设置为该层级的位置
     * 空间复杂度为O(层数)
     *
     * @param root
     * @param level
     * @param path
     */
    private void print(BinaryNode root, int level, List<BinaryNode> path) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + " ");
        if (path.size() > level && path.get(level) != null) {
            // 建立连接
            path.get(level).next = root;
            // 更新当前层级节点
            path.set(level, root);
        } else {
            // 第一次就添加
            path.add(level, root);
        }
        print(root.left, level + 1, path);
        print(root.right, level + 1, path);
    }

    /**
     * leetcode 提交记录上用时最短的解法
     *
     * @param root
     * @return
     */
    public BinaryNode connectBest(BinaryNode root) {
        if (root == null || root.left == null) {
            return root;
        }
        // 利用已经建立好的next节点
        BinaryNode leftmost = root;
        // leftmost不为null，该层有数据
        while (leftmost.left != null) {
            BinaryNode head = leftmost;
            while (head != null) {
                head.left.next = head.right;
                if (head.next != null) {
                    head.right.next = head.next.left;
                }
                head = head.next;
            }
            leftmost = leftmost.left;
        }
        return root;
    }

    public BinaryNode connect1(BinaryNode root) {
        if (root == null) {
            return root;
        }
        if (root.right != null)
            root.left.next = root.right;
        if (root.left != null)
            root.right.next = root.next != null ? root.next.left : null;
        connect1(root.left);
        connect1(root.right);
        return root;
    }
}