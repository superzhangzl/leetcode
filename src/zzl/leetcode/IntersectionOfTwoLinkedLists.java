package zzl.leetcode;

import zzl.base.ListNode;
import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

import java.util.*;

/**
 * 相交链表
 * 找到两个单链表相交的起始节点。
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/intersection-of-two-linked-lists/}
 */
public class IntersectionOfTwoLinkedLists {
    public static void main(String[] args) {
        ListNode partA = GenerateUtil.generateListNode("4,1", ",");
        ListNode partB = GenerateUtil.generateListNode("5,0,1", ",");
        ListNode common = GenerateUtil.generateListNode("8,4,5", ",");
        partA.next.next = common;
        partB.next.next.next = common;
        PrintConsoleUtil.printListNode(partA);
        PrintConsoleUtil.printListNode(partB);
        System.out.println(partA.next.next == partB.next.next.next); // true
        System.out.println(new IntersectionOfTwoLinkedLists().getIntersectionNode(partA, partB).val); // 8
    }

    /**
     * 4 -> 1 -> 8 -> 4 -> 5 -> 5 -> 0 -> 1 -> 8 -> 4 -> 5
     * 5 -> 0 -> 1 -> 8 -> 4 -> 5 -> 4 -> 1 -> 8 -> 4 -> 5
     * ========================================|
     *
     * 创建两个指针 pApA 和 pBpB，分别初始化为链表 A 和 B 的头结点。然后让它们向后逐结点遍历。
     * 当 pApA 到达链表的尾部时，将它重定位到链表 B 的头结点 (你没看错，就是链表 B); 类似的，当 pBpB 到达链表的尾部时，将它重定位到链表 A 的头结点。
     * 若在某一时刻 pApA 和 pBpB 相遇，则 pApA/pBpB 为相交结点。
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode indexA = headA;
        ListNode indexB = headB;
        while (indexA != indexB) {
            if (indexA == null) {
                indexA = headB;
            } else {
                indexA = indexA.next;
            }
            if (indexB == null) {
                indexB = headA;
            } else {
                indexB = indexB.next;
            }
        }
        return indexA;
    }

    /**
     * hash法：比较耗时
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNodeHash(ListNode headA, ListNode headB) {
        HashMap<ListNode, Integer> list = new HashMap<>();
        while (headA != null) {
            list.put(headA, 1);
            headA = headA.next;
        }
        while (headB != null) {
            if (list.containsKey(headB)) {
                return headB;
            }
            headB = headB.next;
        }
        return null;
    }
}
