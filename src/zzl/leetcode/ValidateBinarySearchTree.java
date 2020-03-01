package zzl.leetcode;

import org.junit.Assert;
import zzl.base.TreeNode;

import java.util.Stack;

/**
 * @author zzl
 * @link {https://leetcode-cn.com/problems/validate-binary-search-tree/}
 */
public class ValidateBinarySearchTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
//        Assert.assertEquals(new ValidateBinarySearchTree().isValidBST(root), true);
        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(1);
        TreeNode node = new TreeNode(6);
        node.left = new TreeNode(4);
        node.right = new TreeNode(7);
        root2.right = node;
//        Assert.assertEquals(new ValidateBinarySearchTree().isValidBST(root2), false);
//        Assert.assertEquals(new ValidateBinarySearchTree().isValidBST(new TreeNode(0)), true);
        TreeNode root3 = new TreeNode(1);
        root3.left = new TreeNode(1);
//        Assert.assertEquals(new ValidateBinarySearchTree().isValidBST(root3), false);
        TreeNode root4 = new TreeNode(1);
        root4.right = new TreeNode(1);
//        Assert.assertEquals(new ValidateBinarySearchTree().isValidBST(root4), false);
        TreeNode root5 = new TreeNode(Integer.MIN_VALUE);
        root5.right = new TreeNode(Integer.MIN_VALUE);
        Assert.assertEquals(new ValidateBinarySearchTree().isValidBST(root5), false);
        Assert.assertEquals(new ValidateBinarySearchTree().isValidBST(new TreeNode(-2147483648)), true);

    }


    /**
     * 二叉搜索树的中序遍历是有序的，判断当前节点和两个子节点的关系
     * 使用中序遍历是有序的，使用堆栈存储打印的顺序，当当前的节点的值小于等于栈顶的最大值时，说明二叉搜索树错误，直接返回false即可
     * 此处没有使用官方解析的上界和下届，在内存占用上会稍高一些，因为要完整记录
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        return validate1(root, new Integer[]{null});
    }

    /**
     * 使用topValue来保存栈顶元素，就不用完整的保存整个堆栈记录了，时间是非常短，但是相较于stack来说，内存空间并没有提升太多
     * 至于此处用了长度为1的数组来保存是为了传递引用，不然更新的top值会无法回传
     *
     * @param root
     * @param stackTop
     * @return
     */
    boolean validate1(TreeNode root, Integer[] stackTop) {
        if (root == null) {
            return true;
        } else {

            if (!validate1(root.left, stackTop)) {
                return false;
            }
            if (stackTop[0] != null && stackTop[0] >= root.val) {
                return false;
            }
            stackTop[0] = root.val;
            if (!validate1(root.right, stackTop)) {
                return false;
            }
            return true;
        }
    }

    /**
     * 这个是官网解题记录里最节省资源的解题方法，但不是非常好理解
     *
     * @param root
     * @return
     */
    public boolean isValidBST2(TreeNode root) {
        if (root == null) return true;
        return helper(root, null, null);
    }

    public boolean helper(TreeNode root, Integer min, Integer max) {
        if (root == null) return true;
        if (min != null && root.val <= min) return false;
        if (max != null && root.val >= max) return false;
        return helper(root.left, min, root.val) && helper(root.right, root.val, max);
    }

    boolean validate(TreeNode root, Stack<Integer> stack) {
        if (root == null) {
            return true;
        } else {
            if (!validate(root.left, stack)) {
                return false;
            }
            if (!stack.isEmpty() && stack.get(stack.size() - 1) >= root.val) {
                return false;
            }
            stack.push(root.val);
            if (!validate(root.right, stack)) {
                return false;
            }
            return true;
        }
    }


}
