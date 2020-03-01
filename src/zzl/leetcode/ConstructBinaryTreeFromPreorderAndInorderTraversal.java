package zzl.leetcode;

import zzl.base.TreeNode;
import zzl.util.PrintTreeUtil;

/**
 * 从前序与中序遍历序列构造二叉树
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/}
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public static void main(String[] args) {
//        前序遍历 preorder = [3,9,20,15,7]
//        中序遍历 inorder = [9,3,15,20,7]
        int[] preorder = new int[]{1, 2, 3};
        int[] inorder = new int[]{3, 2, 1};
        TreeNode treeNode = new ConstructBinaryTreeFromPreorderAndInorderTraversal().buildTree(preorder, inorder);
        PrintTreeUtil.print(treeNode);
        System.out.println("+=====================");
        preorder = new int[]{3, 9, 20, 15, 7};
        inorder = new int[]{9, 3, 15, 20, 7};
        treeNode = new ConstructBinaryTreeFromPreorderAndInorderTraversal().buildTree(preorder, inorder);
        PrintTreeUtil.print(treeNode);
        preorder = new int[]{3};
        inorder = new int[]{3};
        treeNode = new ConstructBinaryTreeFromPreorderAndInorderTraversal().buildTree(preorder, inorder);
        PrintTreeUtil.print(treeNode);
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return builder(preorder, 0, preorder.length, inorder, 0, inorder.length);
    }

    private TreeNode builder(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        System.out.println(String.format("preStart=%d, preEnd=%d, inStart=%d, inEnd=%d", preStart, preEnd, inStart, inEnd));
        // 前序遍历的前后所有有可能会出现start>end的情况，
        if (preStart > preEnd) {
            return null;
        }
        if (inStart == inEnd) {
            return null;
        }
        // 先序遍历的第一个元素是根节点
        int rootVal = preorder[preStart];
        // 记录当前索引的位置为中序遍历的起始位置
        int indexInOrder = inStart;
        // 记录移动次数，即可知道中序遍历中坐子树的长度
        int preMoveCount = 0;
        // 寻找中序遍历中根节点的位置，那左边的就是左子树序列，右边的是右子树序列，两边又满足同样的递归条件
        while (indexInOrder < inEnd) {
            if (inorder[indexInOrder] == rootVal) {
                break;
            } else {
                indexInOrder++;
                preMoveCount++;
            }
        }
        System.out.println("root value: " + rootVal);
        System.out.println("current root index: " + indexInOrder);
        System.out.println("pre move count: " + preMoveCount);
        TreeNode root = new TreeNode(rootVal);
        // 左子树的也满足当前的递归条件，
        // 前序的起始位置是preStart+1，第一个元素是root节点，从下一个开始，一直到记录的移动的个数之间为当前层级的左子树
        // 中序遍历的起点是inStart，到当前root节点
        root.left = builder(preorder, preStart + 1, preStart + preMoveCount, inorder, inStart, indexInOrder);
        // 右子树的也满足当前的递归条件，
        // 前序的起始位置是preStart + 1 + preMoveCount，第一个元素是root节点，从[preStart + 1~preStart + preMoveCount]为当前层级右子树的范围
        // 中序遍历的起点是indexInOrder+1，即root节点的下一个开始，一直到结尾
        root.right = builder(preorder, preStart + 1 + preMoveCount, preEnd, inorder, indexInOrder + 1, inEnd);
        return root;
    }
}
