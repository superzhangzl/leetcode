package zzl.leetcode;

import zzl.base.ListNode;
import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

import java.util.Stack;

/**
 * @author zzl
 * @link {https://leetcode-cn.com/problems/reverse-linked-list/}
 */
public class ReverseLinkedList {
    public static void main(String[] args) {
        ListNode listNode = GenerateUtil.generateListNode("1->2->3->4->5", "->");
        PrintConsoleUtil.printListNode(listNode);
        PrintConsoleUtil.printListNode(new ReverseLinkedList().reverseList(listNode));
        listNode = GenerateUtil.generateListNode("1", "->");
        PrintConsoleUtil.printListNode(listNode);
        PrintConsoleUtil.printListNode(new ReverseLinkedList().reverseList(listNode));
    }

    /**
     * 将当前节点的指针指向上一个（额外的变量来保存）
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    /**
     * 使用堆栈保存信息，空间复杂度为O(n)
     *
     * @param head
     * @return
     */
    public ListNode reverseListStack(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        while (head != null) {
            stack.push(head.val);
            head = head.next;
        }
        System.out.println(stack);
        if (stack.isEmpty()) {
            return null;
        }
        head = new ListNode(stack.pop());
        ListNode index = head;
        while (!stack.isEmpty()) {
            index.next = new ListNode(stack.pop());
            index = index.next;
        }
        return head;
    }
}
