package zzl.leetcode;

import zzl.base.TreeNode;
import zzl.util.PrintConsoleUtil;

/**
 * @author zzl
 * @link {https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/}
 */
public class ConstructBinaryTreeFromInorderAndPostorderTraversal {
    public static void main(String[] args) {
//        前序遍历 preorder = [3,9,20,15,7]
//        中序遍历 inorder = [9,3,15,20,7]
        int[] inorder = new int[]{9, 3, 15, 20, 7};
        int[] postorder = new int[]{9, 15, 7, 20, 3};
        TreeNode treeNode = new ConstructBinaryTreeFromInorderAndPostorderTraversal().buildTree(inorder, postorder);
        PrintConsoleUtil.printTreeNode(treeNode);
        System.out.println("+=====================");
        inorder = new int[]{3};
        postorder = new int[]{3};
        treeNode = new ConstructBinaryTreeFromInorderAndPostorderTraversal().buildTree(inorder, postorder);
        PrintConsoleUtil.printTreeNode(treeNode);
        System.out.println("+=====================");
        inorder = new int[]{1, 2, 3};
        postorder = new int[]{2, 3, 1};
        treeNode = new ConstructBinaryTreeFromInorderAndPostorderTraversal().buildTree(inorder, postorder);
        PrintConsoleUtil.printTreeNode(treeNode);
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return builder(inorder, 0, inorder.length, postorder, 0, postorder.length);
    }

    private TreeNode builder(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd) {
        System.out.println(String.format("inStart=%d, inEnd=%d, postStart=%d, postEnd=%d", inStart, inEnd, postStart, postEnd));
        if (postStart == postEnd) {
            return null;
        }
        if (inStart > inEnd) {
            return null;
        }
        // 后序遍历的最后一个元素是根节点
        int rootVal = postorder[postEnd - 1];
        // 记录当前索引的位置为中序遍历的起始位置
        int indexInOrder = inEnd - 1;
        // 记录移动次数，即可知道中序遍历中坐子树的长度
        int preMoveCount = 0;
        // 寻找中序遍历中根节点的位置，那左边的就是左子树序列，右边的是右子树序列，两边又满足同样的递归条件
        while (indexInOrder >= 0) {
            if (inorder[indexInOrder] == rootVal) {
                break;
            } else {
                indexInOrder--;
                preMoveCount++;
            }
        }
        System.out.println("root value: " + rootVal);
        System.out.println("current root index: " + indexInOrder);
        System.out.println("pre move count: " + preMoveCount);
        TreeNode root = new TreeNode(rootVal);
        // 记录左右子节点的访问范围
        /**
         * @link {ConstructBinaryTreeFromPreorderAndInorderTravers}
         */
        root.left = builder(inorder, inStart, indexInOrder, postorder, postStart, postEnd - preMoveCount - 1);
        root.right = builder(inorder, indexInOrder + 1, inEnd, postorder, postEnd - preMoveCount - 1, postEnd - 1);
        return root;
    }
}
