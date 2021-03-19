package zzl.leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 驼峰式匹配
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/camelcase-matching/}
 */
public class CamelcaseMatching {
    public static void main(String[] args) {
        String[] queries;
        String pattern;
        List<Boolean> list;
        //===
        queries = new String[]{"FooBar", "FooBarTest", "FootBall", "FrameBuffer", "ForceFeedBack"};
        pattern = "FB";
        list = new CamelcaseMatching().camelMatch(queries, pattern);
        System.out.println(list);
        //===
        queries = new String[]{"FooBar", "FooBarTest", "FootBall", "FrameBuffer", "ForceFeedBack"};
        pattern = "FoBa";
        list = new CamelcaseMatching().camelMatch(queries, pattern);
        System.out.println(list);
        //===
        queries = new String[]{"FooBar", "FooBarTest", "FootBall", "FrameBuffer", "ForceFeedBack"};
        pattern = "FoBaT";
        list = new CamelcaseMatching().camelMatch(queries, pattern);
        System.out.println(list);
        //===
        queries = new String[]{"CompetitiveProgramming", "CounterPick", "ControlPanel"};
        pattern = "CooP";
        list = new CamelcaseMatching().camelMatch(queries, pattern);
        // [false, false, true]
        System.out.println(list);
        //===
        queries = new String[]{"aksvbjLiknuTzqon", "ksvjLimflkpnTzqn", "mmkasvjLiknTxzqn", "ksvjLiurknTzzqbn", "ksvsjLctikgnTzqn", "knzsvzjLiknTszqn"};
        pattern = "ksvjLiknTzqn";
        list = new CamelcaseMatching().camelMatch(queries, pattern);
        // [true,true,true,true,true,true]
        System.out.println(list);
    }

    /**
     * 构造正则表达式去匹配
     *
     * @param queries
     * @param pattern
     * @return
     */
    public List<Boolean> camelMatch(String[] queries, String pattern) {
        StringBuilder sb = new StringBuilder();
        String subReg = "[a-z]*";
        sb.append(subReg);
        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            sb.append(c).append(subReg);
        }
        String regex = sb.toString();
        return Arrays.stream(queries).map(query -> query.matches(regex)).collect(Collectors.toList());
    }
}
