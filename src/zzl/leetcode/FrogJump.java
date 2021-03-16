package zzl.leetcode;

import org.junit.Assert;
import zzl.util.GenerateUtil;
import zzl.util.PrintConsoleUtil;

/**
 * 青蛙过河
 * TODO 未解答出，暂存
 *
 * @link {https://leetcode-cn.com/problems/frog-jump/}
 */
public class FrogJump {
    public static void main(String[] args) {
        int[] stones;
        boolean crossRiver;
        //
        stones = GenerateUtil.generateIntArray("0,1,3,5,6,8,12,17");
        crossRiver = new FrogJump().canCross(stones);
        Assert.assertEquals(crossRiver, true);
        //
        System.out.println("====");
        stones = GenerateUtil.generateIntArray("0,1,2,3,4,8,9,11");
        crossRiver = new FrogJump().canCross(stones);
        Assert.assertEquals(crossRiver, false);

    }

    int[] positions = {-1, 0, 1};

    public boolean canCross(int[] stones) {

        return crossDfs(stones, 0, 0, 0, 0);
    }

    private boolean crossDfs(int[] stones, int start, int jump, int moveLength, int moveTime) {
        if (jump + moveLength >= stones[stones.length - 1] && moveTime == stones.length - 1) {
            System.out.println(String.format("moveLength=%s, moveTime=%s", moveLength, moveTime));
            return true;
        }
        if (jump + moveLength < stones[start]) {
            System.out.println(String.format("false : jump=%s, moveLength=%s, stones[%d]=%s", jump, moveLength, start, stones[start]));
            return false;
        }
        int stepLength = stones[start];
        boolean b = false;
        for (int pos : positions) {
            int nextJump = stepLength + pos;
            if (nextJump < 0) {
                continue;
            }
            PrintConsoleUtil.printParams("start", start, "stones[" + start + "]", stones[start], "pos", pos, "nextJump", nextJump, "moveLength", moveLength, "moveTime", moveTime);
            b = crossDfs(stones, start + 1, nextJump, stones[start] + nextJump, moveTime + 1);
            if (b) {
                return true;
            }
        }
        return false;

    }
}
