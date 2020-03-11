package zzl.leetcode;

import zzl.base.ListNode;
import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

/**
 * 合并K个排序链表
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/merge-k-sorted-lists/}
 */
public class MergeKSortedLists {
    public static void main(String[] args) {
        ListNode[] listNodes = {
                GenerateUtil.generateListNode("1->4->5", "->"),
                GenerateUtil.generateListNode("1->3->4", "->"),
                GenerateUtil.generateListNode("2->6", "->")
        };
        ListNode lists = new MergeKSortedLists().mergeKLists(listNodes);
        PrintConsoleUtil.printListNode(lists);
        ListNode[] listNodes2 = {
                null,
                GenerateUtil.generateListNode("1", "->"),
        };
        lists = new MergeKSortedLists().mergeKLists(listNodes2);
        PrintConsoleUtil.printListNode(lists);

    }

    /**
     * 按照{@link MergeTwoSortedLists}中的思路，两两合并
     * 但还是会比较耗时，并不是最优
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        ListNode first = lists[0];
        for (int i = 1; i < lists.length; i++) {
            ListNode merge = mergeTwoLists(first, lists[i]);
            first = merge;
        }
        return first;
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
