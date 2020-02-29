package zzl.leetcode;

/**
 * 输入: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出: 7 -> 0 -> 8
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/add-two-numbers/}
 */
public class AddTwoNumber {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

//        System.out.println(addTwoNumbers(l1, l2));
//
//        System.out.println(addTwoNumbers(l1, null));
//
//        l2.next.next = null;
//        System.out.println(addTwoNumbers(l1, l2));
//
//        l2.next.next = new ListNode(4);
//        l2.next.next.next = new ListNode(1);
//        System.out.println(addTwoNumbers(l1, l2));
//
//        l1 = new ListNode(4);
//        l2 = null;
//        System.out.println(addTwoNumbers(l1, l2));
//
//        l1 = null;
//        l2 = null;
//        System.out.println(addTwoNumbers(l1, l2));

//        l1 = new ListNode(5);
//        l2 = new ListNode(5);
//        System.out.println(addTwoNumbers(l1, l2));
        l1 = new ListNode(1);
        l2 = new ListNode(9);
        l2.next = new ListNode(9);
        System.out.println(addTwoNumbers(l1, l2));
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 其中一个为null则返回0，防止后续出现NPE
        if (l1 == null && l2 == null) {
            return new ListNode(0);
        }
        // 表示进位的flag，初始化为0
        int flag = 0;
        // 初始化任意一个值，只是为了不出现NPE
        ListNode result = new ListNode(-1);
        ListNode resultIndex = result;
        while (l1 != null || l2 != null) {
            // 若长度不一致，差的就补全为0
            if (l1 == null) {
                l1 = new ListNode(0);
            }
            if (l2 == null) {
                l2 = new ListNode(0);
            }
            // todo assert  0 <= val < 10
            int currentValue = (l1.val + l2.val + flag);
            // 当前节点之和加上一级的进位
            ListNode currentNode = new ListNode(currentValue % 10);
            // 计算当前节点需要向下一级node进位的值
            flag = currentValue / 10;
            resultIndex.next = currentNode;
            if (flag > 0) {
                currentNode.next = new ListNode(flag);
            }
            l1 = l1.next;
            l2 = l2.next;
            resultIndex = resultIndex.next;
        }
        // 从第二个节点开始返回
        return result.next;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }
}


