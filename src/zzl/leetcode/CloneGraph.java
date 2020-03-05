package zzl.leetcode;

import zzl.base.Node;

import java.util.HashMap;

/**
 * @author zzl
 * @link {https://leetcode-cn.com/problems/clone-graph/}
 */
public class CloneGraph {
    public static void main(String[] args) {
    }

    // map中存储的是引用，那在图的遍历中，就可以用来标记是否访问过
    // value就作为被拷贝的对象
    HashMap<Node, Node> oneByOne = new HashMap<>();

    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        // 判断是否访问过
        if (oneByOne.containsKey(node)) {
            return oneByOne.get(node);
        }
        Node clone = new Node(node.val);
        oneByOne.put(node, clone);
        for (Node neighbor : node.neighbors) {
            // 添加被克隆的对象，不能直接添加neighbor，会引用重复
            clone.neighbors.add(cloneGraph(neighbor));
        }
        return clone;
    }
}
