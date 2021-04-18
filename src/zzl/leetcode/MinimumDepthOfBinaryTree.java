package zzl.leetcode;

import org.junit.Assert;
import zzl.base.TreeNode;
import zzl.base.annotation.Level;
import zzl.util.GenerateUtil;

import java.util.ArrayDeque;

import static zzl.base.enums.Difficulty.SIMPLE;

/**
 * 二叉树的最小深度
 *
 * @author zzl
 * @date 2021-04-18
 * @link {https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/}
 */
@Level(SIMPLE)
public class MinimumDepthOfBinaryTree {
    public static void main(String[] args) {
        TreeNode root;
        int minDepth;
        //
        root = GenerateUtil.generateTreeNode("3,9,20,null,null,15,7");
        minDepth = new MinimumDepthOfBinaryTree().minDepth(root);
        Assert.assertEquals(minDepth, 2);
        //
        root = GenerateUtil.generateTreeNode("2,null,3,null,4,null,5,null,6");
        minDepth = new MinimumDepthOfBinaryTree().minDepth(root);
        Assert.assertEquals(minDepth, 5);
        //
        root = GenerateUtil.generateTreeNode("1,2,3,4,5");
        minDepth = new MinimumDepthOfBinaryTree().minDepth(root);
        Assert.assertEquals(minDepth, 2);
        //
        root = GenerateUtil.generateTreeNode("1,2,3,4,5,6,7");
        minDepth = new MinimumDepthOfBinaryTree().minDepth(root);
        Assert.assertEquals(minDepth, 2);
    }

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        queue.push(root);
        int depth = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            // 将当前队列中的所有节点向四周移除
            // 即一次遍历一层的数据，否侧depth的数值会变成节点的个数
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left == null && node.right == null) {
                    return depth;
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            depth++;
        }
        return depth;
    }

}
