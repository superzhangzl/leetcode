package zzl.leetcode;

import org.junit.Assert;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 单词拆分
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/word-break/}
 */
public class WordBreak {
    public static void main(String[] args) {
        //  s = "leetcode", wordDict = ["leet", "code"]
        String word = "leetleetcode";
        List<String> wordDict = Arrays.asList("leet", "code");
        Assert.assertEquals(new WordBreak().wordBreakBfs(word, wordDict), true);
        System.out.println("==================");
        word = "applepenapple";
        wordDict = Arrays.asList("apple", "pen");
        Assert.assertEquals(new WordBreak().wordBreakBfs(word, wordDict), true);
        System.out.println("==================");

        word = "catsandog";
        wordDict = Arrays.asList("cats", "dog", "sand", "and", "cat");
        Assert.assertEquals(new WordBreak().wordBreakBfs(word, wordDict), false);
        System.out.println("==================");
        word = "aaaaaaa";
        wordDict = Arrays.asList("aaaa", "aaa");
        Assert.assertEquals(new WordBreak().wordBreakBfs(word, wordDict), false);

    }

    /**
     * 暴力递归
     * 时间复杂度：O(n^n) 。考虑最坏情况 ss = aaaaaaa 。每一个前缀都在字典中，此时回溯树的复杂度会达到 n^n
     * 空间复杂度：O(n)。回溯树的深度最深达到 n。
     *
     * @param s
     * @param wordDict
     * @return
     * @link {https://leetcode-cn.com/problems/word-break/solution/dan-ci-chai-fen-by-leetcode/}
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        // memo 数组会被用来保存从start开始的子问题的结果。每当访问到已经访问过的后缀串，直接用 memo 数组中的值返回而不需要继续调用函数。
        return dfs(s, 0, wordDict, new Boolean[s.length()]);
    }

    private boolean dfs(String s, int start, List<String> wordDict, Boolean[] memory) {
        if (start == s.length()) {
            return true;
        }
        if (memory[start] != null) {
            return memory[start];
        }

        for (int i = start + 1; i <= s.length(); i++) {
            // [start, end)
            String substring = s.substring(start, i);
            System.out.println(substring);
            // 顺序不能变，先判断当前前缀的substring的是否在字典中，包含了再从下一位开始往后继续匹配
            if (wordDict.contains(substring)
                    // dfs的start传i而不是i+1，因为substring的时候，end是不包含在内的
                    && dfs(s, i, wordDict, memory)) {
                return memory[start] = true;
            }
        }
        return memory[start] = false;
    }

    public boolean wordBreakBfs(String s, List<String> wordDict) {
        //另一个方法是使用宽度优先搜索。将字符串可视化成一棵树，每一个节点是用 endend 为结尾的前缀字符串。当两个节点之间的所有节点都对应了字典中一个有效字符串时，两个节点可以被连接。
        int length = s.length();
        Queue<Integer> queue = new LinkedList();
        queue.add(0);
        int[] visited = new int[length];
        while (!queue.isEmpty()) {
            Integer start = queue.poll();
            if (visited[start] == 0) {
                for (int i = start + 1; i <= length; i++) {
                    String substring = s.substring(start, i);
                    System.out.println(substring);
                    if (wordDict.contains(substring)) {
                        queue.add(i);
                        if (i == length) {
                            return true;
                        }
                        break;
                    }
                }
            }
            visited[start] = 1;
        }
        return false;
    }
}
