package zzl.leetcode;

/**
 * 添加与搜索单词 - 数据结构设计
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/add-and-search-word-data-structure-design/}
 */
public class AddAndSearchWordDataStructureDesign {
    public static void main(String[] args) {
        WordDictionary obj = new WordDictionary();
// ["WordDictionary","addWord","addWord","addWord","addWord","search","search","addWord","search","search","search","search","search","search"]
// [[],["at"],["and"],["an"],["add"]       ,["a"] ,[".at"],["bat"],[".at"],["an."],["a.d."],["b."],["a.d"],["."]]
//        obj.addWord("at");
        obj.addWord("and");
        obj.addWord("an");
//        obj.addWord("add");
//        System.out.println(obj.search("a"));
//        System.out.println(obj.search(".at"));
//        System.out.println(obj.search("bat"));
//        System.out.println(obj.search(".at"));
        System.out.println(obj.search("an."));
//        System.out.println(obj.search("a.d."));
//        System.out.println(obj.search("b."));
//        System.out.println(obj.search("a.d"));
//        System.out.println(obj.search("."));
//        [null,null,null,null,null,false,false,null,true,false,false,false,true,false]
//        [null,null,null,null,null,false,false,null,true,true,false,false,true,false]
    }

    public static class WordDictionary {
        Node[] dictionary = new Node[26];

        /**
         * Initialize your data structure here.
         */
        public WordDictionary() {
        }

        /**
         * Adds a word into the data structure.
         */
        public void addWord(String word) {
            if (word == null || word.length() == 0) {
                return;
            }
            if (word.matches(".*[A-Z].*")) {
                return;
            }
            char[] chars = word.toCharArray();
            int firstIndex = chars[0] - 'a';
            Node node = dictionary[firstIndex];
            if (node == null) {
                node = new Node(chars[0]);
                dictionary[firstIndex] = node;
            }
            for (int i = 1; i < chars.length; i++) {
                char character = chars[i];
                Node[] next = node.next;
                if (next == null) {
                    next = new Node[26];
                    node.next = next;
                }
                if (next[character - 'a'] == null) {
                    Node newNode = new Node(character);
                    next[character - 'a'] = newNode;
                }
                node = next[character - 'a'];
            }
            node.isEnd = true;
        }

        /**
         * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
         */
        public boolean search(String word) {
            if (word == null || word.length() == 0) {
                return false;
            }
            if (word.matches(".*[A-Z].*")) {
                return false;
            }
            char[] charArray = word.toCharArray();
            if (charArray[0] != '.') {
                int firstIndex = charArray[0] - 'a';
                Node node = dictionary[firstIndex];
                if (node == null) {
                    return false;
                }
                return depthSearch(charArray, 0, node);
            } else {
                boolean b = false;
                for (Node son : dictionary) {
                    if (son == null) {
                        continue;
                    }
                    b = depthSearch(charArray, 0, son);
                    if (b == true) {
                        return true;
                    }
                }
                return b;
            }
        }

        private boolean depthSearch(char[] charArray, int next, Node node) {
            if (node == null || next >= charArray.length || (node.val != charArray[next] && charArray[next] != '.')) {
                return false;
            }
            if (next == charArray.length - 1) {
                if (node.isEnd) {
                    return true;
                } else {
                    return false;
                }
            }
            if (charArray[next + 1] == '.') {
                if (node.next == null) {
                    return false;
                }
                boolean b = false;
                for (Node son : node.next) {
                    b = depthSearch(charArray, next + 1, son);
                    if (b == true) {
                        return true;
                    }
                }
                return b;
            } else {
                if (node.next == null) {
                    return false;
                }
                return depthSearch(charArray, next + 1, node.next[charArray[next + 1] - 'a']);
            }
        }


    }

    private static class Node {
        public char val;
        public boolean isEnd = false;
        public Node[] next;

        public Node(char x) {
            val = x;
        }

    }
}