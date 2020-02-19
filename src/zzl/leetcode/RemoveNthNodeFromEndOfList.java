package zzl.leetcode;

/**
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * <p>
 * 示例：
 * <p>
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * <p>
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * <p>
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/}
 */
public class RemoveNthNodeFromEndOfList {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        System.out.println(removeNthFromEnd(node1, 2));
        node1 = new ListNode(1);
//        node1.next = new ListNode(2);
        System.out.println(removeNthFromEnd(node1, 1));
        node1 = new ListNode(1);
//        node1.next = new ListNode(2);
        System.out.println(removeNthFromEnd(node1, 0));
        node1 = new ListNode(1);
        node1.next = new ListNode(2);
        System.out.println(removeNthFromEnd(node1, 2));
        node1 = new ListNode(1);
        node1.next = new ListNode(2);
        node1.next.next = new ListNode(3);
        System.out.println(removeNthFromEnd(node1, 1));
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        if (head.next == null && n == 1) {
            return null;
        }
        ListNode index = head;
        for (int i = 0; i < n; i++) {
            index = index.next;
        }
        // 如果游标达到末尾，直接返回head的下一个节点即可
        if (index == null) {
            return head.next;
        }
        ListNode deleteNode = null;
        // 此处由更好的处理办法，new一个对象，将其next节点设置为head，后续会减少判断条件
        deleteNode = head;
        while (index.next != null) {
            index = index.next;
            deleteNode = deleteNode.next;
        }
        if (deleteNode == null) {
            return head;
        }
        if (deleteNode.next != null) {
            deleteNode.next = deleteNode.next.next;
        } else {
            return head;
        }
        return head;
    }
}

