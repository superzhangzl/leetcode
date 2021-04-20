package zzl.leetcode;

import zzl.base.TreeNode;
import zzl.base.annotation.Level;
import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

import static zzl.base.enums.Difficulty.*;

/**
 * 合并二叉树
 *
 * @author zzl
 * @date 2021-04-20
 * @link {https://leetcode-cn.com/problems/merge-two-binary-trees/}
 */
@Level(SIMPLE)
public class MergeTwoBinaryTrees {
    public static void main(String[] args) {
        TreeNode root1 = GenerateUtil.generateTreeNode("1,3,2,5");
        PrintConsoleUtil.printTreeNode(root1);
        TreeNode root2 = GenerateUtil.generateTreeNode("2,1,3,null,4,null,7");
        PrintConsoleUtil.printTreeNode(root2);
        TreeNode mergeTrees = new MergeTwoBinaryTrees().mergeTrees(root1, root2);
        PrintConsoleUtil.printTreeNode(mergeTrees);
    }

    /**
     * 递归
     *
     * @param root1
     * @param root2
     * @return
     * @link {https://leetcode-cn.com/problems/merge-two-binary-trees/solution/he-bing-er-cha-shu-by-leetcode-solution/}
     */
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        // 因为是合并两个左(右)子节点，如果其中一个为null，就返回另一个。
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        // 如果两个都不为空，执行递归合并流程
        TreeNode merged = new TreeNode(root1.val + root2.val);
        merged.left = mergeTrees(root1.left, root2.left);
        merged.right = mergeTrees(root1.right, root2.right);
        return merged;
    }
}
