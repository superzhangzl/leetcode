package zzl.leetcode;

import zzl.base.ListNode;
import zzl.base.annotation.Level;
import zzl.util.GenerateUtil;
import zzl.util.SpecialAssertUtil;

import static zzl.base.enums.Difficulty.HARD;

/**
 * K 个一组翻转链表
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/reverse-nodes-in-k-group/}
 */
@Level(HARD)
public class ReverseNodesInKGroup {
    public static void main(String[] args) {
        ListNode head;
        head = GenerateUtil.generateListNode("1,2,3,4,5", ",");
        ListNode reverseKGroup;
        reverseKGroup = new ReverseNodesInKGroup().reverseKGroup(head, 2);
        SpecialAssertUtil.assertListNode(reverseKGroup, GenerateUtil.generateListNode("2,1,4,3,5", ","));
    }

    /**
     * 递归
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        ListNode a, b;
        a = b = head;
        for (int i = 0; i < k; i++) {
            if (b == null) {
                // 如果从head开始往后剩余的长度不够，直接返回当前段的头结点
                return head;
            }
            b = b.next;
        }
        // 反转[a,b)之间的链表，通过递归实现
        ListNode newHead = reverse(a, b);
        a.next = reverseKGroup(b, k);
        return newHead;
    }

    /**
     * 反转[a, b)之间的链表
     *
     * @param a
     * @param b
     * @return
     */
    private ListNode reverse(ListNode a, ListNode b) {
        ListNode pre, cur;
        pre = null;
        cur = a;
        while (cur != b) {
            // 首先把cur下一个节点临时保存起来
            ListNode next = cur.next;
            // cur的下一个节点指向pre，
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

}
