package zzl.leetcode;

import org.junit.Assert;
import zzl.base.annotation.Level;
import zzl.util.GenerateUtil;

import java.util.Arrays;

import static zzl.base.enums.Difficulty.*;

/**
 * 最长连续序列
 *
 * @author zzl
 * @date 2021-04-20
 * @link {https://leetcode-cn.com/problems/longest-consecutive-sequence/}
 */
@Level(HARD)
public class LongestConsecutiveSequence {
    public static void main(String[] args) {
        int[] nums;
        int longestConsecutive;
        //
        nums = GenerateUtil.generateIntArray("100,4,200,1,3,2");
        longestConsecutive = new LongestConsecutiveSequence().longestConsecutive(nums);
        Assert.assertEquals(longestConsecutive, 4);
        //
        nums = GenerateUtil.generateIntArray("0,3,7,2,5,8,4,6,0,1");
        longestConsecutive = new LongestConsecutiveSequence().longestConsecutive(nums);
        Assert.assertEquals(longestConsecutive, 9);

    }

    /**
     * 先排序，然后再用两个变量判断每段序列中最长的那个序列。
     * 时间复杂度: O(nlogn)，不是最高的，但是是最好理解的
     *
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int res = 0;        //最终所有连续递增序列的最大长度
        int curLength = 1; // 记录当前一段序列的最大长度
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) { //如果和上一个相同，直接跳过本次循环
                // 值相同不算做连续序列
                continue;
            }
            if (nums[i] - nums[i - 1] == 1) {
                curLength++;
            } else {
                res = Math.max(curLength, res);
                curLength = 1;
            }
        }
        // 比较res和curLength的长度，因为最后一轮比较完，res更新那一步还没有走就跳出循环了
        // 所以再执行一次比较操作
        return Math.max(res, curLength);
    }
}
