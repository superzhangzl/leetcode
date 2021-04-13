package zzl.leetcode;

import zzl.base.ListNode;
import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 对链表进行插入排序
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/insertion-sort-list/}
 */
public class InsertionSortList {
    public static void main(String[] args) {
        ListNode head;
        ListNode insertionSortList;
        //
        head = GenerateUtil.generateListNode("4->2->1->3");
        insertionSortList = new InsertionSortList().insertionSortList(head);
        PrintConsoleUtil.printListNode(insertionSortList);
        //
        head = GenerateUtil.generateListNode("-1->5->3->4->0");
        insertionSortList = new InsertionSortList().insertionSortList(head);
        PrintConsoleUtil.printListNode(insertionSortList);
    }

    /**
     * 插入排序
     *
     * @param head
     * @return
     */
    public ListNode insertionSortList(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode preNode = new ListNode(-999);
        preNode.next = head;
        // stop保存的有序的最后一个节点
        // point指向head的next，因为第一个节点本身就是有序的
        ListNode stop = head, point = head.next;
        // 在原来链的基础上修改
        while (point != null) {
            // 如果前面的位置已经有序，那就把停止符后移一位
            if (stop.val <= point.val) {
                stop = stop.next;
            } else {
                // 从头遍历，找合适的位置
                ListNode tmp = preNode;
                while (tmp != null && tmp.next.val <= point.val) {
                    tmp = tmp.next;
                }
                // 标记停止符位置
                stop.next = point.next;
                // 将当前节点插入合适的位置
                // 1. 将当前节点防止有序的节点之后节点接在当前节点后
                point.next = tmp.next;
                // 2. 有序的节点之后接当前节点
                tmp.next = point;
            }
            // point之前stop保存的无序的开始
            point = stop.next;
        }
        return preNode.next;
    }

    /**
     * 不符合题意的做法，该算法将链表转成了数组，然后用数组的库函数排序后转为链表。
     * 没有在链表的基础上操作
     *
     * @param head
     * @return
     */
    @Deprecated
    public ListNode insertionSortList_Bad(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode node = head;
        while (node != null) {
            list.add(node.val);
            node = node.next;
        }
        list.sort(Integer::compareTo);
        int length = list.size();
        ListNode preRoot = new ListNode(-999);
        ListNode chead = preRoot;
        for (int i = 0; i < length; i++) {
            preRoot.next = new ListNode(list.get(i));
            preRoot = preRoot.next;
        }
        return chead.next;
    }
}
