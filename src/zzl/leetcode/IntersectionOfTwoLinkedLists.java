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
     * hash法：比较耗时
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
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
