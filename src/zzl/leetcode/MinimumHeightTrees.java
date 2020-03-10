package zzl.leetcode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 最小高度树
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/minimum-height-trees/}
 */
public class MinimumHeightTrees {
    public static void main(String[] args) {
        int[][] input = new int[][]{
                {1, 0},
                {1, 2},
                {1, 3}
        };
        System.out.println(new MinimumHeightTrees().findMinHeightTrees(4, input));//1
        input = new int[][]{
                {0, 3},
                {1, 3},
                {2, 3},
                {4, 3},
                {5, 4},
        };
        System.out.println(new MinimumHeightTrees().findMinHeightTrees(6, input));// 3, 4
        input = new int[][]{
                {0, 1},
                {0, 2},
                {0, 3},
                {3, 4},
                {4, 5},
        };
        System.out.println(new MinimumHeightTrees().findMinHeightTrees(6, input));// 3
    }

    Map<Integer, ArrayList<Integer>> map = new HashMap<>();
    Map<Integer, Integer> rootWithMinHeight = new HashMap<>();


    /**
     * 不断地删除它的叶子节点（度为1）那么最后剩下的1或2（最长链长度为偶数时）个节点就是所求答案。
     *
     * @param n
     * @param edges
     * @return
     * @link {https://blog.csdn.net/qq_32142535/article/details/78120352}
     */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 0) {
            return Collections.emptyList();
        }
        if (n == 1) {
            return Arrays.asList(0);
        }
        if (n == 2) {
            return Arrays.asList(0, 1);
        }
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            int node1 = edges[i][0];
            int node2 = edges[i][1];
            if (map.containsKey(node1)) {
                map.get(node1).add(node2);
            } else {
                ArrayList<Integer> set = new ArrayList<>();
                set.add(node2);
                map.put(node1, set);
            }
            if (map.containsKey(node2)) {
                map.get(node2).add(node1);
            } else {
                ArrayList<Integer> set = new ArrayList<>();
                set.add(node1);
                map.put(node2, set);
            }
        }
        // 构造所有的点与其他点相连的集合
        System.out.println(map);
        Queue<Integer> leaves = new LinkedList<>();
        Queue<Integer> newLeaves = new LinkedList<>();
        // 将入度为1的点（代表叶子节点）放入队列
        for (Map.Entry<Integer, ArrayList<Integer>> entry : map.entrySet()) {
            if (entry.getValue().size() == 1)
                leaves.add(entry.getKey());
        }
        // 结果只能是1或者2
        while (map.size() > 2 || (map.size() == 2 && leaves.size() != 2)) {
            // 弹出一个值
            int leaf = leaves.poll();
            // 从集合中获取到该叶子节点相连的节点的一个值（入度为1）
            int neighbor = map.get(leaf).get(0);
            // 断开与邻居节点的连接，即将邻居节点中连接的list删除他
            map.get(neighbor).remove(Integer.valueOf(leaf));
            // 如果邻居节点变成1了，即他的入度为1，他又变成叶子节点了
            if (map.get(neighbor).size() == 1)
                // 将他添加进下一轮的操作队列中
                newLeaves.add(neighbor);
            // 从map中移出开始弹出的节点，他已经被干掉了
            map.remove(leaf);
            // 如果第一轮清空叶子节点行动已完成，再进行下一轮清理计划！
            if (leaves.isEmpty()) {
                leaves = newLeaves;
                newLeaves = new LinkedList<>();
            }
            System.out.println(">>>" + map);
        }
        System.out.println(map);
        return map.entrySet().stream().map(Map.Entry::getKey).collect(Collectors.toList());
    }

    /**
     * 不合理的算法，暴力dfs，会超时！！！
     *
     * @param n
     * @param edges
     * @return
     */
    public List<Integer> findMinHeightTreesStupid(int n, int[][] edges) {
        if (n == 0) {
            return Collections.emptyList();
        }
        if (n == 1) {
            return Arrays.asList(0);
        }
        if (n == 2) {
            return Arrays.asList(0, 1);
        }

        for (int i = 0; i < edges.length; i++) {
            int node1 = edges[i][0];
            int node2 = edges[i][1];
            if (map.containsKey(node1)) {
                map.get(node1).add(node2);
            } else {
                ArrayList<Integer> set = new ArrayList<>();
                set.add(node2);
                map.put(node1, set);
            }
            if (map.containsKey(node2)) {
                map.get(node2).add(node1);
            } else {
                ArrayList<Integer> set = new ArrayList<>();
                set.add(node1);
                map.put(node2, set);
            }
        }
//        System.out.println(map);
        for (int i = 0; i < n; i++) {
            curHeight = Integer.MIN_VALUE;
            List<Integer> visited = new LinkedList<>();
            visited.add(i);
            dfs(i, 0, n, visited);
            if (curHeight < minHeight) {
                minHeight = curHeight;
            }
        }
//        System.out.println(minHeight);
//        System.out.println(rootWithMinHeight);
        return rootWithMinHeight.entrySet().stream().filter(e -> e.getValue() == minHeight).map(Map.Entry::getKey).collect(Collectors.toList());
    }

    private int minHeight = Integer.MAX_VALUE;
    private int curHeight = Integer.MIN_VALUE;

    private void dfs(int root, int height, int size, List<Integer> visited) {
        if (visited.size() == size + 1) {
            return;
        }
        if (height > curHeight) {
            curHeight = height;
        }
//        System.out.println(root + " > " + visited);
        if (map.containsKey(root)) {
            List<Integer> list = map.get(root);
            for (Integer node : list) {
                if (!visited.contains(node)) {
                    visited.add(node);
                    dfs(node, height + 1, size, visited);
                }
            }
        }
        rootWithMinHeight.put(visited.get(0), curHeight);
    }


    /**
     * 我这个解法的思路是单纯的根据二维数组的联通关系进行拆解的，没有考虑到n这个参数，所以可能存在许多个单节点的情况下
     * 跑题了，但是这个解法就留着当备份吧
     * 这个解法是获取含有最短边的节点，就是距离叶子节点最近的那些节点
     *
     * @param n
     * @param edges
     * @return
     */
    @Deprecated
    public List<Integer> findMinHeightTreesBackup(int n, int[][] edges) {
        if (n == 0) {
            return Collections.emptyList();
        }
        if (n == 1) {
            return Arrays.asList(0);
        }
        if (n == 2) {
            return Arrays.asList(0, 1);
        }
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            int node1 = edges[i][0];
            int node2 = edges[i][1];
            if (map.containsKey(node1)) {
                map.get(node1).add(node2);
            } else {
                ArrayList<Integer> set = new ArrayList<>();
                set.add(node2);
                map.put(node1, set);
            }
            if (map.containsKey(node2)) {
                map.get(node2).add(node1);
            } else {
                ArrayList<Integer> set = new ArrayList<>();
                set.add(node1);
                map.put(node2, set);
            }
        }
        Queue<Integer> leaves = new LinkedList<>();
        Queue<Integer> newLeaves = new LinkedList<>();
        for (Map.Entry<Integer, ArrayList<Integer>> entry : map.entrySet()) {
            if (entry.getValue().size() == 1)
                leaves.add(entry.getKey());
        }
        while (map.size() > 2 || (map.size() == 2 && leaves.size() != 2)) {
            int leaf = leaves.poll();
            int neighbor = map.get(leaf).get(0);
            map.get(neighbor).remove(leaf);
            if (map.get(neighbor).size() == 1)
                newLeaves.add(neighbor);
            map.remove(leaf);

            if (leaves.isEmpty()) {
                leaves = newLeaves;
                newLeaves = new LinkedList<>();
            }
        }
        System.out.println(map);
        return new ArrayList<>();
    }
}
