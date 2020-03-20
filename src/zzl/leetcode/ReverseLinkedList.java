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

    public ListNode reverseList(ListNode head) {
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
