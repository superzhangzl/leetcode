package zzl.leetcode;

import org.junit.Assert;

/**
 * @author zzl
 * @link {https://leetcode-cn.com/problems/jump-game/}
 */
public class JumpGame {
    public static void main(String[] args) {
        Assert.assertEquals(canJump(new int[]{2, 3, 1, 1, 4}), true);
        Assert.assertEquals(canJump(new int[]{3, 2, 1, 0, 4}), false);
    }

    /**
     * 解题思路：
     * 如果某一个作为 起跳点 的格子可以跳跃的距离是 3，那么表示后面 3 个格子都可以作为 起跳点。
     * 可以对每一个能作为 起跳点 的格子都尝试跳一次，把 能跳到最远的距离 不断更新。
     * 如果可以一直跳到最后，就成功了。
     *
     * @param nums
     * @return
     * @link {https://leetcode-cn.com/problems/jump-game/solution/55-by-ikaruga/}
     */
    public static boolean canJump(int[] nums) {
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > k) {
                //表示左边有一堵墙，左边任意节点，都不能跳到当前节点
                return false;
            }
            //当前节点能到的范围
            k = Math.max(k, i + nums[i]);
        }
        return true;
    }
}
