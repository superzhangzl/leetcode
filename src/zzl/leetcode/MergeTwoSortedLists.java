package zzl.leetcode;

import zzl.base.ListNode;
import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

/**
 * 合并两个有序链表
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/merge-two-sorted-lists/}
 */
public class MergeTwoSortedLists {
    public static void main(String[] args) {
        ListNode l1 = GenerateUtil.generateListNode("1->2->4", "->");
        ListNode l2 = GenerateUtil.generateListNode("1->3->4", "->");
        new MergeTwoSortedLists().mergeTwoLists(l1, l2);
        l1 = GenerateUtil.generateListNode("5", "->");
        l2 = GenerateUtil.generateListNode("1->2->4", "->");
        new MergeTwoSortedLists().mergeTwoLists(l1, l2);
        l1 = GenerateUtil.generateListNode("-10,-9,-6,-4,1,9,9", ",");
        l2 = GenerateUtil.generateListNode("-5,-3,0,7,8,8", ",");
        new MergeTwoSortedLists().mergeTwoLists(l1, l2);//[-10,-9,-6,-5,-4,-3,0,1,7,8,8,9,9]
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        // 特殊情况判定
        if (l1 == null && l2 != null) {
            return l2;
        } else if (l1 != null && l2 == null) {
            return l1;
        } else if (l1 == null && l2 == null) {
            return null;
        }
        // 将头节点较小的那一个作为被插入链表，这样可以保证起始点是最小的
        if (l1.val <= l2.val) {
            return merge(l1, l2);
        } else {
            return merge(l2, l1);
        }
    }

    /**
     * 移动l1，并将l2中的每一个节点插入的合理的位置
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode merge(ListNode l1, ListNode l2) {
        // 防止数字和驼峰影响阅读，使用下划线
        ListNode head = l1;
        ListNode l1_index = l1;
        ListNode l2_index = l2;

        while (l1_index != null && l2_index != null) {
            // 合并l1结尾没有内容的情况，直接把l2续上就行
            if (l1_index.val <= l2_index.val && l1_index.next == null) {
                l1_index.next = new ListNode(l2_index.val);
                l1_index = l1_index.next;
                l2_index = l2_index.next;
            } else if (l1_index.val <= l2_index.val && l1_index.next.val >= l2_index.val) {
                //  先保存当前l1的下一个节点
                ListNode next = l1_index.next;
                // 给l1续上一个新的节点
                l1_index.next = new ListNode(l2_index.val);
                // 把l1的下一个节点的下一个节点接上临时保存的next，次数插入新结点完成
                l1_index.next.next = next;
                // 向后移动游标
                l1_index = l1_index.next;
                l2_index = l2_index.next;
            } else {
                // l2当前节点的值大于l1节点以及l1节点的下一个节点，就是还没到合理的位置
                l1_index = l1_index.next;
            }
            PrintConsoleUtil.printListNode(head);
        }
        return head;
    }
}
