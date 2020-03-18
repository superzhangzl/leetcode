package zzl.leetcode;

import zzl.base.ListNode;

/**
 * 环形链表 II
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * 说明：不允许修改给定的链表。
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/linked-list-cycle-ii/}
 */
public class LinkedListCycleII {
    public static void main(String[] args) {
        // head = [3,2,0,-4], pos = 1
        ListNode head = new ListNode(3);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(0);
        ListNode node3 = new ListNode(-4);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node1;
        System.out.println(new LinkedListCycleII().detectCycle(head).val);
        //head = [1,2], pos = 0
        head = new ListNode(1);
        node1 = new ListNode(2);
        head.next = node1;
        node1.next = head;
        System.out.println(new LinkedListCycleII().detectCycle(head).val);
        head = new ListNode(1);
        System.out.println(new LinkedListCycleII().detectCycle(head));
    }

    /**
     * 获取快慢指针相遇的点
     * 注：此处的写法和{@link LinkedListCycle}里快慢指针的写法不同
     *
     * @param head
     * @return
     */
    private ListNode getIntersect(ListNode head) {
        ListNode tortoise = head;
        ListNode hare = head;
        // 快速指针将在一个循环中循环并遇到慢速指针，或者在非循环列表的末尾到达“ null”。
        while (hare != null && hare.next != null) {
            tortoise = tortoise.next;
            hare = hare.next.next;
            if (tortoise == hare) {
                return tortoise;
            }
        }
        return null;
    }

    /**
     * @param head
     * @return
     * @link {https://leetcode-cn.com/problems/linked-list-cycle-ii/solution/huan-xing-lian-biao-ii-by-leetcode/}
     */
    public ListNode detectCycle(ListNode head) {
        // 先使用快慢指针，判断是否包含环

        ListNode intersect = getIntersect(head);
        if (intersect == null) {
            return null;
        }
        // 如果包含环，则查找入口
        ListNode ptr1 = head;
        ListNode ptr2 = intersect;
        while (ptr1 != ptr2) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }
        return ptr1;
    }
}
