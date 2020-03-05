package zzl.leetcode;

import zzl.base.BinaryNode;
import zzl.util.PrintConsoleUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * 填充每个节点的下一个右侧节点指针II
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii/}
 */
public class PopulatingNextRightPointersInEachNodeII {
    public static void main(String[] args) {
        BinaryNode root = new BinaryNode(1);
        BinaryNode binaryNode2 = new BinaryNode(2);
        BinaryNode binaryNode3 = new BinaryNode(3);
        binaryNode2.left = new BinaryNode(4);
        binaryNode2.right = new BinaryNode(5);
        binaryNode3.right = new BinaryNode(7);
        root.left = binaryNode2;
        root.right = binaryNode3;
        PrintConsoleUtil.printNode(root);
        BinaryNode connect = new PopulatingNextRightPointersInEachNodeII().connect(root);
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
}