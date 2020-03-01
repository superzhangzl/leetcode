package zzl.leetcode;

import zzl.base.ListNode;
import zzl.base.TreeNode;

import java.util.ArrayList;
import java.util.List;

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

    private List<Integer> array = new ArrayList<>();

    public TreeNode sortedListToBST(ListNode head) {
        generateListNameToArray(head);

        if (array.isEmpty()) {
            return null;
        }
        TreeNode root = listToNode(0, array.size() - 1);
        return root;
    }

    private TreeNode listToNode(int start, int end) {
        System.out.println("start=" + start + ", end=" + end);
        if (start > end) {
            return null;
        }
        int middleIndex = (end + start) / 2;
        System.out.println(middleIndex);
        TreeNode node = new TreeNode(array.get(middleIndex));
        if (start == end) {
            return node;
        }
        System.out.println(array);
        node.left = listToNode(start, middleIndex - 1);
        node.right = listToNode(middleIndex + 1, end);
        return node;
    }

    private void generateListNameToArray(ListNode head) {
        if (head == null) {
            return;
        }
        ListNode index = head;
        while (index != null) {
            array.add(index.val);
            index = index.next;
        }
    }


    private ListNode findMiddleElement(ListNode head) {
        // 用于从中间位置截断
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
    public TreeNode sortedListToBST2(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode middleElement = findMiddleElement(head);
        TreeNode middleRoot = new TreeNode(middleElement.val);

        if (head == middleElement) {
            return middleRoot;
        }

        // Recursively form balanced BSTs using the left and right halves of the original list.
        middleRoot.left = sortedListToBST2(head);
        middleRoot.right = sortedListToBST2(middleElement.next);

        return middleRoot;
    }
}
