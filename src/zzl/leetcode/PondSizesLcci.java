package zzl.leetcode;

import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author zzl
 * @link {https://leetcode-cn.com/problems/pond-sizes-lcci/}
 */
public class PondSizesLcci {
    public static void main(String[] args) {
        int[][] land = GenerateUtil.generateBinaryIntArray("0,2,1,0,\n" +
                "0,1,0,1,\n" +
                "1,1,0,1,\n" +
                "0,1,0,1", ",");
        int[] sizes = new PondSizesLcci().pondSizes(land);
        PrintConsoleUtil.printArray(sizes);
    }

    boolean[][] visited;

    public int[] pondSizes(int[][] land) {
        if (land == null || land.length == 0) {
            return new int[0];
        }
        visited = new boolean[land.length][land[0].length];
        ArrayList<Integer> sizes = new ArrayList<>();
        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < land[i].length; j++) {
                if (land[i][j] == 0 && !visited[i][j]) {
                    size = 0;
                    dfs(land, i, j);
                    sizes.add(size);
                }
            }
        }
        sizes.sort(Comparator.comparingInt(o -> o));
        int[] ints = new int[sizes.size()];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = sizes.get(i);
        }
        return ints;
    }

    int[][] position = {
            {0, 1},
            {0, -1},
            {1, 1},
            {1, -1},
            {-1, 1},
            {-1, -1},
            {1, 0},
            {-1, 0},
    };
    int size;

    private void dfs(int[][] land, int i, int j) {
        if (i < 0 || i >= land.length || j < 0 || j >= land[0].length || visited[i][j]) {
            return;
        }
        if (land[i][j] == 0) {
            visited[i][j] = true;
            size += 1;
        } else {
            return;
        }
        for (int[] pos : position) {
            dfs(land, i + pos[0], j + pos[1]);
        }
    }
}
