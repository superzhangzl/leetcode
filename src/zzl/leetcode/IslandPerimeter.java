package zzl.leetcode;

import org.junit.Assert;
import zzl.util.GenerateUtil;

/**
 * 岛屿的周长
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/island-perimeter/}
 */
public class IslandPerimeter {


    public static void main(String[] args) {
        int[][] grid;
        int perimeter;
        // ==
        grid = GenerateUtil.generateBinaryIntArrayBetter("[[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]");
        perimeter = new IslandPerimeter().islandPerimeter(grid);
        Assert.assertEquals(perimeter, 16);
        // ==
        grid = GenerateUtil.generateBinaryIntArrayBetter("[[1]]");
        perimeter = new IslandPerimeter().islandPerimeter(grid);
        Assert.assertEquals(perimeter, 4);
        // ==
        grid = GenerateUtil.generateBinaryIntArrayBetter("[[1,0]]");
        perimeter = new IslandPerimeter().islandPerimeter(grid);
        Assert.assertEquals(perimeter, 4);

    }


    int[][] positions = {
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1}
    };

    public int islandPerimeter(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int landSize = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0) {
                    continue;
                }
                int count = 0;
                for (int[] pos : positions) {
                    int nx = i + pos[0];
                    int ny = j + pos[1];
                    if (nx >= 0 && nx < grid.length && ny >= 0 && ny < grid[i].length) {
                        if (grid[nx][ny] == 1) {
                            count++;
                        }
                    }
                }
                landSize += (4 - count);
            }
        }
        return landSize;
    }
}
