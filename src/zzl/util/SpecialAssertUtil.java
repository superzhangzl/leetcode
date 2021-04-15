package zzl.util;

import java.util.List;

public class SpecialAssertUtil {

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
