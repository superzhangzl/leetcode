package zzl.leetcode;

import org.junit.Assert;
import zzl.base.TreeNode;
import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

/**
 * 路径总和III
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/path-sum-iii/}
 */
public class PathSumIII {
    public static void main(String[] args) {
        TreeNode treeNode = GenerateUtil.generateTreeNode("10,5,-3,3,2,null,11,3,-2,null,1", ",");
        PrintConsoleUtil.printTreeNode(treeNode);
        Assert.assertEquals(new PathSumIII().pathSum(treeNode, 8), 3);
    }

    /**
     * @param root
     * @param sum
     * @return
     * @link {https://leetcode-cn.com/problems/path-sum-iii/solution/javajie-fa-shi-jian-100-kong-jian-93-by-xiao-chao-/}
     */
    public int pathSum(TreeNode root, int sum) {
        return pathSum(root, sum, new int[1000], 0);
    }

    /**
     * 先序遍历的操作顺序
     * 遍历n个节点，为每个节点计算以当前节点为路径终点的所有路径和，平均路径长度是logn，所以平均时间复杂度是O(nlogn)
     * <p>
     * 应该是可以理解为以当前结点为最终叶子结点向上追溯，路径上的任一结点为根节点到当前结点的路径和为sum的路径个数。 然后以同样的方法以自己的孩子结点为最终叶子结点计算。
     * 每一层向上追溯
     *
     * @param root      当前节点
     * @param sum       目标和
     * @param array     保存了节点路径上的值，题目限制不超过1k个
     * @param pathLevel 当前路径的深度
     * @return
     */
    public int pathSum(TreeNode root, int sum, int[] array, int pathLevel) {
        if (root == null) {
            return 0;
        }
        int tmp = root.val;
        // 判断当前节点是否等于目标值
        int sumRoot = root.val == sum ? 1 : 0;
        // 计算从当前节点开始（不包含）到父节点累加起来是否等于目标值
        for (int i = pathLevel - 1; i >= 0; i--) {
            tmp += array[i];
            if (tmp == sum) {
                sumRoot++;
            }
        }
        // 更新当前节点的值
        array[pathLevel] = root.val;
        // 在此处打印的是每个路径的值
        PrintConsoleUtil.printArray(array);
        System.out.println(sumRoot);
        // 以当前节点为根节点，计算左子树和右子树的满足条件的数量
        int sumLeft = pathSum(root.left, sum, array, pathLevel + 1);
        int sumRight = pathSum(root.right, sum, array, pathLevel + 1);
        // 返回累加的值
        return sumRoot + sumLeft + sumRight;
    }
}
