package zzl.leetcode;

import zzl.base.TreeNode;
import zzl.base.annotation.Level;
import zzl.util.PrintConsoleUtil;

import java.util.*;

import static zzl.base.enums.Difficulty.*;

/**
 * 所有可能的满二叉树
 *
 * @author zzl
 * @date 2021-04-19
 * @link {https://leetcode-cn.com/problems/all-possible-full-binary-trees/}
 */
@Level(MEDIUM)
public class AllPossibleFullBinaryTrees {
    public static void main(String[] args) {
        List<TreeNode> allPossibleFBT = new AllPossibleFullBinaryTrees().allPossibleFBT(7);
        for (TreeNode treeNode : allPossibleFBT) {
            PrintConsoleUtil.printTreeNode(treeNode);
            System.out.println();
        }
    }

    /**
     * DFS
     *
     * @param N
     * @return
     * @link {https://leetcode-cn.com/problems/all-possible-full-binary-trees/solution/javajian-dan-yi-dong-by-cyingenohalt-nhnu/}
     */
    public List<TreeNode> allPossibleFBT(int N) {
        List<TreeNode> ans = new ArrayList<>();
        create(N, ans);
        return ans;
    }

    private void create(int n, List<TreeNode> ans) {
        if (n == 1) {
            // 叶子节点，此时n=1，不需要添加左右子节点
            ans.add(new TreeNode(0));
            return;
        }
        // 除了根节点，别的都是需要两两形成左右子节点，所以循环步长为2
        for (int i = 1; i < n - 1; i += 2) {
            List<TreeNode> l = new ArrayList<>(); //左子树所有情况
            List<TreeNode> r = new ArrayList<>();//右子树所有情况
            // 相当于是以i为cur根节点，生成左右的子节点
            create(i, l);//左子树i个节点
            create(n - i - 1, r);//右子树n-i-1个节点
            for (TreeNode left : l) {//组合所有情况
                for (TreeNode right : r) {
                    TreeNode root = new TreeNode(0);//根
                    root.left = left;
                    root.right = right;
                    ans.add(root);
                }
            }
        }
    }
}
