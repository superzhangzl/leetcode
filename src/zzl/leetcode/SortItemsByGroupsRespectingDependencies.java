package zzl.leetcode;

import zzl.base.annotation.Level;
import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

import java.util.*;

import static zzl.base.enums.Difficulty.HARD;

/**
 * 项目管理
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/sort-items-by-groups-respecting-dependencies/}
 */
@Level(HARD)
public class SortItemsByGroupsRespectingDependencies {

    public static void main(String[] args) {
        int n;
        int m;
        int[] group;
        List<List<Integer>> beforeItems;
        int[] sortItems;

        n = 8;
        m = 2;
        group = GenerateUtil.generateIntArray("-1,-1,1,0,0,1,0,-1");
        beforeItems = GenerateUtil.generateBinaryIntList("[[],[6],[5],[6],[3,6],[],[],[]]");
        System.out.println(beforeItems);
        sortItems = new SortItemsByGroupsRespectingDependencies().sortItems(n, m, group, beforeItems);
        PrintConsoleUtil.printArray(sortItems);
    }

    /**
     * 拓扑排序：
     * 第一优先级：组相同的必须排在一起
     * 第二优先级：项目的先后顺序必须要满足拓扑排序
     * <p>
     * 思路：
     * 1. 先按照组进行拓扑排序
     * 2. 再按照项目进行拓扑排序
     * 3. 得出组合项目之间的对应关系
     * 4. 将组的拓扑排序中的“组”，按照项目的拓扑顺序进行替换
     * <p>
     * 参考：链接中视频分析
     *
     * @param n           项目个数
     * @param m           小组个数
     * @param group       group[i]表示i项目所属小组
     * @param beforeItems group[i]表示i项目前置任务
     * @return
     * @link {https://leetcode-cn.com/problems/sort-items-by-groups-respecting-dependencies/solution/1203-xiang-mu-guan-li-by-leetcode-t63b/}
     */
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        // 第 1 步：数据预处理，给没有归属于一个组的项目编上组号，防止所有的-1被分到一个组里
        for (int i = 0; i < group.length; i++) {
            if (group[i] == -1) {
                // m为组数上限，直接赋值自增即可
                group[i] = m;
                m++;
            }
        }

        // 第 2 步：实例化组和项目的邻接表
        List<Integer>[] groupAdj = new ArrayList[m];
        List<Integer>[] itemAdj = new ArrayList[n];
        for (int i = 0; i < m; i++) {
            groupAdj[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            itemAdj[i] = new ArrayList<>();
        }

        // 第 3 步：建图和统计入度数组
        int[] groupsIndegree = new int[m];
        int[] itemsIndegree = new int[n];

        int len = group.length;
        for (int i = 0; i < len; i++) {
            int currentGroup = group[i];
            for (int beforeItem : beforeItems.get(i)) {
                int beforeGroup = group[beforeItem];
                // 根据项目的前驱关系构造对应的组的关系
                if (beforeGroup != currentGroup) {
                    groupAdj[beforeGroup].add(currentGroup);
                    groupsIndegree[currentGroup]++;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (Integer item : beforeItems.get(i)) {
                itemAdj[item].add(i);
                itemsIndegree[i]++;
            }
        }
        PrintConsoleUtil.printArray(groupAdj);
        PrintConsoleUtil.printArray(itemAdj);
        PrintConsoleUtil.printArray(groupsIndegree);
        PrintConsoleUtil.printArray(itemsIndegree);

        // 第 4 步：得到组和项目的拓扑排序结果
        List<Integer> groupsList = topologicalSort(groupAdj, groupsIndegree, m);
        if (groupsList.size() == 0) {
            return new int[0];
        }
        List<Integer> itemsList = topologicalSort(itemAdj, itemsIndegree, n);
        if (itemsList.size() == 0) {
            return new int[0];
        }

        // 第 5 步：根据项目的拓扑排序结果，项目到组的多对一关系，建立组到项目的一对多关系
        // key：组，value：在同一组的项目列表
        Map<Integer, List<Integer>> groups2Items = new HashMap<>();
        for (Integer item : itemsList) {
            groups2Items.computeIfAbsent(group[item], key -> new ArrayList<>()).add(item);
        }

        // 第 6 步：把组的拓扑排序结果替换成为项目的拓扑排序结果
        List<Integer> res = new ArrayList<>();
        for (Integer groupId : groupsList) {
            List<Integer> items = groups2Items.getOrDefault(groupId, new ArrayList<>());
            res.addAll(items);
        }
        return res.stream().mapToInt(Integer::valueOf).toArray();
    }


    /**
     * 拓扑排序模板
     *
     * @param adj
     * @param inDegree
     * @param n
     * @return
     */
    private List<Integer> topologicalSort(List<Integer>[] adj, int[] inDegree, int n) {
        List<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new ArrayDeque<>();
        // 此处注意是需要将下标写入队列中
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        while (!queue.isEmpty()) {
            Integer pre = queue.poll();
            result.add(pre);
            List<Integer> list = adj[pre];
            for (Integer successor : list) {
                inDegree[successor]--;
                if (inDegree[successor] == 0) {
                    queue.add(successor);
                }
            }
        }
        if (result.size() == n) {
            return result;
        }
        return new ArrayList<>();
    }

}
