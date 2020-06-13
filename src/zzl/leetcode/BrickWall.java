package zzl.leetcode;

import org.junit.Assert;
import zzl.util.GenerateUtil;

import java.util.HashMap;
import java.util.List;

/**
 * 砖墙
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/brick-wall/}
 */
public class BrickWall {
    public static void main(String[] args) {
        BrickWall util = new BrickWall();
        List<List<Integer>> input = GenerateUtil.generateBinaryList("1,2,2,1\n" +
                "3,1,2\n" +
                "1,3,2\n" +
                "2,4\n" +
                "3,1,2\n" +
                "1,3,1,1", ",");
        Assert.assertEquals(util.leastBricks(input), 2);
        input = GenerateUtil.generateBinaryList(
                "1\n" +
                        "1\n" +
                        "1\n", ",");
        Assert.assertEquals(util.leastBricks(input), 3);
    }

    public int leastBricks(List<List<Integer>> wall) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum;
        for (List<Integer> line : wall) {
            sum = 0;
            for (int i = 0; i < line.size() - 1; i++) {
                sum += line.get(i);
                if (map.containsKey(sum)) {
                    map.put(sum, map.get(sum) + 1);
                } else {
                    map.put(sum, 1);
                }
            }
        }
        int min = Integer.MAX_VALUE;
        for (Integer width : map.keySet()) {
            min = Integer.min(min, wall.size() - map.get(width));
        }
        return min == Integer.MAX_VALUE ? wall.size() : min;
    }
}
