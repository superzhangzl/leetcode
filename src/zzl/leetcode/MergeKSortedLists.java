package zzl.leetcode;

import zzl.base.ListNode;
import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

import java.util.PriorityQueue;

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
     * 使用优先级队列（最大堆堆）填充序列，然后生成node
     * 该方法比两两合并的效率高非常多
     * 暴力法起始更容易，只需要排个序就好了
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<Integer> heap =
                new PriorityQueue<>((n1, n2) -> n1 - n2);
        for (ListNode node : lists) {
            if (node == null) {
                continue;
            }
            while (node != null) {
                heap.add(node.val);
                node = node.next;
            }
        }
        System.out.println(heap);
        ListNode preRoot = new ListNode(-999);
        ListNode head = preRoot;
        while (!heap.isEmpty()) {
            // 需要poll出来的才是有序的
            preRoot.next = new ListNode(heap.poll());
            preRoot = preRoot.next;
        }
        return head.next;
    }

    /**
     * 按照{@link MergeTwoSortedLists}中的思路，两两合并
     * 但还是会比较耗时，并不是最优
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists_between(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        ListNode first = lists[0];
        for (int i = 1; i < lists.length; i++) {
            // 代码已提交git和leetcode，就在此处精简一下
            ListNode merge = new MergeTwoSortedLists().mergeTwoLists(first, lists[i]);
            first = merge;
        }
        return first;
    }
}
