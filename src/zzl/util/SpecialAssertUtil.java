package zzl.util;

import zzl.base.ListNode;

import java.util.List;
import java.util.stream.Collectors;

public class SpecialAssertUtil {

    /**
     * 校验两个顺序链表的值是否一致
     *
     * @param expected
     * @param actual
     */
    public static void assertListNode(ListNode expected, ListNode actual) {
        ListNode na = expected, nb = actual;
        while (na != null && nb != null) {
            if (na.val != nb.val) {
                throw new AssertionError(String.format("[%d] is not equal actual listNode [%d]", na.val, nb.val));
            } else {
                na = na.next;
                nb = nb.next;
            }
        }
        if (na != null || nb != null) {
            throw new AssertionError(String.format("listNode length not equal"));
        }
    }

    public static void assertIntListContain(List<Integer> expected, List<Integer> actual) {
        assertStringListContain(expected.stream().map(String::valueOf).collect(Collectors.toList()),
                actual.stream().map(String::valueOf).collect(Collectors.toList()));
    }

    /**
     * 校验String 类型的数组元素是否包含，不校验顺序，只要包含即可
     * <p>
     * 注：本地判断，不考虑性能问题
     *
     * @param expected
     * @param actual
     */
    public static void assertStringListContain(List<String> expected, List<String> actual) {
        // 先判断当前列表是否都在目标列表中
        for (String s : expected) {
            if (!actual.contains(s)) {
                throw new AssertionError(String.format("\"%s\" is not in actual list %s", s, actual));
            }
        }
        // 反向判断expect数组
        for (String s : actual) {
            if (!expected.contains(s)) {
                throw new AssertionError(String.format("\"%s\" is not in expected list %s", s, expected));
            }
        }
        // 最后保底判断长度是否相同
        if (expected.size() != actual.size()) {
            throw new AssertionError("list size not equal");
        }
    }
}
