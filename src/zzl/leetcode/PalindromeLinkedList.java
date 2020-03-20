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
     * leetcode上用时最短
     * 核心思路就是通过快慢指针每次访问到回文的两个端点
     * 注：会破坏掉链结构
     *
     * @param head
     * @return
     */
    public boolean isPalindromeBest(ListNode head) {
        //1.判断是否是一个节点
        if (head == null || head.next == null) return true;
        //2判断是否是2个节点
        if (head.next.next == null) return head.val == head.next.val;
        //如果都不是
        //1.设定一个slow指针指向当前回文遍历的字符，设定一个fast指针，遍历到slow对应回文的后面的节点的前一个节点，也就是fast.next.val==slow.val
        ListNode slow = head, fast = head.next;
        //2.这样不断循环推进slow，然后把fast设置为slow.next开始遍历，循环后退，每次匹配成功，然后就把fast后面的断开，并把slow推进
        //如果匹配不成功，则继续推进fast到fast.next==null位置说明没有位置匹配了
        while (fast.next != null) {
            if (slow.val == fast.next.val) {
                //如果匹配成功
                if (fast.next.next != null) {
                    //如果不是和最后一个位置的字符匹配，说明回文中间掺杂其他字符，不连续
                    return false;
                } else {
                    fast.next = null;
                    slow = slow.next;
                    fast = slow.next;
                }
                //判断是否循环结束,这里区分奇和偶，如果是奇数个回文
                if (fast == null || (fast.next == null && slow.val == fast.val)) {
                    return true;
                }
            } else {
                fast = fast.next;
            }
        }
        return false;
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
