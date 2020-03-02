package zzl.leetcode;

import javafx.util.Pair;
import org.junit.Assert;

import java.util.*;

/**
 * 单词接龙
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/word-ladder/}
 */
public class WordLadder {
    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        Assert.assertEquals(new WordLadder().ladderLength(beginWord, endWord, wordList), 5);
        beginWord = "hit";
        endWord = "cog";
        wordList = Arrays.asList("hot", "dot", "dog", "lot", "log");
        Assert.assertEquals(new WordLadder().ladderLength(beginWord, endWord, wordList), 0);
    }

    private Map<String, List<String>> ele = new HashMap<>();

    /**
     * https://leetcode-cn.com/problems/word-ladder/solution/dan-ci-jie-long-by-leetcode/
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (wordList.isEmpty() || !wordList.contains(endWord)) {
            return 0;
        }
        // 所有单词长度相同
        int L = beginWord.length();

        // 记录每一种通用状态所保存的单词
        Map<String, ArrayList<String>> allComboDict = new HashMap<>();

        wordList.forEach(word -> {
            for (int i = 0; i < L; i++) {
                // Key is the generic word
                // Value is a list of words which have the same intermediate generic word.
                String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);
                ArrayList<String> transformations =
                        allComboDict.getOrDefault(newWord, new ArrayList<>());
                transformations.add(word);
                allComboDict.put(newWord, transformations);
            }
        });
        System.out.println(allComboDict);
        // 防止出现重复（环）
        Map<String, Boolean> isVisited = new HashMap<>();
        isVisited.put(beginWord, true);
        // 不使用递归，Pair用来保存当前访问的单词和访问深度
        Queue<Pair<String, Integer>> queue = new LinkedList<>();
        queue.add(new Pair(beginWord, 1));
        while (!queue.isEmpty()) {
            Pair<String, Integer> input = queue.remove();
            String currentLevelWord = input.getKey();
            System.out.println(currentLevelWord);
            for (int i = 0; i < L; i++) {
                String currentRegion = currentLevelWord.substring(0, i) + "*" + currentLevelWord.substring(i + 1, L);
                System.out.println(currentRegion);
                // 读取到当前word对应的几种通用状态，从map中获取该通用状态下保存的单词
                for (String next : allComboDict.getOrDefault(currentRegion, new ArrayList<>())) {
                    if (next.equals(endWord)) {
                        // 当前层级对应的下一个
                        System.out.println("result: " + next);
                        return input.getValue() + 1;
                    }
                    if (!isVisited.containsKey(next)) {
                        System.out.println("next: " + next);
                        isVisited.put(next, true);
                        queue.add(new Pair(next, input.getValue() + 1));
                    }
                }
            }
        }
        return 0;
    }

    /*
    // 保存了单个字符所相连的
    Set<String> collect = Arrays.stream(wordList.stream().collect(Collectors.joining()).split("")).collect(Collectors.toSet());
        System.out.println(collect);
        for (String character : collect) {
            for (String word : wordList) {
                if (word.contains(character)) {
                    if (ele.containsKey(character)) {
                        ele.get(character).add(word);
                    } else {
                        ele.put(character, new ArrayList() {{
                            add(word);
                        }});
                    }
                }
            }
        }
        System.out.println(ele);
     */
}
