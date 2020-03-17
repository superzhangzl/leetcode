package zzl.leetcode;

import org.junit.Assert;
import zzl.base.ListNode;

import java.util.LinkedList;
import java.util.List;

/**
 * 环形链表
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/linked-list-cycle/}
 */
public class LinkedListCycle {
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
        Assert.assertEquals(new LinkedListCycle().hasCycle(head), true);
        //head = [1,2], pos = 0
        head = new ListNode(1);
        node1 = new ListNode(2);
        head.next = node1;
        node1.next = head;
        Assert.assertEquals(new LinkedListCycle().hasCycle(head), true);
        head = new ListNode(1);
        Assert.assertEquals(new LinkedListCycle().hasCycle(head), false);
    }

    /**
     * 最普通的方法，空间复杂度为O(n)
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        List<ListNode> list = new LinkedList<>();
        while (head != null) {
            if (list.contains(head)) {
                return true;
            }
            list.add(head);
            head = head.next;
        }
        return false;
    }
}
