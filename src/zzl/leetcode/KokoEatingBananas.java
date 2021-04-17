package zzl.leetcode;

import org.junit.Assert;
import zzl.base.annotation.Level;
import zzl.util.GenerateUtil;

import java.util.Arrays;

import static zzl.base.enums.Difficulty.MEDIUM;

/**
 * 爱吃香蕉的珂珂
 *
 * @author zzl
 * @link {https://leetcode-cn.com/problems/koko-eating-bananas/}
 */
@Level(MEDIUM)
public class KokoEatingBananas {
    public static void main(String[] args) {
        int[] piles;
        int minEatingSpeed;
        //
        piles = GenerateUtil.generateIntArray("3,6,7,11");
        minEatingSpeed = new KokoEatingBananas().minEatingSpeed(piles, 8);
        Assert.assertEquals(minEatingSpeed, 4);
        //
        piles = GenerateUtil.generateIntArray("30,11,23,4,20");
        minEatingSpeed = new KokoEatingBananas().minEatingSpeed(piles, 5);
        Assert.assertEquals(minEatingSpeed, 30);
        //
        minEatingSpeed = new KokoEatingBananas().minEatingSpeed(piles, 6);
        Assert.assertEquals(minEatingSpeed, 23);
    }

    /**
     * 二分查找，找恰好满足吃完香蕉的临界点
     *
     * @param piles
     * @param h
     * @return
     */
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = Arrays.stream(piles).max().getAsInt();
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (!canFinish(piles, mid, h)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    /**
     * 统计吃完香蕉需要的小时数，和限制小时数做比较
     *
     * @param piles
     * @param mid
     * @param h
     * @return
     */
    private boolean canFinish(int[] piles, int mid, int h) {
        int count = 0;
        for (int pile : piles) {
            // 因为吃不完只能留到下一个小时继续吃，所以需要向上取整
            count += (pile / mid + (pile % mid > 0 ? 1 : 0));
        }
        return count <= h;
    }
}
