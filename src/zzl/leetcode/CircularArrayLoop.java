package zzl.leetcode;

import org.junit.Assert;
import zzl.base.annotation.Level;
import zzl.util.GenerateUtil;

import static zzl.base.enums.Difficulty.*;

/**
 * 环形数组是否存在循环
 *
 * @author zzl
 * @date 2021-08-07
 * @link {https://leetcode-cn.com/problems/circular-array-loop/}
 */
@Level(MEDIUM)
public class CircularArrayLoop {
    public static void main(String[] args) {
        int[] nums;
        boolean loop;
        //
        nums = GenerateUtil.generateIntArray("2,-1,1,2,2");
        loop = new CircularArrayLoop().circularArrayLoop(nums);
        Assert.assertTrue(loop);
        //
        nums = GenerateUtil.generateIntArray("-1,2");
        loop = new CircularArrayLoop().circularArrayLoop(nums);
        Assert.assertFalse(loop);
        //
        nums = GenerateUtil.generateIntArray("-2,1,-1,-2,-2");
        loop = new CircularArrayLoop().circularArrayLoop(nums);
        Assert.assertFalse(loop);
    }

    /**
     * 暴力解法：
     * 因为不确定哪个点是起始点，所以需要假设以某一个点开始，算它是不是起点
     *
     * @param nums
     * @return
     * @link {https://leetcode-cn.com/problems/circular-array-loop/solution/gong-shui-san-xie-yi-ti-shuang-jie-mo-ni-ag05/}
     */
    public boolean circularArrayLoop(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (checkIsLoopStartPoint(i, nums)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkIsLoopStartPoint(int start, int[] nums) {
        int n = nums.length;
        int cur = start;
        int loopLength = 1;
        // 起始点是否为正数
        boolean startIsPositive = nums[start] > 0;
        while (true) {
            if (loopLength > n) return false;
            int next = ((cur + nums[cur]) % n + n) % n;
            // 判断起始点和循环点的是否都为正数或者负数
            if (nums[start] * nums[next] < 0) {
                return false;
            }
            // 形成了环
            if (next == start) {
                return loopLength > 1;
            }
            // 循环长度+1
            loopLength++;
            // 步数+1
            cur = next;
        }
    }
}
