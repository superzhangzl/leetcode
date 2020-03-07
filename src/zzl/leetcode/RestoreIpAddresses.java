package zzl.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * 复原IP地址
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/restore-ip-addresses/}
 */
public class RestoreIpAddresses {
    public static void main(String[] args) {
        // ["255.255.11.135", "255.255.111.35"]
        System.out.println(new RestoreIpAddresses().restoreIpAddresses("25525511135"));
        // ["0.10.0.10","0.100.1.0"]
        System.out.println(new RestoreIpAddresses().restoreIpAddresses("010010"));
        //
        System.out.println(new RestoreIpAddresses().restoreIpAddresses("0000"));
    }

    private List<List<String>> result = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        if (s == null || s.length() == 0) {
            return Collections.emptyList();
        }
        Stack<String> addresses = new Stack<>();
        dfs(s, 0, 0, addresses);
        return result.stream().map(one -> one.stream().collect(Collectors.joining("."))).collect(Collectors.toList());
    }

    /**
     * 使用递归获取所有的可行的列表，然后拼接成字符串返回
     *
     * @param s
     * @param start
     * @param depth
     * @param addresses
     */
    private void dfs(String s, int start, int depth, Stack<String> addresses) {
        if (depth == 4) {
            if (start == s.length()) {
                result.add(new ArrayList<>(addresses));
            }
            return;
        }
        for (int i = start + 1; i <= s.length(); i++) {
            String point = s.substring(start, i);
            // 剪枝，长度超过3的或者大于255的，或者长度大于1且以0开头的
            // 起始可以对stack和剩余字符串长度再进行剪枝，还能减少时长
            if (i - start > 3 || Integer.parseInt(point) > 255 || (point.length() >= 2 && point.charAt(0) == '0')) {
                continue;
            }
            // 优化一下，当剩余的子串数大于最大可添加的长度，那肯定连接不完的，直接跳过即可
            int least = 4 - addresses.size();
            if (s.length() - i > least * 3) {
                continue;
            }
            addresses.push(point);
            dfs(s, i, depth + 1, addresses);
            addresses.pop();
        }
    }
}
