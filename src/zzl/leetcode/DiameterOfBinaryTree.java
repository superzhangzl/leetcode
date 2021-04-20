package zzl.leetcode;

import org.junit.Assert;
import zzl.base.TreeNode;
import zzl.base.annotation.Level;
import zzl.util.GenerateUtil;

import static zzl.base.enums.Difficulty.SIMPLE;

/**
 * 二叉树的直径
 *
 * @author zzl
 * @date 2021-04-20
 * @link {https://leetcode-cn.com/problems/diameter-of-binary-tree/}
 */
@Level(SIMPLE)
public class DiameterOfBinaryTree {
    public static void main(String[] args) {
        TreeNode root;
        int diameterOfBinaryTree;
        //
        root = GenerateUtil.generateTreeNode("1,2,3,4,5");
        diameterOfBinaryTree = new DiameterOfBinaryTree().diameterOfBinaryTree(root);
        Assert.assertEquals(diameterOfBinaryTree, 3);
        //
        root = GenerateUtil.generateTreeNode("1,2,3");
        diameterOfBinaryTree = new DiameterOfBinaryTree().diameterOfBinaryTree(root);
        Assert.assertEquals(diameterOfBinaryTree, 2);
        //
        root = GenerateUtil.generateTreeNode("1,2,3,4,5,6,7,8");
        diameterOfBinaryTree = new DiameterOfBinaryTree().diameterOfBinaryTree(root);
        Assert.assertEquals(diameterOfBinaryTree, 5);

    }

    /**
     * 二叉树的直径：两结点之间的路径长度是以它们之间边的数目表示。
     * 那计算出最多的相连节点数，然后减一就好了
     *
     * @param root
     * @return
     */
    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return maxLength - 1;
    }

    int maxLength = 0;

    public int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = dfs(root.left) + 1;
        int rightDepth = dfs(root.right) + 1;
        maxLength = Math.max(maxLength, rightDepth + leftDepth - 1);
        return Math.max(leftDepth, rightDepth);
    }
}
