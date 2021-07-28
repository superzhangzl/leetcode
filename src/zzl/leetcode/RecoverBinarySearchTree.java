package zzl.leetcode;

import zzl.base.TreeNode;
import zzl.base.annotation.Level;
import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

import java.util.ArrayList;
import java.util.List;

import static zzl.base.enums.Difficulty.*;

/**
 * 恢复二叉搜索树
 *
 * @author zzl
 * @date 2021-07-28
 * @link {https://leetcode-cn.com/problems/recover-binary-search-tree/}
 */
@Level(MEDIUM)
public class RecoverBinarySearchTree {
    public static void main(String[] args) {
        TreeNode root;
        //
        root = GenerateUtil.generateTreeNode("1,3,null,null,2");
        new RecoverBinarySearchTree().recoverTree(root);
        PrintConsoleUtil.printTreeNode(root);
        //
        root = GenerateUtil.generateTreeNode("3,1,4,null,null,2");
        new RecoverBinarySearchTree().recoverTree(root);
        PrintConsoleUtil.printTreeNode(root);

    }

    /**
     * BST 中序遍历是递增的
     *
     * @param root
     */
    public void recoverTree(TreeNode root) {
        List<TreeNode> treeList = new ArrayList<>();
        inorder(root, treeList);
        TreeNode[] findTwoSwappedTree = findTwoSwappedTree(treeList);
        // 交换两个node节点的值
        int tmp = findTwoSwappedTree[0].val;
        findTwoSwappedTree[0].val = findTwoSwappedTree[1].val;
        findTwoSwappedTree[1].val = tmp;
    }

    public void inorder(TreeNode root, List<TreeNode> treeList) {
        if (root == null) {
            return;
        }
        inorder(root.left, treeList);
        treeList.add(root);
        inorder(root.right, treeList);
    }

    /**
     * 1. 找到二叉搜索树中序遍历得到值序列的不满足条件的位置。
     * 2. 如果有两个，我们记为 i 和 j（i<j 且 a_i>a_{i+1} && a_j>a_{j+1})，那么对应被错误交换的节点即为 a_i对应的节点和 a_{j+1}对应的节点，
     * 我们分别记为 x 和 y。
     * 3. 如果有一个，我们记为 i，那么对应被错误交换的节点即为 a_i对应的节点和 a_{i+1}对应的节点，我们分别记为 x 和 y。
     * 4. 交换 x和 y两个节点即可。
     *
     * 题目要求就是只混乱了两个节点的值，
     * 也就是说前面找一个最大的，后面找一个最小的，交换这两个的值即可
     * @param nums
     * @return
     */
    /**
     * @param nums
     * @return
     */
    public TreeNode[] findTwoSwappedTree(List<TreeNode> nums) {
        int n = nums.size();
        TreeNode x = null, y = null;
        for (int i = 0; i < n - 1; ++i) {
            if (nums.get(i + 1).val < nums.get(i).val) {
                y = nums.get(i + 1);
                if (x == null) {
                    x = nums.get(i);
                } else {
                    break;
                }
            }
        }
        return new TreeNode[]{x, y};
    }

}
