package zzl.leetcode;

import org.junit.Assert;
import zzl.base.annotation.Level;
import zzl.util.GenerateUtil;

import java.util.Arrays;
import java.util.stream.Collectors;

import static zzl.base.enums.Difficulty.*;

/**
 * 最大数
 *
 * @author zzl
 * @date 2021-04-19
 * @link {https://leetcode-cn.com/problems/largest-number/}
 */
@Level(MEDIUM)
public class LargestNumber {
    public static void main(String[] args) {
        int[] nums;
        String largestNumber;
        //
        nums = GenerateUtil.generateIntArray("3,30,34,5,9");
        largestNumber = new LargestNumber().largestNumber(nums);
        Assert.assertEquals(largestNumber, "9534330");
        //
        nums = GenerateUtil.generateIntArray("0,0,0");
        largestNumber = new LargestNumber().largestNumber(nums);
        Assert.assertEquals(largestNumber, "0");
        //
        nums = GenerateUtil.generateIntArray("0,0,0,1");
        largestNumber = new LargestNumber().largestNumber(nums);
        Assert.assertEquals(largestNumber, "1000");
        //
        nums = GenerateUtil.generateIntArray("99999,888888888,0,1");
        largestNumber = new LargestNumber().largestNumber(nums);
        Assert.assertEquals(largestNumber, "9999988888888810");
    }

    /**
     * 先排序，即将字符串大的排在前面，这样形成的字符串肯定最大
     * 如果遇到全0的情况，就直接替换掉。
     * 注：当前算法仅完成算法，并没有考虑优化情况想
     *
     * @param nums
     * @return
     */
    public String largestNumber(int[] nums) {
        return Arrays.stream(nums)
                .mapToObj(String::valueOf)
                .sorted((o1, o2) -> (o2 + o1).compareTo(o1 + o2))
                .collect(Collectors.joining())
                .replaceAll("^0+", "0");
    }
}
