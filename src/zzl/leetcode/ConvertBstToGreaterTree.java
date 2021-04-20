package zzl.leetcode;

import zzl.base.TreeNode;
import zzl.base.annotation.Level;
import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

import static zzl.base.enums.Difficulty.*;

/**
 * 把二叉搜索树转换为累加树
 *
 * @author zzl
 * @date 2021-04-20
 * @link {https://leetcode-cn.com/problems/convert-bst-to-greater-tree/}
 */
@Level(MEDIUM)
public class ConvertBstToGreaterTree {
    public static void main(String[] args) {
        TreeNode root = GenerateUtil.generateTreeNode("4,1,6,0,2,5,7,null,null,null,3,null,null,null,8");
        TreeNode convertBST = new ConvertBstToGreaterTree().convertBST(root);
        PrintConsoleUtil.printTreeNode(convertBST);
    }

    public TreeNode convertBST(TreeNode root) {
        dfs(root);
        return root;
    }

    int sum = 0;

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.right);
        System.out.println(root.val);
        sum += root.val;
        root.val = sum;
        dfs(root.left);
    }
}
