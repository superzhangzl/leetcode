package zzl.leetcode;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author zzl
 * @link {https://leetcode-cn.com/problems/unique-binary-search-trees-ii/}
 */
public class UniqueBinarySearchTreesII {
    public static void main(String[] args) {
        System.out.println(new UniqueBinarySearchTreesII().generateTrees(3));
    }

    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return Collections.emptyList();
        }
        return generateBinarySearchTrees(1, n);
    }

    /**
     * 因为BST的特性，将[1,n]的每一个节点作为根节点，左边的肯定都root节点小，右边的肯定都比root大
     * 将[1,n]分割成[1,i],i,[i+1,n]，对[1,i]和[i+1,n]循环重复上述操作
     *
     * @param left
     * @param right
     * @return
     */
    private List<TreeNode> generateBinarySearchTrees(int left, int right) {
        List<TreeNode> result = new ArrayList<>();
        // 添加终止条件，经过测试不添加这个终止条件，返回的是[]
        // 因为不返回null来占位（即表示最子树的左右节点为null），不然上一次循环的时候list为空会无法连接
        if (left > right) {
            result.add(null);
            return result;
        }

        // 递归方法,
        for (int i = left; i <= right; i++) {
            String logMsg = String.format("i=%d, left=%d, right=%d", i, left, right);
            System.out.println(logMsg);

            // 以i为中间节点生成的左子树
            List<TreeNode> leftTrees = generateBinarySearchTrees(left, i - 1);
            // 以i为中间节点生成的右子树
            List<TreeNode> rightTrees = generateBinarySearchTrees(i + 1, right);

            // 将生成的左子树和右子树连接在i上，因为当前i是这一层的root节点
            // 此处使用循环是因为以当前节点为root节点时，做很多种左子树和右子树的情况
            // 通过局部两两组合的方式O(len(left)*len(right))的方式将当前节点连接在root上，生成多个不同的情况
            for (TreeNode leftTree : leftTrees) {
                for (TreeNode rightTree : rightTrees) {
                    String log = String.format("i=%d, left=%s, right=%s", i, leftTree, rightTree);
                    System.out.println(log);
                    TreeNode currentRootNode = new TreeNode(i);
                    currentRootNode.left = leftTree;
                    currentRootNode.right = rightTree;
                    // 此时其中一棵树的情况就连接完成，将其添加进list
                    result.add(currentRootNode);
                }
            }
        }

        return result;
    }

    /**
     * 高级解法，内存占用少，实际上各个元素之间是相互连接引用的
     *
     * @link {https://leetcode-cn.com/problems/unique-binary-search-trees-ii/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-2-7/}
     */

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return "{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}

