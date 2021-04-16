package zzl.leetcode;

import org.junit.Assert;
import zzl.base.TreeNode;
import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

/**
 * 二叉树的最近公共祖先
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/er-cha-shu-de-zui-jin-gong-gong-zu-xian-lcof/}
 * @see LowestCommonAncestorOfABinaryTree
 */
public class ErChaShuDeZuiJinGongGongZuXianLcof {
    public static void main(String[] args) {
        TreeNode root;
        TreeNode p;
        TreeNode q;
        TreeNode lowestCommonAncestor;
        //
        root = GenerateUtil.generateTreeNode("3,5,1,6,2,0,8,null,null,7,4");
        PrintConsoleUtil.printTreeNode(root);
        p = new TreeNode(5);
        q = new TreeNode(1);
        lowestCommonAncestor = new ErChaShuDeZuiJinGongGongZuXianLcof().lowestCommonAncestor(root, p, q);
        Assert.assertEquals(lowestCommonAncestor.val, 3);
        //
        p = new TreeNode(5);
        q = new TreeNode(4);
        lowestCommonAncestor = new ErChaShuDeZuiJinGongGongZuXianLcof().lowestCommonAncestor(root, p, q);
        Assert.assertEquals(lowestCommonAncestor.val, 5);
    }

    /**
     * 与{@link LowestCommonAncestorOfABinaryTree}的题目内容一样，都是求解最近公共祖先，
     * 那道题的思路是，通过递归打印出从根节点到目标节点的路径，然后比较两个字符串相同的最后一个字符即为最近公共父节点，属于取巧的方法
     * 本题使用递归遍历
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        // 题目条件说明节点的值在二叉树中唯一，可以直接使用值来比较，否则得比较对象
        if (root.val == p.val || root.val == q.val) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        /*
        情况1：后续遍历所以是最近，从当前节点开始能找到p和q两个节点，
        说明当前节点是最新的公共父节点
         */
        if (left != null && right != null) {
            return root;
        }
        /*
        情况2：两个节点就没有公共的父节点，即从每个点出发都找不到
         */
        if (left == null && right == null) {
            return null;
        }
        /*
        情况3：left或者right本身就是公共的父节点，
        即从该节点向下能找到另外一个元素，那他就不为空，就是我们需要的值
         */
        return left != null ? left : right;
    }
}
