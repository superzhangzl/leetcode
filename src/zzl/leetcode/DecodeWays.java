package zzl.leetcode;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 解码方法@link {https://leetcode-cn.com/problems/decode-ways/}
 *
 * @author zzl
 */
public class DecodeWays {
    public static void main(String[] args) {
        Assert.assertEquals(new DecodeWays().numDecodings("1222"), 5);
//        Assert.assertEquals(new DecodeWays().numDecodings("12"), 2);
//        Assert.assertEquals(new DecodeWays().numDecodings("226"), 3);
//        Assert.assertEquals(new DecodeWays().numDecodings("227"), 2);
//        Assert.assertEquals(new DecodeWays().numDecodings("0"), 0);
//        Assert.assertEquals(new DecodeWays().numDecodings("10"), 1);
//        Assert.assertEquals(new DecodeWays().numDecodings("01"), 0);
//        Assert.assertEquals(new DecodeWays().numDecodings("100"), 0);
//        Assert.assertEquals(new DecodeWays().numDecodings("101"), 1);
//        Assert.assertEquals(new DecodeWays().numDecodings("110"), 1);
//        Assert.assertEquals(new DecodeWays().numDecodings("99"), 1);
//        Assert.assertEquals(new DecodeWays().numDecodings("4757562545844617494555774581341211511296816786586787755257741178599337186486723247528324612117156948"), 589824);
    }


    /**
     * 个人感觉这个题型和小青蛙跳台阶类似，一次可以跳一个或者跳两个，问到多少级需要跳多少次
     *
     * @param s
     * @return
     * @link {https://leetcode-cn.com/problems/decode-ways/solution/dong-tai-gui-hua-ting-jian-dan-de-a-by-cai-liao-xi/}
     */
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        if (s.charAt(0) == '0') {
            return 0;
        }
        dp[1] = 1;
        // todo 可优化使用两个变量保存[i],[i-1]
        for (int i = 1; i < s.length(); i++) {
            int j = (s.charAt(i - 1) - '0') * 10 + s.charAt(i) - '0';//和前一个字符合起来的值
            if (s.charAt(i) > '0') {
                //此处不为0，继承前一项的方法数
                dp[i + 1] = dp[i];
            }
            if (j > 9 && j <= 26) {
                // 只有当有[10,26]之间的时候，才有可能
                //如果和前面一项合起来在10~26直接，则再加上前两项的数
                dp[i + 1] += dp[i - 1];
            }
            if (dp[i + 1] == 0) {
                //出现不合法直接返回0
                return 0;
            }
        }
        return dp[s.length()];
    }

    List<List<String>> result = new ArrayList<>();

    private void dfs(String s, int start, Stack<String> stack) {
        if (start == s.length()) {
            System.out.println(stack);
            result.add(new ArrayList<>(stack));
            return;
        }
        if (s.charAt(start) == '0') {
            return;
        }
        for (int i = start + 1; i <= s.length(); i++) {
            String substring = s.substring(start, i);
            if (substring.length() > 2 || (substring.length() == 2 && Integer.parseInt(substring) > 26)) {
                continue;
            }
            stack.push(substring);
            dfs(s, i, stack);
            stack.pop();
        }
    }
}
