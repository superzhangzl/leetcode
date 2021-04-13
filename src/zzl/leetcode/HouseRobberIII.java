package zzl.leetcode;

import org.junit.Assert;
import zzl.base.TreeNode;
import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 打家劫舍III
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/house-robber-iii/}
 */
public class HouseRobberIII {
    public static void main(String[] args) {
        TreeNode treeNode;
        //
        treeNode = GenerateUtil.generateTreeNode("3,2,3,null,3,null,1");
        PrintConsoleUtil.printTreeNode(treeNode);
        int rob;
        rob = new HouseRobberIII().rob(treeNode);
        Assert.assertEquals(rob, 7);
        //
        treeNode = GenerateUtil.generateTreeNode("3,4,5,1,3,null,1");
        PrintConsoleUtil.printTreeNode(treeNode);
        rob = new HouseRobberIII().rob(treeNode);
        Assert.assertEquals(rob, 9);
        //
        treeNode = GenerateUtil.generateTreeNode("79,99,77,null,null,null,69,null,60,53,null,73,11,null,null,null,62,27,62,null,null,98,50,null,null,90,48,82,null,null,null,55,64,null,null,73,56,6,47,null,93,null,null,75,44,30,82,null,null,null,null,null,null,57,36,89,42,null,null,76,10,null,null,null,null,null,32,4,18,null,null,1,7,null,null,42,64,null,null,39,76,null,null,6,null,66,8,96,91,38,38,null,null,null,null,74,42,null,null,null,10,40,5,null,null,null,null,28,8,24,47,null,null,null,17,36,50,19,63,33,89,null,null,null,null,null,null,null,null,94,72,null,null,79,25,null,null,51,null,70,84,43,null,64,35,null,null,null,null,40,78,null,null,35,42,98,96,null,null,82,26,null,null,null,null,48,91,null,null,35,93,86,42,null,null,null,null,0,61,null,null,67,null,53,48,null,null,82,30,null,97,null,null,null,1,null,null");
        PrintConsoleUtil.printTreeNode(treeNode);
        rob = new HouseRobberIII().rob(treeNode);
        Assert.assertEquals(rob, 3038);
    }

    public int rob(TreeNode root) {
        return dfs(root);
    }
    // 存在重复计算，缓存节点的值；否则会超时
    Map<TreeNode, Integer> cache = new HashMap<>();

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (cache.containsKey(root)) {
            return cache.get(root);
        }
        // 当前计算的是选择根节点的金额
        // 选择了根节点就不能选择相邻的子节点
        int selectRootMoney = root.val;
        if (root.left != null) {
            selectRootMoney += dfs(root.left.left) + dfs(root.left.right);
        }
        if (root.right != null) {
            selectRootMoney += dfs(root.right.left) + dfs(root.right.right);
        }
        // 计算不选择根节点的方案
        // 不选根节点那就选根节点的两个子节点
        int notSelectRootMoney = dfs(root.left) + dfs(root.right);
        // 两种方案做对比
        int max = Math.max(selectRootMoney, notSelectRootMoney);
        cache.put(root, max);
        return max;
    }

}
