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
        Assert.assertEquals(new WordLadder().ladderLength2(beginWord, endWord, wordList), 5);
        beginWord = "hit";
        endWord = "cog";
        wordList = Arrays.asList("hot", "dot", "dog", "lot", "log");
        Assert.assertEquals(new WordLadder().ladderLength2(beginWord, endWord, wordList), 0);
    }

    /**
     * 1. 算法与之前描述的标准广搜方法相类似。
     * 2. 唯一的不同是我们从两个节点同时开始搜索，同时搜索的结束条件也有所变化。
     * 3. 我们现在有两个访问数组，分别记录从对应的起点是否已经访问了该节点。
     * 4. 如果我们发现一个节点被两个搜索同时访问，就结束搜索过程。因为我们找到了双向搜索的交点。过程如同从中间相遇而不是沿着搜索路径一直走。
     * > 双向搜索的结束条件是找到一个单词被两边搜索都访问过了。
     * 5. 最短变换序列的长度就是中间节点在两边的层次之和。因此，我们可以在访问数组中记录节点的层次。
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    Map<String, ArrayList<String>> allComboDict = new HashMap<>();
    int wordLength = 0;

    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        if (wordList.isEmpty() || !wordList.contains(endWord)) {
            return 0;
        }
        wordLength = beginWord.length();
        // 构造通用状态表
        wordList.forEach(word -> {
            for (int i = 0; i < wordLength; i++) {
                // Key is the generic word
                // Value is a list of words which have the same intermediate generic word.
                String newWord = word.substring(0, i) + '*' + word.substring(i + 1, wordLength);
                ArrayList<String> transformations =
                        allComboDict.getOrDefault(newWord, new ArrayList<>());
                transformations.add(word);
                allComboDict.put(newWord, transformations);
            }
        });
        // 访问表，这是存储的是到当前层级的深度
        Map<String, Integer> beginVisited = new HashMap<>();
        beginVisited.put(beginWord, 1);
        Map<String, Integer> endVisited = new HashMap<>();
        endVisited.put(endWord, 1);
        // 不使用递归，Pair用来保存当前访问的单词和访问深度
        // 起始队列
        Queue<Pair<String, Integer>> beginQueue = new LinkedList<>();
        beginQueue.add(new Pair(beginWord, 1));
        // 结尾队列
        Queue<Pair<String, Integer>> endQueue = new LinkedList<>();
        endQueue.add(new Pair(endWord, 1));

        while (!beginQueue.isEmpty() && !endQueue.isEmpty()) {
            // 传入不同的队列，让他俩去碰头
            int res = visit(beginQueue, beginVisited, endVisited);
            if (res > -1) {
                return res;
            }
            res = visit(endQueue, endVisited, beginVisited);
            if (res > -1) {
                return res;
            }
        }
        return 0;

    }

    public int visit(Queue<Pair<String, Integer>> queue, Map<String, Integer> isVisited, Map<String, Integer> otherVisited) {
        Pair<String, Integer> input = queue.remove();
        String currentLevelWord = input.getKey();
        System.out.println(currentLevelWord);
        for (int i = 0; i < wordLength; i++) {
            String currentRegion = currentLevelWord.substring(0, i) + "*" + currentLevelWord.substring(i + 1, wordLength);
            System.out.println(currentRegion);
            // 读取到当前word对应的几种通用状态，从map中获取该通用状态下保存的单词
            for (String next : allComboDict.getOrDefault(currentRegion, new ArrayList<>())) {
                // 就是说当前队列中访问的这个数，在另外一个visitedMap中找到了，说明在此处相遇了
                if (otherVisited.containsKey(next)) {
                    System.out.println("result: " + next);
                    // 双端队列在此处碰头
                    return input.getValue() + otherVisited.get(next);
                }
                if (!isVisited.containsKey(next)) {
                    System.out.println("next: " + next);
                    isVisited.put(next, input.getValue() + 1);
                    queue.add(new Pair(next, input.getValue() + 1));
                }
            }
        }
        return -1;
    }

    /**
     * 算法
     * <p>
     * 1. 对给定的 wordList 做预处理，找出所有的通用状态。将通用状态记录在字典中，键是通用状态，值是所有具有通用状态的单词。
     * 2. 将包含 beginWord 和 1 的元组放入队列中，1 代表节点的层次。我们需要返回 endWord 的层次也就是从 beginWord 出发的最短距离。
     * 3. 为了防止出现环，使用访问数组记录。
     * 4. 当队列中有元素的时候，取出第一个元素，记为 current_word。
     * 5. 找到 current_word 的所有通用状态，并检查这些通用状态是否存在其它单词的映射，这一步通过检查 all_combo_dict 来实现。
     * 6. 从 all_combo_dict 获得的所有单词，都和 current_word 共有一个通用状态，所以都和 current_word 相连，因此将他们加入到队列中。
     * 7. 对于新获得的所有单词，向队列中加入元素 (word, level + 1) 其中 level 是 current_word 的层次。
     * 8. 最终当你到达期望的单词，对应的层次就是最短变换序列的长度。
     * <p>
     * > 标准广度优先搜索的终止条件就是找到结束单词。
     * <p>
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
     private Map<String, List<String>> ele = new HashMap<>();
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
