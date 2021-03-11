package zzl.leetcode;

import zzl.util.GenerateUtil;

import java.util.List;

/**
 * 项目管理
 * TODO 暂存
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/sort-items-by-groups-respecting-dependencies/}
 */
public class SortItemsByGroupsRespectingDependencies {

    public static void main(String[] args) {
        int n;
        int m;
        int[] group;
        int[][] beforeItems;

        n = 8;
        m = 2;
        group = GenerateUtil.generateIntArray("-1,-1,1,0,0,1,0,-1");
        beforeItems = GenerateUtil.generateBinaryIntArrayBetter("[[],[6],[5],[6],[3,6],[],[],[]]");
    }

    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {

    }
}
