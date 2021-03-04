package zzl.leetcode;

import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

import java.util.ArrayDeque;

/**
 * 地图中的最高点
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/map-of-highest-peak/}
 */
public class MapOfHighestPeak {
    public static void main(String[] args) {
        int[][] array = GenerateUtil.generateBinaryIntArray("0,1\n" +
                "0,0");
        int[][] highestPeak = new MapOfHighestPeak().highestPeak(array);
        PrintConsoleUtil.printArray(highestPeak);
        array = GenerateUtil.generateBinaryIntArray("0,0,1\n" +
                "1,0,0\n" +
                "0,0,0");
        highestPeak = new MapOfHighestPeak().highestPeak(array);
        PrintConsoleUtil.printArray(highestPeak);
    }

    int[][] position = {
            {0, 1},
            {0, -1},
            {1, 0},
            {-1, 0},
    };

    /**
     * 多源BFS
     *
     * @param isWater
     * @return
     */
    public int[][] highestPeak(int[][] isWater) {
        if (isWater == null || isWater.length == 0) {
            return new int[0][0];
        }
        int[][] result = new int[isWater.length][isWater[0].length];
        // 队列
        ArrayDeque<Pair> deque = new ArrayDeque<>();
        for (int i = 0; i < isWater.length; i++) {
            for (int j = 0; j < isWater[i].length; j++) {
                if (isWater[i][j] == 1) {
                    // 从所有的水域为出发点，因为水域都是同样的初始值，而陆地有可能会变高
                    deque.add(new Pair(i, j));
                }
            }
        }
        while (!deque.isEmpty()) {
            Pair pair = deque.poll();
            for (int[] pos : position) {
                int x = pos[0] + pair.x;
                int y = pos[1] + pair.y;
                if (x >= 0 && x < isWater.length && y >= 0 && y < isWater[0].length && isWater[x][y] == 0) {
                    // x,y 相比较原位置的海拔要多1，满足题意。也就是x,y是个陆地，name就相比较原位置海拔要大于1
                    result[x][y] = result[pair.x][pair.y] + 1;
                    // 从水域出发，标记当前陆地节点为水域，即已经访问过了
                    isWater[x][y] = 1;
                    // 再把这个位置放进队列，下一次等着和周边未被访问的陆地进行比较，如果还有了则需要把海拔+1
                    deque.add(new Pair(x, y));
                }
            }

        }
        return result;
    }

    class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

