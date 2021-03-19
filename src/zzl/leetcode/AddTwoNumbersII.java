package zzl.leetcode;

import zzl.base.ListNode;
import zzl.util.GenerateUtil;

import java.util.Stack;

/**
 * 两数相加 II
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/add-two-numbers-ii/}
 */
public class AddTwoNumbersII {
    public static void main(String[] args) {
        ListNode l1;
        ListNode l2;
        ListNode node;
        //===
        l1 = GenerateUtil.generateListNode("7 -> 2 -> 4 -> 3", " -> ");
        l2 = GenerateUtil.generateListNode("5 -> 6 -> 4", " -> ");
        node = new AddTwoNumbersII().addTwoNumbers(l1, l2);
        System.out.println(node);
    }

    /**
     * 使用栈来保存每一位的数字，再通过保留进位信息，最终将结果追加到链表上
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> left = convert2Stack(l1);
        Stack<Integer> right = convert2Stack(l2);
        System.out.println(left);
        System.out.println(right);
        int carry = 0;
        ListNode result = null;
        while (!left.isEmpty() || !right.isEmpty() || carry != 0) {
            int a = left.isEmpty() ? 0 : left.pop();
            int b = right.isEmpty() ? 0 : right.pop();
            // 0 <= a+b <= 18, 加上进位最大为19，通过模10能包含的住
            int cur = a + b + carry;
            carry = cur / 10;
            cur %= 10;
            // 追加到链表的结尾
            ListNode curnode = new ListNode(cur);
            curnode.next = result;
            result = curnode;
        }
        return result;
    }

    private Stack<Integer> convert2Stack(ListNode ln) {
        Stack<Integer> stack = new Stack<>();
        while (ln != null) {
            stack.push(ln.val);
            ln = ln.next;
        }
        return stack;
    }
}
