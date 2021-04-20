package zzl.leetcode;

import zzl.base.annotation.Level;
import zzl.util.GenerateUtil;
import zzl.util.SpecialAssertUtil;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static zzl.base.enums.Difficulty.*;

/**
 * 找到所有数组中消失的数字
 *
 * @author zzl
 * @date 2021-04-20
 * @link {https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array/}
 */
@Level(SIMPLE)
public class FindAllNumbersDisappearedInAnArray {
    public static void main(String[] args) {
        int[] nums;
        List<Integer> disappearedNumbers;
        //
        nums = GenerateUtil.generateIntArray("4,3,2,7,8,2,3,1");
        disappearedNumbers = new FindAllNumbersDisappearedInAnArray().findDisappearedNumbers(nums);
        SpecialAssertUtil.assertIntListContain(disappearedNumbers, Arrays.asList(5, 6));
    }

    /**
     * Set 去重
     *
     * @param nums
     * @return
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        Set<Integer> set = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        List<Integer> res = new LinkedList<>();
        for (int i = 1; i <= nums.length; i++) {
            if (!set.contains(i))
                res.add(i);
        }
        return res;
    }
}
