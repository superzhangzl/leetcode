package zzl.leetcode;

import org.junit.Assert;
import zzl.util.GenerateUtil;

/**
 * 跳跃游戏 II
 * 假设你总是可以到达数组的最后一个位置。
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/jump-game-ii/}
 * @tag 45
 */
public class JumpGameII {
    public static void main(String[] args) {
        int[] input = GenerateUtil.generateIntArray("2,3,1,1,4", ",");
        Assert.assertEquals(new JumpGameII().jump(input), 2);
        input = GenerateUtil.generateIntArray("1,1,1,1,1", ",");
        Assert.assertEquals(new JumpGameII().jump(input), 4);
        input = GenerateUtil.generateIntArray("0", ",");
        Assert.assertEquals(new JumpGameII().jump(input), 0);
        input = GenerateUtil.generateIntArray("0,1,2", ",");
        Assert.assertEquals(new JumpGameII().jump(input), 0);
        input = GenerateUtil.generateIntArray("1", ",");
        Assert.assertEquals(new JumpGameII().jump(input), 0);
        input = GenerateUtil.generateIntArray("4,1,1,3,1,1,1", ",");
        Assert.assertEquals(new JumpGameII().jump(input), 2);
        input = GenerateUtil.generateIntArray("7,0,9,6,9,6,1,7,9,0,1,2,9,0,3", ",");
        Assert.assertEquals(new JumpGameII().jump(input), 2);
    }

    public int jump(int[] nums) {
        System.out.println("========");
        // 最大可跳跃的距离
        int maxJumpStep = 0;
        // 最小的跳跃次数
        int minJumpCount = 0;
        // 当前情况下，可跳的最远位置
        int currentMaxJumpPosition = 0;
        // 修复，不用去操作最后一位数，因为最后一个位置是 0 步
        // 题目有假设最后最后一个位置是可达的，所以这里的返回是nums.length - 1,否则需要改成nums.length，同时限制最后累加时不将最后一次纳入判断
        for (int i = 0; i < nums.length - 1; i++) {
            if (i > maxJumpStep) {
                //表示不可达
                return 0;
            }
            // 以当前位置i为起点，计算的最大跳远距离
            maxJumpStep = Integer.max(maxJumpStep, i + nums[i]);
            System.out.println(maxJumpStep);
            // 当i等于当前最远可跳的距离时，就需要更新一下下一次可跳的最远距离了
            // 如果不一定可达，则需要在此处设置，并将i的范围变成[0, nums.length)
//            if (i == currentMaxJumpPosition && i != nums.length - 1) {
            if (i == currentMaxJumpPosition) {
                currentMaxJumpPosition = maxJumpStep;
                minJumpCount++;
            }
        }
        return minJumpCount;
    }
}
