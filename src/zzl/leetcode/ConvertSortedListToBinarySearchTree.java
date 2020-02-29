package zzl.leetcode;

/**
 * @author zzl
 * @link {https://leetcode-cn.com/problems/convert-sorted-list-to-binary-search-tree/}
 */
public class ConvertSortedListToBinarySearchTree {
    public static void main(String[] args) {
//        [-10, -3, 0, 5, 9]
        ListNode linkedNode = generate(new int[]{5, 9});
        System.out.println(new ConvertSortedListToBinarySearchTree().sortedListToBST(linkedNode));
        linkedNode = generate(new int[]{-10, -3, 0, 5, 9});
        System.out.println(new ConvertSortedListToBinarySearchTree().sortedListToBST(linkedNode));
//        linkedNode = generate(new int[]{-10, -3, 0, 5, 9, 10});
//        System.out.println(new ConvertSortedListToBinarySearchTree().sortedListToBST(linkedNode));
//        linkedNode = generate(new int[]{-10});
//        System.out.println(new ConvertSortedListToBinarySearchTree().sortedListToBST(linkedNode));
//        linkedNode = null;
//        System.out.println(new ConvertSortedListToBinarySearchTree().sortedListToBST(linkedNode));

    }

    public static ListNode generate(int[] nums) {
        ListNode head = new ListNode(nums[0]);
        ListNode index = head;
        for (int i = 1; i < nums.length; i++) {
            ListNode node = new ListNode(nums[i]);
            index.next = node;
            index = index.next;
        }
        System.out.println(head);
        return head;
    }


    private ListNode findMiddleElement(ListNode head) {
        // The pointer used to disconnect the left half from the mid node.
        ListNode prevPtr = null;
        // 每次走一步的指针
        ListNode onceStepPointer = head;
        // 每次走两步的指针
        ListNode twiceStepPointer = head;
        while (twiceStepPointer != null && twiceStepPointer.next != null) {
            System.out.println("once  :> " + onceStepPointer);
            System.out.println("twice :> " + twiceStepPointer);
            prevPtr = onceStepPointer;
            onceStepPointer = onceStepPointer.next;
            twiceStepPointer = twiceStepPointer.next.next;
        }
        // 需要有一个指针，来截断once左半边的的链表
        if (prevPtr != null) {
            // 此时prevPtr.next = onceStepPointer
            // 不然由于左半边递归会一直无限递归下去！！！！！
            prevPtr.next = null;
        }
        System.out.println("res :> " + onceStepPointer);
        return onceStepPointer;
    }

    /**
     * 高度平衡的二叉树，不然特殊情况下有序链表也是一种平衡二叉树
     * 1. 使用快慢指针从链表中查找中间位置的元素
     *
     * @param head
     * @return
     */
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode middleElement = findMiddleElement(head);
        TreeNode middleRoot = new TreeNode(middleElement.val);

        if (head == middleElement) {
            return middleRoot;
        }

        // Recursively form balanced BSTs using the left and right halves of the original list.
        middleRoot.left = sortedListToBST(head);
        middleRoot.right = sortedListToBST(middleElement.next);

        return middleRoot;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return "{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
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
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
}
