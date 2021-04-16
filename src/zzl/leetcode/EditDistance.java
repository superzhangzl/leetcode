package zzl.leetcode;

import org.junit.Assert;

import java.util.HashMap;

/**
 * 编辑距离
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/edit-distance/}
 */
public class EditDistance {
    public static void main(String[] args) {
        String word1;
        String word2;
        int minDistance;
        //
        word1 = "horse";
        word2 = "ros";
        minDistance = new EditDistance().minDistance(word1, word2);
        Assert.assertEquals(minDistance, 3);
        //
        word1 = "intention";
        word2 = "execution";
        minDistance = new EditDistance().minDistance(word1, word2);
        Assert.assertEquals(minDistance, 5);
    }

    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            // 此处的base case就是一个单词有，另一个单词是空串，那要变过去直接把当前的长度设置即可
            dp[i][0] = i;
        }
        for (int j = 1; j <= n; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                // 字符串的下标从0开始
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j] + 1,
                            Math.min(dp[i][j - 1] + 1,
                                    dp[i - 1][j - 1] + 1));
                }
            }
        }
        return dp[m][n];


    }

    char[] s1;
    char[] s2;

    /**
     * 递归版本
     *
     * @param word1
     * @param word2
     * @return
     */
    public int minDistanceRecursive(String word1, String word2) {
        s1 = word1.toCharArray();
        s2 = word2.toCharArray();
        return dp(word1.length() - 1, word2.length() - 1);
    }

    private HashMap<String, Integer> cache = new HashMap<>();

    /**
     * i -> j
     * 递归版本
     *
     * @param i
     * @param j
     * @return
     */
    private int dp(int i, int j) {
        /*
        base case：只要其中一方走完了，那就返回另外一个字符串剩余的长度
         */
        if (i == -1) {
            return j + 1;
        }
        if (j == -1) {
            return i + 1;
        }
        String key = i + "," + j;
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        int res;
        if (s1[i] == s2[j]) {
            res = dp(i - 1, j - 1);//字符串相同什么都不做
        } else {
            res = Math.min(dp(i, j - 1) + 1,           // 插入
                    Math.min(dp(i - 1, j) + 1,          // 删除
                            dp(i - 1, j - 1) + 1));  // 替换
        }
        cache.put(key, res);
        return res;
    }
}
