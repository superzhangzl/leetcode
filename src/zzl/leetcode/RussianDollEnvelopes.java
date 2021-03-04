package zzl.leetcode;

import org.junit.Assert;
import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

import java.util.Arrays;

public class RussianDollEnvelopes {
    public static void main(String[] args) {
        int[][] mails = GenerateUtil.generateBinaryIntArray("5,4\n" +
                "6,4\n" +
                "6,7\n" +
                "2,3");
        int count = new RussianDollEnvelopes().maxEnvelopes(mails);
        Assert.assertEquals(count, 3);

        mails = GenerateUtil.generateBinaryIntArray("1,1\n" +
                "1,1\n" +
                "1,1\n" +
                "1,1");
        count = new RussianDollEnvelopes().maxEnvelopes(mails);
        Assert.assertEquals(count, 1);

        mails = GenerateUtil.generateBinaryIntArray("1,2\n2,3\n3,4\n3,5\n4,5\n5,5\n5,6\n6,7\n7,8");
        count = new RussianDollEnvelopes().maxEnvelopes(mails);
        Assert.assertEquals(count, 7);


    }

    /**
     * 两个最长递增子序列
     * <p>
     * 讲解详见labuladong的《算法小抄》
     *
     * @param envelopes
     * @return
     */
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) {
            return 0;
        }
        // 首先按照信封的长来升序排列，相同时，按照宽的降序排列
        // 这样长就是一定能满足的，然后我们再比较宽
        // 首先宽从第一个开始(长最小的)，依次找更宽的信封，这样就一定会满足条件
        Arrays.sort(envelopes, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o2[1] - o1[1];
            } else {
                return o1[0] - o2[0];
            }
        });
        PrintConsoleUtil.printArray(envelopes);
        int count = 1;
        int[] dp = new int[envelopes.length];
        Arrays.fill(dp, 1);
        for (int i = 1; i < envelopes.length; i++) {
            // 以[i]为节点，查看之前比他小的值，若有则在[j]的基础上dp+1
            for (int j = 0; j < i; j++) {
                if (envelopes[j][1] < envelopes[i][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            count = Math.max(count, dp[i]);
        }
        return count;
    }
}
