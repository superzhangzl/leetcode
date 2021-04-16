package zzl.leetcode;

import zzl.base.ListNode;
import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

/**
 * 排序链表
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/sort-list/}
 */
public class SortList {
    public static void main(String[] args) {
        ListNode listNode;
        ListNode sortList;
        //
        listNode = GenerateUtil.generateListNode("-1,5,3,4,0", ",");
        sortList = new SortList().sortList(listNode);
        PrintConsoleUtil.printListNode(sortList);

    }

    /**
     * 详见链表插入排序算法题，如果不考虑要求，直接读取重新生成链表最快
     * 或者没有要求使用归并排序时间复杂度低
     *
     * @param head
     * @return
     * @see InsertionSortList#insertionSortList(ListNode)
     */
    public ListNode sortList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode preHead = new ListNode(999);
        preHead.next = head;
        ListNode sortedPosition = head;
        ListNode index = head.next;

        while (index != null) {
            if (sortedPosition.val <= index.val) {
                sortedPosition = sortedPosition.next;
            } else {
                ListNode tmp = preHead;
                while (tmp != null && tmp.next.val <= index.val) {
                    tmp = tmp.next;
                }
                sortedPosition.next = index.next;
                index.next = tmp.next;
                tmp.next = index;
            }
            index = sortedPosition.next;
        }
        return preHead.next;
    }
}
