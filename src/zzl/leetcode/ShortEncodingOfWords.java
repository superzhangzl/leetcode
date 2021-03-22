package zzl.leetcode;

import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

/**
 * 单词的压缩编码
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/short-encoding-of-words/}
 */
public class ShortEncodingOfWords {
    public static void main(String[] args) {
        String[] words;
        int lengthEncoding;
        //===
        words = new String[]{"time", "me", "bell"};
        lengthEncoding = new ShortEncodingOfWords().minimumLengthEncoding(words);
        Assert.assertEquals(lengthEncoding, 10);
        //===
        words = new String[]{"t"};
        lengthEncoding = new ShortEncodingOfWords().minimumLengthEncoding(words);
        Assert.assertEquals(lengthEncoding, 2);


    }

    public int minimumLengthEncoding(String[] words) {
        // 判空
        if (words == null || words.length == 0) {
            return 0;
        }
        DirTreeNode head = new DirTreeNode();
        Map<DirTreeNode, Integer> tailNodeAndMaxLengthWordIndex = new HashMap<>();

        for (int i = 0; i < words.length; ++i) {
            String word = words[i];
            DirTreeNode cur = head;
            for (int j = word.length() - 1; j >= 0; --j) {
                cur = cur.get(word.charAt(j));
            }
            // 每个起始单词在树种的位置以及单词的下标
            tailNodeAndMaxLengthWordIndex.put(cur, i);
        }
        int res = 0;
        for (DirTreeNode node : tailNodeAndMaxLengthWordIndex.keySet()) {
            // count=0说明是叶子节点，即当前前缀最大深度
            if (node.count == 0) {
                // 找到当前树对应的单词的下标，再获得对应的长度，+1表示结尾的#号
                res += words[tailNodeAndMaxLengthWordIndex.get(node)].length() + 1;
            }
        }
        // 注：其实也可以计算每个叶子节点的高度做累加，或者保留当前树的深度
        return res;
    }

    class DirTreeNode {
        DirTreeNode[] children;
        // 子节点的个数
        int count;

        public DirTreeNode() {
            this.children = new DirTreeNode[26];
            this.count = 0;
        }

        public DirTreeNode get(char c) {
            if (children[c - 'a'] == null) {
                children[c - 'a'] = new DirTreeNode();
                count++;
            }
            return children[c - 'a'];
        }
    }
}
