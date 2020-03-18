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
     * 通过使用具有 不同速度 的快、慢两个指针遍历链表，空间复杂度可以被降低至 O(1)。慢指针每次移动一步，而快指针每次移动两步。
     * 如果列表中不存在环，最终快指针将会最先到达尾部，此时我们可以返回 false
     *
     * @param head
     * @return
     * @link {https://leetcode-cn.com/problems/linked-list-cycle/solution/huan-xing-lian-biao-by-leetcode/}
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        // 如果存在环，快指针会在环上比慢指针多走一圈然后再重合
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    /**
     * 你能用 O(1)（即，常量）内存解决此问题吗？
     * 能：
     * 思路：每次遍历是，断开他的next节点，并且将val设置为一个特殊值
     * 当遍历到尾部，走到下一个环的连接点时，next=null && val=特殊值，就说明这个访问过，直接返回true即可
     *
     * @param head
     * @return
     */
    public boolean hasCycle2(ListNode head) {
        ListNode temp;
        while (head != null) {
            if (head.next == null && head.val == Integer.MIN_VALUE) {
                return true;
            }
            temp = head.next;
            head.next = null;
            head.val = Integer.MIN_VALUE;
            head = temp;
        }
        return false;
    }

    /**
     * 最普通的方法，空间复杂度为O(n)
     *
     * @param head
     * @return
     */
    public boolean hasCycleStupid(ListNode head) {
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
