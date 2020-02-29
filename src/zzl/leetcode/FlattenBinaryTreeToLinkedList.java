package zzl.leetcode;

/**
 * @author zzl
 * @link {https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/}
 */
public class FlattenBinaryTreeToLinkedList {
    public static void main(String[] args) {
        /*
            1
           / \
          2   5
         / \   \
        3   4   6
        */
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node5 = new TreeNode(5);
        node2.left = new TreeNode(3);
        node2.right = new TreeNode(4);
        node5.left = new TreeNode(6);
        root.left = node2;
        root.right = node5;
        new FlattenBinaryTreeToLinkedList().flatten(root);
        System.out.println("> " + root);
    }


    /**
     * 后续遍历
     * 我们依次遍历 6 5 4 3 2 1，然后每遍历一个节点就将当前节点的右指针更新为上一个节点。
     * <p>
     * 遍历到 5，把 5 的右指针指向 6。6 <- 5 4 3 2 1。
     * <p>
     * 遍历到 4，把 4 的右指针指向 5。6 <- 5 <- 4 3 2 1。
     *
     * @param root
     * @link {https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--26/}
     */
    private TreeNode pre = null;

    public void flatten(TreeNode root) {
        if (root == null)
            return;
        flatten(root.right);
        flatten(root.left);
        // 右子树为临时节点，第一次（即最右子节点）时为null
        root.right = pre;
        // 左边不允许有数
        root.left = null;
        // pre 指向当前更新好的节点，等到最后一次就是指向的根节点
        pre = root;
        System.out.println(root);
    }

    /**
     * 1. 将左子树插入到右子树的地方
     * 2. 将原来的右子树接到左子树的最右边节点
     * 3. 考虑新的右子树的根节点，一直重复上边的过程，直到新的右子树为 null
     *
     * @param root
     * @link {https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--26/}
     */
    public void flatten1(TreeNode root) {
        while (root != null) {
            if (root.left != null) {
                // 先把right拿出来，临时保存
                TreeNode temp = root.right;
                System.out.println(temp);
                TreeNode current = root;
                // 然后把node的左节点拼接到右节点上
                root.right = root.left;
                root.left = null;
                // 循环遍历到最右子树
                while (current.right != null) {
                    current = current.right;
                }
                System.out.println(current);
                // 把先前临时保存的右子树拼接在当前的新的最右子树上
                current.right = temp;
                // 一次循环结束
                System.out.println(root);
                root = root.right;
            } else {
                // 因为要把左子树全部转到右子树，左子树都没了，就把当前的root节点设置为右子树
                // 此时右子树由于有从左边转移过去的，需要再次递归判断
                // 直到所有的右节点树都没有左子树
                root = root.right;
            }
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return "{" +
                    "\"val\":" + val +
                    ", \"left\":" + left +
                    ", \"right\":" + right +
                    '}';
        }
    }
}
