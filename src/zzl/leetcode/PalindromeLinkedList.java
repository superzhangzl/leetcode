package zzl.leetcode;

import org.junit.Assert;
import zzl.base.ListNode;
import zzl.util.GenerateUtil;

import java.util.*;

/**
 * 回文链表
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/palindrome-linked-list/}
 */
public class PalindromeLinkedList {
    public static void main(String[] args) {
        ListNode listNode = GenerateUtil.generateListNode("1->2->2->1", "->");
        Assert.assertEquals(new PalindromeLinkedList().isPalindrome(listNode), true);
        listNode = GenerateUtil.generateListNode("1->2", "->");
        Assert.assertEquals(new PalindromeLinkedList().isPalindrome(listNode), false);
        listNode = GenerateUtil.generateListNode("1->2->1", "->");
        Assert.assertEquals(new PalindromeLinkedList().isPalindrome(listNode), true);
        listNode = GenerateUtil.generateListNode("-129->-129", "->");
        Assert.assertEquals(new PalindromeLinkedList().isPalindrome(listNode), true);
    }

    /**
     * 双向队列
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        Deque<Integer> dq = new ArrayDeque<>();
        while (head != null) {
            dq.add(head.val);
            head = head.next;
        }
        while (dq.size() > 1) {
            Integer first = dq.removeFirst();
            Integer last = dq.removeLast();
            if (!first.equals(last)) {
                return false;
            }
        }
        return true;
    }
}
