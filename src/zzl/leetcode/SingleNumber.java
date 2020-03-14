package zzl.leetcode;

import org.junit.Assert;
import zzl.util.GenerateUtil;

/**
 * 只出现一次的数字
 * 算法应该具有线性时间复杂度。 使用额外空间来实现
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/single-number/}
 */
public class SingleNumber {
    public static void main(String[] args) {
        int[] input = GenerateUtil.generateIntArray("2,2,1", ",");
        Assert.assertEquals(new SingleNumber().singleNumber(input), 1);
        input = GenerateUtil.generateIntArray("4,1,2,1,2", ",");
        Assert.assertEquals(new SingleNumber().singleNumber(input), 4);
    }

    /**
     * 题目要求不使用额外空间来实现，个人的思路是用一个大整数的每个二进制位来存储，出现一次设置为1，再出现一次设置为0，
     * 这样剩下的最后一个位就是当前的数字的位置。然而看到官方示例中使用数学思路来解决，更为精巧。
     * <p>
     * 官方解题：
     * 如果我们对 0 和二进制位做 XOR 运算，得到的仍然是这个二进制位
     * a \oplus 0 = aa⊕0=a
     * 如果我们对相同的二进制位做 XOR 运算，返回的结果是 0
     * a \oplus a = 0a⊕a=0
     * XOR 满足交换律和结合律
     * a \oplus b \oplus a = (a \oplus a) \oplus b = 0 \oplus b = ba⊕b⊕a=(a⊕a)⊕b=0⊕b=b
     * 所以我们只需要将所有的数进行 XOR 操作，得到那个唯一的数字。
     *
     * @param nums
     * @return
     * @link {https://leetcode-cn.com/problems/single-number/solution/zhi-chu-xian-yi-ci-de-shu-zi-by-leetcode/}
     */
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            result ^= nums[i];
        }
        return result;
    }
}
